
package org.example;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CalculatorTest {

    @DataProvider(name = "addData")
    public Object[][] addData() {
        return new Object[][] {
                {2, 3, 5L},
                {-2, -3, -5L},
                {-2, 3, 1L},
                {0, 5, 5L},
                {0, 0, 0L},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 4294967294L},
                {Integer.MIN_VALUE, Integer.MIN_VALUE, -4294967296L}
        };
    }

    @Test(
            dataProvider = "addData",
            description = "Сложение целых чисел"
    )
    public void addTest(int a, int b, long expected) {
        assertEquals(Calculator.add(a, b), expected);
    }

    @DataProvider(name = "subtractData")
    public Object[][] subtractData() {
        return new Object[][] {
                {5, 3, 2L},
                {3, 5, -2L},
                {-5, -3, -2L},
                {-3, -5, 2L},
                {0, 5, -5L},
                {5, 0, 5L},
                {0, 0, 0L},
                {Integer.MAX_VALUE, Integer.MIN_VALUE, 4294967295L},
                {Integer.MIN_VALUE, Integer.MAX_VALUE, -4294967295L}
        };
    }

    @Test(
            dataProvider = "subtractData",
            description = "Вычитание целых чисел"
    )
    public void subtractTest(int a, int b, long expected) {
        assertEquals(Calculator.subtract(a, b), expected);
    }

    @DataProvider(name = "multiplyData")
    public Object[][] multiplyData() {
        return new Object[][] {
                {2, 3, 6L},
                {-2, 3, -6L},
                {-2, -3, 6L},
                {0, 5, 0L},
                {5, 0, 0L},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 4611686014132420609L},
                {Integer.MIN_VALUE, Integer.MIN_VALUE, 4611686018427387904L},
                {Integer.MIN_VALUE, Integer.MAX_VALUE, -4611686016279904256L}
        };
    }

    @Test(
            dataProvider = "multiplyData",
            description = "Умножение целых чисел"
    )
    public void multiplyTest(int a, int b, long expected) {
        assertEquals(Calculator.multiply(a, b), expected);
    }

    @DataProvider(name = "divideData")
    public Object[][] divideData() {
        return new Object[][] {
                {6, 3, 2.0},
                {5, 2, 2.5},
                {7, 2, 3.5},
                {-6, 3, -2.0},
                {6, -3, -2.0},
                {-6, -3, 2.0},
                {0, 5, 0.0},
                {Integer.MIN_VALUE, -1, 2147483648.0}
        };
    }

    @Test(
            dataProvider = "divideData",
            description = "Деление целых чисел"
    )
    public void divideTest(int a, int b, double expected) {
        assertEquals(Calculator.divide(a, b), expected, 0.0001);
    }

    @Test(
            expectedExceptions = ArithmeticException.class,
            description = "Обработка деления на ноль"
    )
    public void divideByZeroTest() {
        Calculator.divide(10, 0);
    }
}
