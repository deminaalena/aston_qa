package org.example;

public class Rectangle implements Shape {
    private double width;
    private double height;
    private String fillColor;
    private String borderColor;

    public Rectangle(double width, double height, String fillColor, String borderColor) {
        if (width <= 0 || !Double.isFinite(width) ||
                height <= 0 || !Double.isFinite(height)) {
            throw new IllegalArgumentException("Стороны прямоугольника должны быть положительными числами.");
        }
        if (fillColor == null || fillColor.isBlank() ||
                borderColor == null || borderColor.isBlank()) {
            throw new IllegalArgumentException("Цвета не должны быть пустыми.");
        }

        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    public double calculateArea() {
        return width * height;
    }

    public double calculatePerimeter() {
        return calculatePerimeter(width, height);
    }

    public String getFillColor() {
        return fillColor;
    }

    public String getBorderColor() {
        return borderColor;
    }
}
