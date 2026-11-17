package org.example;

public class TriangleArea {
    public static double triangleArea(double a, double b, double c) {
        if (a <= 0 || !Double.isFinite(a) ||
                b <= 0 || !Double.isFinite(b) ||
                c <= 0 || !Double.isFinite(c) ||
                a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Стороны треугольника заданы некорректно.");
        }
        double p = (a + b + c)/2;
        return Math.sqrt(p*(p - a)*(p - b)*(p - c));
    }

}
