package org.example;

public class Triangle implements Shape {
    private double a;
    private double b;
    private double c;
    private String fillColor;
    private String borderColor;

    public Triangle(double a, double b, double c, String fillColor, String borderColor) {
        if (a <= 0 || !Double.isFinite(a) ||
                b <= 0 || !Double.isFinite(b) ||
                c <= 0 || !Double.isFinite(c) ||
                a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Стороны треугольника заданы некорректно.");
        }
        if (fillColor == null || fillColor.isBlank() ||
                borderColor == null || borderColor.isBlank()) {
            throw new IllegalArgumentException("Цвета не должны быть пустыми.");
        }

        this.a = a;
        this.b = b;
        this.c = c;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    public double calculateArea() {
        double p = (a + b + c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

    public double calculatePerimeter() {
        return calculatePerimeter(a, b ,c);
    }

    public String getFillColor() {
        return fillColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

}
