package org.example;

public class Circle implements Shape {
    private double radius;
    private String fillColor;
    private String borderColor;

    public Circle(double radius, String fillColor, String borderColor) {
        if (radius <= 0 || !Double.isFinite(radius)) {
            throw new IllegalArgumentException("Радиус должен быть положительным числом.");
        }
        if (fillColor == null || fillColor.isBlank() ||
                borderColor == null || borderColor.isBlank()) {
            throw new IllegalArgumentException("Цвета не должны быть пустыми.");
        }

        this.radius = radius;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    public double calculatePerimeter() {
        return calculatePerimeter(radius);
    }

    public String getFillColor() {
        return fillColor;
    }

    public String getBorderColor() {
        return borderColor;
    }
}
