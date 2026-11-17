
package org.example;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FactorialTest {

    @DataProvider(name = "factorialData")
    public Object[][] factorialData() {
        return new Object[][] {
                {0, 1L},
                {1, 1L},
                {5, 120L},
                {10, 3628800L},
                {20, 2432902008176640000L}
        };
    }

    @Test(
            dataProvider = "factorialData",
            description = "Вычисление факториала неотрицательных целых чисел"
    )
    public void factorialTest(int num, long expected) {
        assertEquals(Factorial.factorial(num), expected);
    }

    @DataProvider(name = "negativeFactorialData")
    public Object[][] negativeFactorialData() {
        return new Object[][] {
                {-1},
                {-20},
                {Integer.MIN_VALUE}
        };
    }

    @Test(
            dataProvider = "negativeFactorialData",
            expectedExceptions = IllegalArgumentException.class,
            description = "Обработка отрицательных чисел"
    )
    public void factorialNegativeTest(int num) {
        Factorial.factorial(num);
    }

    @DataProvider(name = "largeFactorialData")
    public Object[][] largeFactorialData() {
        return new Object[][] {
                {21},
                {25},
                {Integer.MAX_VALUE}
        };
    }

    @Test(
            dataProvider = "largeFactorialData",
            expectedExceptions = IllegalArgumentException.class,
            description = "Обработка чисел больше допустимого диапазона"
    )
    public void factorialTooLargeTest(int num) {
        Factorial.factorial(num);
    }
}
