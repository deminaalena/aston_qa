
package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CompareTest {

    @ParameterizedTest
    @DisplayName("Сравнение положительных, отрицательных чисел и нуля")
    @CsvSource({
            "1, 2, -1",
            "2, 1, 1",
            "5, 5, 0",
            "-1, -2, 1",
            "-2, -1, -1",
            "-5, 5, -1",
            "5, -5, 1",
            "0, 0, 0",
            "0, 5, -1",
            "5, 0, 1",
            "0, -5, 1",
            "-5, 0, -1"
    })
    void compareTest(int a, int b, int expected) {
        Assertions.assertEquals(expected, Compare.compare(a, b));
    }

    @ParameterizedTest
    @DisplayName("Сравнение граничных значений int")
    @CsvSource({
            "2147483647, 2147483647, 0",
            "-2147483648, -2147483648, 0",
            "2147483647, -2147483648, 1",
            "-2147483648, 2147483647, -1",
            "2147483647, 0, 1",
            "-2147483648, 0, -1"
    })
    void compareBoundaryTest(int a, int b, int expected) {
        Assertions.assertEquals(expected, Compare.compare(a, b));
    }
}
