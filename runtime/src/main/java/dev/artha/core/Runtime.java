package dev.artha.core;

import dev.artha.annotations.Step;
import dev.artha.http.Request;
import dev.artha.http.RequestImpl;
import dev.artha.http.Response;
import dev.artha.http.ResponseImpl;
import io.javalin.Javalin;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * ARTHA Runtime - Main server entry point
 */
public class Runtime {

    public static void main(String[] args) {
        printBanner();

        // Get port from CLI -Dartha.port or default 8080
        int port = Integer.parseInt(System.getProperty("artha.port", "8080"));
        System.out.println("ℹ️  Using port: " + port + "\n");

        Javalin app = Javalin.create(config -> {
            config.showJavalinBanner = false;

            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(it -> {
                    it.anyHost();
                });
            });
        });

        // Scan for @Step annotations
        System.out.println("🔍 Scanning for routes...\n");

        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .setUrls(ClasspathHelper.forJavaClassPath())
                        .setScanners(Scanners.TypesAnnotated)
        );

        Set<Class<?>> stepClasses = reflections.getTypesAnnotatedWith(Step.class);

        if (stepClasses.isEmpty()) {
            System.out.println("⚠️  No @Step annotations found!");
            System.out.println("   Make sure your classes use @Step annotation\n");
        }

        // Register routes
        for (Class<?> clazz : stepClasses) {
            registerRoute(app, clazz);
        }

        app.start(port);

        System.out.println("\n✓ Server started at http://localhost:" + port + "\n");
        System.out.println("Press Ctrl+C to stop\n");
    }

    private static void registerRoute(Javalin app, Class<?> clazz) {
        try {
            Step step = clazz.getAnnotation(Step.class);
            String path = step.path();
            String method = step.method().toUpperCase();

            System.out.println("  " + method + "  " + path + " → " + clazz.getSimpleName());

            switch (method) {
                case "GET":
                    app.get(path, ctx -> handleRequest(ctx, clazz));
                    break;
                case "POST":
                    app.post(path, ctx -> handleRequest(ctx, clazz));
                    break;
                case "PUT":
                    app.put(path, ctx -> handleRequest(ctx, clazz));
                    break;
                case "DELETE":
                    app.delete(path, ctx -> handleRequest(ctx, clazz));
                    break;
            }
        } catch (Exception e) {
            System.err.println("❌ Failed to register " + clazz.getName());
            e.printStackTrace();
        }
    }

    private static void handleRequest(io.javalin.http.Context ctx, Class<?> clazz) {
        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();
            Method handleMethod = clazz.getMethod("handle", Request.class, Response.class);

            Request req = new RequestImpl(ctx);
            Response res = new ResponseImpl(ctx);

            Object result = handleMethod.invoke(instance, req, res);

            // Auto-serialize if not already handled
            if (result != null) {
                if (result instanceof String) {
                    ctx.result((String) result);
                } else {
                    ctx.json(result);
                }
            }
        } catch (Exception e) {
            ctx.status(500).json(Map.of(
                    "error", "Internal server error",
                    "message", e.getMessage()
            ));
            e.printStackTrace();
        }
    }

    private static void printBanner() {
        System.out.println();
        System.out.println("   █████╗ ██████╗ ████████╗██╗  ██╗ █████╗ ");
        System.out.println("  ██╔══██╗██╔══██╗╚══██╔══╝██║  ██║██╔══██╗");
        System.out.println("  ███████║██████╔╝   ██║   ███████║███████║");
        System.out.println("  ██╔══██║██╔══██╗   ██║   ██╔══██║██╔══██║");
        System.out.println("  ██║  ██║██║  ██║   ██║   ██║  ██║██║  ██║");
        System.out.println("  ╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝");
        System.out.println();
        System.out.println("  Simple Java Backend Framework for Students");
        System.out.println("  Version 0.1.0");
        System.out.println();
    }
}
