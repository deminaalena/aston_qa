
package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalculatorTest {

    @ParameterizedTest
    @DisplayName("Сложение положительных, отрицательных чисел и нуля")
    @CsvSource({
            "2, 3, 5",
            "-2, -3, -5",
            "-3, 3, 0",
            "0, 5, 5",
            "100, 200, 300"
    })
    void addTest(int a, int b, long expected) {
        Assertions.assertEquals(expected, Calculator.add(a, b));
    }

    @ParameterizedTest
    @DisplayName("Сложение граничных значений int")
    @CsvSource({
            "2147483647, 1, 2147483648",
            "-2147483648, -1, -2147483649",
            "2147483647, 2147483647, 4294967294",
            "-2147483648, -2147483648, -4294967296"
    })
    void addBoundaryTest(int a, int b, long expected) {
        Assertions.assertEquals(expected, Calculator.add(a, b));
    }

    @ParameterizedTest
    @DisplayName("Вычитание положительных, отрицательных чисел и нуля")
    @CsvSource({
            "5, 3, 2",
            "3, 5, -2",
            "-3, -3, 0",
            "0, 5, -5",
            "-5, 3, -8"
    })
    void subtractTest(int a, int b, long expected) {
        Assertions.assertEquals(expected, Calculator.subtract(a, b));
    }

    @ParameterizedTest
    @DisplayName("Вычитание граничных значений int")
    @CsvSource({
            "2147483647, -1, 2147483648",
            "-2147483648, 1, -2147483649",
            "2147483647, -2147483648, 4294967295",
            "-2147483648, 2147483647, -4294967295"
    })
    void subtractBoundaryTest(int a, int b, long expected) {
        Assertions.assertEquals(expected, Calculator.subtract(a, b));
    }

    @ParameterizedTest
    @DisplayName("Умножение положительных, отрицательных чисел и нуля")
    @CsvSource({
            "2, 3, 6",
            "-2, -3, 6",
            "-3, 3, -9",
            "0, 5, 0",
            "10, 10, 100"
    })
    void multiplyTest(int a, int b, long expected) {
        Assertions.assertEquals(expected, Calculator.multiply(a, b));
    }

    @ParameterizedTest
    @DisplayName("Умножение граничных значений int")
    @CsvSource({
            "2147483647, 2147483647, 4611686014132420609",
            "-2147483648, -2147483648, 4611686018427387904",
            "-2147483648, 2147483647, -4611686016279904256"
    })
    void multiplyBoundaryTest(int a, int b, long expected) {
        Assertions.assertEquals(expected, Calculator.multiply(a, b));
    }

    @ParameterizedTest
    @DisplayName("Деление с целым результатом")
    @CsvSource({
            "6, 3, 2",
            "-6, 3, -2",
            "-6, -3, 2",
            "0, 5, 0"
    })
    void divideIntegerTest(int a, int b, double expected) {
        Assertions.assertEquals(expected, Calculator.divide(a, b), 0.0001);
    }

    @ParameterizedTest
    @DisplayName("Деление с дробным результатом")
    @CsvSource({
            "5, 2, 2.5",
            "1, 3, 0.3333333",
            "-5, 2, -2.5",
            "7, 2, 3.5"
    })
    void divideFractionalTest(int a, int b, double expected) {
        Assertions.assertEquals(expected, Calculator.divide(a, b), 0.0001);
    }

    @ParameterizedTest
    @DisplayName("Деление граничных значений int")
    @CsvSource({
            "2147483647, 1, 2147483647",
            "-2147483648, 1, -2147483648",
            "-2147483648, -1, 2147483648",
            "2147483647, 2147483647, 1"
    })
    void divideBoundaryTest(int a, int b, double expected) {
        Assertions.assertEquals(expected, Calculator.divide(a, b), 0.0001);
    }

    @Test
    @DisplayName("Обработка деления на ноль")
    void divideByZeroTest() {
        Assertions.assertThrows(ArithmeticException.class,
                () -> Calculator.divide(5, 0));
    }
}
