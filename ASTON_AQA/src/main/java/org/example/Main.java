package org.example;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();

        printColor(-5);
        printColor(50);
        printColor(150);

        compareNumbers(10, 5);

        System.out.println(isSumInRange(10, 5));

        printSign(-10);

        System.out.println(isPositive(-5));

        printString("Homework 2.2", 3);

        System.out.println(isLeapYear(2024));

        int[] array10 = {1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0};
        invertArray(array10);
        System.out.println(Arrays.toString(array10));

        int[] array11 = createArrayFromOneToHundred();
        System.out.println(Arrays.toString(array11));

        int[] array12 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        multiplySmallNumbers(array12);
        System.out.println(Arrays.toString(array12));

        int[][] array13 = createDiagonalArray(5);
        printTwoDimensionalArray(array13);

        int[] array14 = createArray(5, 7);
        System.out.println(Arrays.toString(array14));
    }

    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public static void checkSumSign() {
        int a = 1;
        int b = -3;

        if (a + b >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void printColor(int value) {
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    public static void compareNumbers(int a, int b) {
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    public static boolean isSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    public static void printSign(int value) {
        if (value >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }

    public static boolean isPositive(int value) {
        return value < 0;
    }

    public static void printString(String text, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(text);
        }
    }

    public static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    public static void invertArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = 1;
            } else if (array[i] == 1) {
                array[i] = 0;
            }
        }
    }

    public static int[] createArrayFromOneToHundred() {
        int[] array = new int[100];

        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        return array;
    }

    public static void multiplySmallNumbers(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }
    }

    public static int[][] createDiagonalArray(int size) {
        int[][] array = new int[size][size];

        for (int i = 0; i < size; i++) {
            array[i][i] = 1;
            array[i][size - 1 - i] = 1;
        }

        return array;
    }

    public static void printTwoDimensionalArray(int[][] array) {
        for (int[] row : array) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static int[] createArray(int len, int initialValue) {
        int[] array = new int[len];
        Arrays.fill(array, initialValue);
        return array;
    }
}