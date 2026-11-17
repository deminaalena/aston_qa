package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class FactorialTest {
    @ParameterizedTest
    @DisplayName("Вычисление факториала неотрицательных целых чисел")
    @CsvSource({
            "0, 1",
            "1, 1",
            "5, 120",
            "10, 3628800",
            "20, 2432902008176640000"

    })
    void factorialPositiveTest(int number, long expected) {
        Assertions.assertEquals(expected, Factorial.factorial(number));
    }

    @ParameterizedTest
    @DisplayName("Вычисление факториала отрицательных чисел")
    @ValueSource(ints = {-1, -20, Integer.MIN_VALUE})
    void factorialNegativeNumberTest(int number) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Factorial.factorial(number));
    }

    @ParameterizedTest
    @DisplayName("Вычисление факториала слишком большого числа")
    @ValueSource(ints = {21, 25, Integer.MAX_VALUE})
    void factorialTooBigTest(int number) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Factorial.factorial(number));
    }
}
