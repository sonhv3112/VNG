package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

import org.HdrHistogram.Histogram;

public class Main {
    private static final int NUM_REQUESTS_WARMUP = 200;
    private static final int NUM_REQUESTS = 2000;
    private static final int TIME_SLEEP = 10;
    private static final int INTERVAL_CAL_THROUGHPUT = 5;
    private static void sendRequest(String json) throws Exception {
        URL url = new URL("http://localhost:8080/add");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        byte[] jsonBytes = json.getBytes(StandardCharsets.UTF_8);

        OutputStream os = con.getOutputStream();
        os.write(jsonBytes, 0, jsonBytes.length);

        Thread.sleep(TIME_SLEEP);

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String responseLine = null;
        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }
//        System.out.println(response.toString());
//        System.out.println("Status code: " + con.getResponseCode());

        br.close();
        os.close();
        con.disconnect();
    }

    public static void main(String[] args) throws Exception {

        for (int i = 1; i <= NUM_REQUESTS_WARMUP; ++i) {
            System.out.println("Warm up step " + i + ": ");
            sendRequest("{\"a\": " + i + ", \"b\": " + (i + 1234) + "}");
        }

        long startTimeThroughput = System.nanoTime();
        int numSent = 0;
        List<Double> throughputList = new ArrayList<Double>();

        Histogram histogram = new Histogram(3);

        for (int i = 0; i < NUM_REQUESTS; i++) {
            System.out.println("Benchmark step " + i + ": ");
            long startTime = System.nanoTime();

            sendRequest("{\"a\": " + i + ", \"b\": " + (i + 1234) + "}");
            ++numSent;

            long endTime = System.nanoTime();
            long latency = endTime - startTime;
            histogram.recordValue(latency);

            long elapsedTimeThroughput = (long)((System.nanoTime() - startTimeThroughput) / 1e9);
            if (elapsedTimeThroughput >= INTERVAL_CAL_THROUGHPUT) {
                double throughput = numSent / elapsedTimeThroughput;
                throughputList.add(throughput);
                startTimeThroughput = System.nanoTime();
                numSent = 0;
            }
        }

        System.out.println("Throughput measured list (requests/sec):");
        for (Double x : throughputList)
            System.out.println(x + " requests/sec");

        System.out.println("Latency (ms)");
        System.out.println("Min: " + histogram.getMinValue() / 1e6);
        System.out.println("Max: " + histogram.getMaxValue() / 1e6);
        System.out.println("Mean: " + histogram.getMean() / 1e6);
        System.out.println("Std Dev: " + histogram.getStdDeviation() / 1e6);
        System.out.println("50th percentile: " + histogram.getValueAtPercentile(50) / 1e6);
        System.out.println("99th percentile: " + histogram.getValueAtPercentile(99) / 1e6);
        System.out.println("99.9th percentile: " + histogram.getValueAtPercentile(99.9) / 1e6);
    }
}
