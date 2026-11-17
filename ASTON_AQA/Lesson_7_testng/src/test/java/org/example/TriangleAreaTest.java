
package org.example;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TriangleAreaTest {

    @DataProvider(name = "triangleAreaData")
    public Object[][] triangleAreaData() {
        return new Object[][] {
                {3.0, 4.0, 5.0, 6.0, 0.0001},
                {5.0, 5.0, 6.0, 12.0, 0.0001},
                {13.0, 14.0, 15.0, 84.0, 0.0001},
                {5.0, 5.0, 5.0, 10.8253175, 0.0001},
                {0.0001, 0.0001, 0.0001, 4.330127e-9, 1e-16},
                {0.000001, 0.000001, 0.000001, 4.330127e-13, 1e-20},
                {1000000.0, 1000000.0, 1000000.0,
                        433012701892.2193, 0.001},
                {1000000000.0, 1000000000.0, 1000000000.0,
                        4.330127018922193e17, 1000.0},
                {3.0, 4.0, 6.9999, 0.06480623, 0.000001}
        };
    }

    @Test(
            dataProvider = "triangleAreaData",
            description = "Вычисление площади корректных треугольников"
    )
    public void triangleAreaTest(
            double a, double b, double c,
            double expected, double delta) {

        assertEquals(
                TriangleArea.triangleArea(a, b, c),
                expected,
                delta
        );
    }

    @DataProvider(name = "impossibleTriangleData")
    public Object[][] impossibleTriangleData() {
        return new Object[][] {
                {1.0, 2.0, 10.0},
                {1.0, 10.0, 2.0},
                {10.0, 1.0, 2.0}
        };
    }

    @Test(
            dataProvider = "impossibleTriangleData",
            expectedExceptions = IllegalArgumentException.class,
            description = "Обработка невозможных треугольников"
    )
    public void impossibleTriangleTest(double a, double b, double c) {
        TriangleArea.triangleArea(a, b, c);
    }

    @DataProvider(name = "degenerateTriangleData")
    public Object[][] degenerateTriangleData() {
        return new Object[][] {
                {1.0, 2.0, 3.0},
                {3.0, 4.0, 7.0},
                {5.0, 5.0, 10.0}
        };
    }

    @Test(
            dataProvider = "degenerateTriangleData",
            expectedExceptions = IllegalArgumentException.class,
            description = "Обработка вырожденных треугольников"
    )
    public void degenerateTriangleTest(double a, double b, double c) {
        TriangleArea.triangleArea(a, b, c);
    }

    @DataProvider(name = "invalidSideData")
    public Object[][] invalidSideData() {
        return new Object[][] {
                {0.0, 4.0, 5.0},
                {3.0, 0.0, 5.0},
                {3.0, 4.0, 0.0},
                {-3.0, 4.0, 5.0},
                {3.0, -4.0, 5.0},
                {3.0, 4.0, -5.0}
        };
    }

    @Test(
            dataProvider = "invalidSideData",
            expectedExceptions = IllegalArgumentException.class,
            description = "Обработка нулевых и отрицательных сторон"
    )
    public void invalidSideTest(double a, double b, double c) {
        TriangleArea.triangleArea(a, b, c);
    }

    @DataProvider(name = "nonFiniteSideData")
    public Object[][] nonFiniteSideData() {
        return new Object[][] {
                {Double.POSITIVE_INFINITY, 4.0, 5.0},
                {3.0, Double.NEGATIVE_INFINITY, 5.0},
                {3.0, 4.0, Double.NaN}
        };
    }

    @Test(
            dataProvider = "nonFiniteSideData",
            expectedExceptions = IllegalArgumentException.class,
            description = "Обработка бесконечных и неопределённых значений"
    )
    public void nonFiniteSideTest(double a, double b, double c) {
        TriangleArea.triangleArea(a, b, c);
    }
}
