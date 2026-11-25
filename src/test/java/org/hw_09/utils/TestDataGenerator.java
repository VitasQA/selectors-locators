package org.hw_09.utils;

import com.github.javafaker.Faker;

public class TestDataGenerator {
    private static final Faker faker = new Faker();

    public static String getRandomProductName() {
        String fruit = faker.food().fruit();
        int randomNum = faker.number().numberBetween(1, 999);
        return fruit + "_" + randomNum;
    }

    public static int getRandomPrice() {
        return faker.number().numberBetween(10, 500);
    }
}