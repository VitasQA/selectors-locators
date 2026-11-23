package org.hw_06.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить config.properties", e);
        }
    }

    public static String getStandUrl() {
        return properties.getProperty("stand.url");
    }

    public static String getApiUrl() {
        return properties.getProperty("api.url");
    }

    public static int getTimeout() {
        return Integer.parseInt(properties.getProperty("timeout"));
    }

    public static boolean isLoggingEnabled() {
        return Boolean.parseBoolean(properties.getProperty("logging"));
    }

    public static String getAdminUsername() {
        return properties.getProperty("admin.username");
    }

    public static String getAdminPassword() {
        return properties.getProperty("admin.password");
    }

    public static String getProductName() {
        return properties.getProperty("product.name");
    }

    public static int getProductPrice() {
        return Integer.parseInt(properties.getProperty("product.price"));
    }
}