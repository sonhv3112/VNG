package vng.training.w4.slackfake.repository;


import lombok.extern.slf4j.Slf4j;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.RocksIterator;
import vng.training.w4.slackfake.utils.BytesUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public final class AppRocksDB {
    private RocksDB rocksDB;

    public AppRocksDB(String name) {
        RocksDB.loadLibrary();
        final Options options = new Options();
        options.setCreateIfMissing(true);
        File rocksdbDir = new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/rocks-db", name);
        try {
            Files.createDirectories(rocksdbDir.getParentFile().toPath());
            Files.createDirectories(rocksdbDir.getAbsoluteFile().toPath());
            rocksDB = RocksDB.open(options, rocksdbDir.getAbsolutePath());
        } catch(IOException | RocksDBException ex) {
            log.error("Error initializng RocksDB, check configurations and permissions, exception: {}, message: {}, stackTrace: {}",
                    ex.getCause(), ex.getMessage(), ex.getStackTrace());
        }
        log.info("RocksDB in path {} initialized and ready to use", rocksdbDir.getAbsolutePath());
    }

    public synchronized void save(String key, Object value) {
        try {
            byte[] bytesValue = BytesUtils.getBytes(value);
            if (bytesValue != null)
                rocksDB.put(key.getBytes(), bytesValue);
        } catch (RocksDBException e) {
            log.error("Error saving entry in RocksDB, cause: {}, message: {}", e.getCause(), e.getMessage());
        }
    }

    public synchronized Object find(String key) {
        Object result = null;
        try {
            byte[] bytes = rocksDB.get(key.getBytes());
            if(bytes == null) return null;
            result = BytesUtils.getObject(bytes);
        } catch (RocksDBException e) {
            log.error("Error retrieving the entry in RocksDB from key: {}, cause: {}, message: {}", key, e.getCause(), e.getMessage());
        }
        return result;
    }

    public synchronized void delete(String key) {
        try {
            rocksDB.delete(key.getBytes());
        } catch (RocksDBException e) {
            log.error("Error deleting entry in RocksDB, cause: {}, message: {}", e.getCause(), e.getMessage());
        }
    }

    public synchronized List<Object> getAll() {
        List<Object> objectList = new ArrayList<>();
        RocksIterator iterator = rocksDB.newIterator();
        for (iterator.seekToFirst(); iterator.isValid(); iterator.next()) {
            objectList.add(BytesUtils.getObject(iterator.value()));
        }
        return objectList;
    }
}
