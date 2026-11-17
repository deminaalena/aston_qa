
package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class TriangleAreaTest {

    @ParameterizedTest
    @DisplayName("Вычисление площади различных типов треугольников")
    @CsvSource({
            "3, 4, 5, 6",
            "5, 5, 6, 12",
            "13, 14, 15, 84",
            "5, 5, 5, 10.8253175"
    })
    void validTriangleTest(double a, double b, double c, double expected) {
        Assertions.assertEquals(expected,
                TriangleArea.triangleArea(a, b, c), 0.0001);
    }

    @ParameterizedTest
    @DisplayName("Вычисление площади при малых значениях сторон")
    @CsvSource({
            "0.0001, 0.0001, 0.0001, 0.000000004330127",
            "0.000001, 0.000001, 0.000001, 0.0000000000004330127"
    })
    void minimumSidesTest(double a, double b, double c, double expected) {
        Assertions.assertEquals(expected,
                TriangleArea.triangleArea(a, b, c), 0.0000000000000001);
    }

    @Test
    @DisplayName("Вычисление площади при больших значениях сторон")
    void maximumSidesTest() {
        Assertions.assertEquals(433012701892.2193,
                TriangleArea.triangleArea(1000000, 1000000, 1000000), 0.001);

        Assertions.assertEquals(433012701892219300.0,
                TriangleArea.triangleArea(1000000000, 1000000000, 1000000000), 1000);
    }

    @ParameterizedTest
    @DisplayName("Обработка треугольников с нарушением неравенства сторон")
    @CsvSource({
            "1, 2, 10",
            "1, 10, 2",
            "10, 1, 2"
    })
    void invalidTriangleTest(double a, double b, double c) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> TriangleArea.triangleArea(a, b, c));
    }

    @ParameterizedTest
    @DisplayName("Обработка вырожденных треугольников")
    @CsvSource({
            "1, 2, 3",
            "3, 4, 7",
            "5, 5, 10"
    })
    void degenerateTriangleTest(double a, double b, double c) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> TriangleArea.triangleArea(a, b, c));
    }

    @Test
    @DisplayName("Вычисление площади при значении стороны ниже граничного")
    void triangleBelowBoundaryTest() {
        Assertions.assertEquals(0.06480623,
                TriangleArea.triangleArea(3, 4, 6.9999), 0.000001);
    }

    @ParameterizedTest
    @DisplayName("Обработка значений стороны на границе и выше неё")
    @CsvSource({
            "3, 4, 7",
            "3, 4, 7.0001"
    })
    void triangleAtOrAboveBoundaryTest(double a, double b, double c) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> TriangleArea.triangleArea(a, b, c));
    }

    @ParameterizedTest
    @DisplayName("Обработка нулевых и отрицательных сторон")
    @CsvSource({
            "0, 3, 4",
            "3, 0, 4",
            "3, 4, 0",
            "-1, 3, 4",
            "3, -1, 4",
            "3, 4, -1"
    })
    void nonPositiveSidesTest(double a, double b, double c) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> TriangleArea.triangleArea(a, b, c));
    }

    @ParameterizedTest
    @DisplayName("Обработка бесконечных значений")
    @ValueSource(doubles = {
            Double.POSITIVE_INFINITY,
            Double.NEGATIVE_INFINITY
    })
    void infiniteSidesTest(double side) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> TriangleArea.triangleArea(side, 3, 4));
    }

    @Test
    @DisplayName("Обработка значения NaN")
    void nanSideTest() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> TriangleArea.triangleArea(Double.NaN, 3, 4));
    }
}
