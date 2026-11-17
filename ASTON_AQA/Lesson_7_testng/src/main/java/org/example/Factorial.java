package org.example;

public class Factorial {
    public static long factorial(int num) {
        if (num < 0 || num > 20) {
            throw new IllegalArgumentException("Число должно быть от 0 до 20");
        }
        long factorialResult = 1;
        for (int i = 2; i <= num; i++) {
            factorialResult *= i;
        }
        return factorialResult;
    }
}