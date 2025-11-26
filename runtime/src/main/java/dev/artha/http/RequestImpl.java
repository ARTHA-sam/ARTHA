package dev.artha.http;

import io.javalin.http.Context;
import java.util.Map;
import java.util.HashMap;

public class RequestImpl implements Request {
    private final Context ctx;

    public RequestImpl(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public String query(String key) {
        return ctx.queryParam(key);
    }

    @Override
    public String query(String key, String defaultValue) {
        String value = ctx.queryParam(key);
        return value != null ? value : defaultValue;
    }

    @Override
    public Map<String, String> queryMap() {
        Map<String, String> result = new HashMap<>();
        ctx.queryParamMap().forEach((key, values) -> {
            if (values != null && !values.isEmpty()) {
                result.put(key, values.get(0));
            }
        });
        return result;
    }

    @Override
    public String path(String key) {
        try {
            return ctx.pathParam(key);
        } catch (Exception e) {
            throw new RuntimeException("Path parameter '" + key + "' not found.");
        }
    }

    @Override
    public <T> T body(Class<T> clazz) {
        try {
            String b = ctx.body();
            if (b == null || b.trim().isEmpty())
                throw new RuntimeException("Empty body or wrong Content-Type?");
            return ctx.bodyAsClass(clazz);  // Javalin auto-parses JSON!
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON body to " + clazz.getSimpleName() + ": " +
                    e.getMessage() + "\n" +
                    "Expected JSON, got: " + ctx.body());
        }
    }

    @Override
    public String bodyAsString() {
        return ctx.body();
    }

    @Override
    public String header(String key) {
        return ctx.header(key);
    }

    @Override
    public Map<String, String> headers() {
        Map<String, String> result = new HashMap<>();
        ctx.headerMap().forEach(result::put);
        return result;
    }

    @Override
    public String method() {
        return ctx.method().toString();
    }

    @Override
    public String url() {
        return ctx.url();
    }

    @Override
    public String ip() {
        return ctx.ip();
    }
}
