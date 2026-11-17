package org.example;

public class Calculator {

    public static long add(int a, int b) {
        return (long) a + b;
    }

    public static long subtract(int a, int b) {
        return (long) a - b;
    }

    public static long multiply(int a, int b) {
        return (long) a * b;
    }

    public static double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        return (double) a / b;
    }

}