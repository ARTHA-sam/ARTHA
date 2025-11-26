package dev.artha.http;

import java.util.Map;

/**
 * ARTHA Request - Simplified HTTP request wrapper
 */
public interface Request {
    // Query params: /api?name=value
    String query(String key);
    String query(String key, String defaultValue);
    Map<String, String> queryMap();

    // Path params: /users/:id
    String path(String key);

    // JSON body
    <T> T body(Class<T> clazz);
    String bodyAsString();

    // Headers
    String header(String key);
    Map<String, String> headers();

    // Metadata
    String method();
    String url();
    String ip();
}
