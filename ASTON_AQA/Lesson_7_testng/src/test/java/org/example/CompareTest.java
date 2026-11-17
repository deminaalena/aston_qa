
package org.example;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CompareTest {

    @DataProvider(name = "compareData")
    public Object[][] compareData() {
        return new Object[][] {
                {1, 2, -1},
                {2, 1, 1},
                {5, 5, 0},
                {-1, -2, 1},
                {-2, -1, -1},
                {-5, 5, -1},
                {5, -5, 1},
                {0, 0, 0},
                {0, 5, -1},
                {5, 0, 1},
                {0, -5, 1},
                {-5, 0, -1}
        };
    }

    @Test(
            dataProvider = "compareData",
            description = "Сравнение целых чисел"
    )
    public void compareTest(int a, int b, int expected) {
        assertEquals(Compare.compare(a, b), expected);
    }

    @DataProvider(name = "compareBoundaryData")
    public Object[][] compareBoundaryData() {
        return new Object[][] {
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0},
                {Integer.MIN_VALUE, Integer.MIN_VALUE, 0},
                {Integer.MAX_VALUE, Integer.MIN_VALUE, 1},
                {Integer.MIN_VALUE, Integer.MAX_VALUE, -1},
                {Integer.MAX_VALUE, 0, 1},
                {Integer.MIN_VALUE, 0, -1}
        };
    }

    @Test(
            dataProvider = "compareBoundaryData",
            description = "Сравнение граничных значений"
    )
    public void compareBoundaryTest(int a, int b, int expected) {
        assertEquals(Compare.compare(a, b), expected);
    }
}
