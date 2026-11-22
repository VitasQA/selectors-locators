package org.hw_06.utils;

import com.github.javafaker.Faker;

public class TestDataGenerator {
    private static final Faker faker = new Faker();

    public static String getRandomProductName() {
        return faker.food().fruit();
    }

    public static int getRandomPrice() {
        return faker.number().numberBetween(10, 500);
    }
}