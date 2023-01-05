package com.example.simplerestapi;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.post("/add").handler(this::addHandler);

        vertx.createHttpServer()
            .requestHandler(router)
            .listen(8080)
            .onSuccess(s -> System.out.println("HTTP server started on port " + s.actualPort()));
    }

    private void addHandler(RoutingContext routingContext) {
        
        JsonObject requestBody = routingContext.getBodyAsJson();

        int a = Integer.parseInt(requestBody.getString("a"));
        int b = Integer.parseInt(requestBody.getString("b"));
        int c = this.add(a, b);

        routingContext.response()
            .putHeader("content-type", "application/json")
            .setStatusCode(200)
            .end(Json.encodePrettily(new AddResult(a, b, c)));
    }

    private int add(int x, int y) { 
        return x + y; 
    }
}

