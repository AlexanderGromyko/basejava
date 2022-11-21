package com.urise.webapp.util;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {
    static final String SPACE = " ";
    static final String DOT = ".";
    static final String EMPTY_STRING = "";

    public static String phoneNumber() {
        final int PHONE_NUMBER_LENGTH = 11;
        return "+" + randomNumericString(PHONE_NUMBER_LENGTH);
    }

    public static String skype(String name) {
        return "skype:" + name.replace(SPACE, DOT);
    }

    public static String email(String name) {
        return name.replace(SPACE, DOT) + "@gmail.com";
    }

    public static String linkedin(String name) {
        return "https://github.com/" + name.replace(SPACE, EMPTY_STRING);
    }

    public static String github(String name) {
        return "https://www.linkedin.com/in/" + name.replace(SPACE, EMPTY_STRING);
    }

    public static String stackoverflow(String name) {
        final int ACCAUNT_LENGTH = 6;
        return "https://stackoverflow.com/users/" + randomNumericString(ACCAUNT_LENGTH);
    }

    public static String homepage(String name) {
        return "http://" + name.replace(SPACE, EMPTY_STRING) + ".com";
    }

    public static String site() {
        final int MINIMUM_LENGTH = 12;
        final int MAXIMUM_LENGTH = 18;
        return "http://" + randomString(randomNumber(MINIMUM_LENGTH, MAXIMUM_LENGTH)) + ".com";
    }

    public static String randomExperienceTitle() {
        final int MINIMUM_LENGTH = 20;
        final int MAXIMUM_LENGTH = 30;
        return randomString(randomNumber(MINIMUM_LENGTH, MAXIMUM_LENGTH));
    }

    public static String randomExperienceDescription() {
        final int MINIMUM_LENGTH = 40;
        final int MAXIMUM_LENGTH = 60;
        return randomString(randomNumber(MINIMUM_LENGTH, MAXIMUM_LENGTH));
    }

    public static String randomCompanyName() {
        final int MINIMUM_LENGTH = 10;
        final int MAXIMUM_LENGTH = 20;
        return randomString(randomNumber(MINIMUM_LENGTH, MAXIMUM_LENGTH));
    }

    public static LocalDate randomLocalDate() {
        return randomLocalDate(LocalDate.of(1970, 1, 1));
    }

    public static LocalDate randomLocalDate(LocalDate minimumDate) {
        long minDay = minimumDate.toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    public static int randomNumber(int minimum, int maximum) {
        return minimum + new Random().nextInt((maximum - minimum) + 1);
    }

    public static String randomString(int length) {
        final String LETTERSS_STRING="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (i < length){
            int number = random.nextInt(LETTERSS_STRING.length());
            stringBuffer.append(LETTERSS_STRING.charAt(number));
            i++;
        }
        return stringBuffer.toString();
    }

    private static String randomNumericString(int length) {
        final int MINIMUM = 0;
        final int MAXIMUM = 9;
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (i < length) {
            stringBuffer.append(randomNumber(MINIMUM, MAXIMUM));
            i++;
        }
        return stringBuffer.toString();
    }
}
