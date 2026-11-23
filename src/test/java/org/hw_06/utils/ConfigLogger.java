package org.hw_06.utils;

public class ConfigLogger {
    public static void printConfig() {
        System.out.println("=== Конфигурация тестов ===");
        System.out.println("URL стенда: " + ConfigReader.getStandUrl());
        System.out.println("URL API: " + ConfigReader.getApiUrl());
        System.out.println("Тайм-аут (мс): " + ConfigReader.getTimeout());
        System.out.println("Логирование: " + ConfigReader.isLoggingEnabled());

        System.out.println("Стартовый товар: " + ConfigReader.getProductName() + " (цена: " + ConfigReader.getProductPrice() + ")");
        System.out.println("==========================");
    }
}