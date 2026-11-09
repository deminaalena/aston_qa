package org.example;

public class Main {
    public static void main(String[] args) {
        String[][] array1 = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };
        String[][] array2 = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "String", "12"},
                {"13", "14", "15", "16"}
        };
        String[][] array3 = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10"},
                {"13", "14", "15", "16"}
        };

        try {
            int result = calculateArraySum(array1);
            System.out.println("Сумма элементов массива: " + result);
        } catch (MyArrayDataException | MyArraySizeException e) {
            System.out.println(e.getMessage());
        }

        try {
            int result2 = calculateArraySum(array2);
            System.out.println("Сумма элементов массива: " + result2);
        } catch (MyArrayDataException | MyArraySizeException e) {
            System.out.println(e.getMessage());
        }

        try {
            int result3 = calculateArraySum(array3);
            System.out.println("Сумма элементов массива: " + result3);
        } catch (MyArrayDataException | MyArraySizeException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(array1[5][5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

    }

    public static int calculateArraySum(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4) {
            throw new MyArraySizeException("Неверный размер массива.");
        }
        for (int i = 0; i < 4; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException("Неверный размер массива.");
            }
        }
        int arraySum = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    arraySum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Ошибка в ячейке [" + i + "][" + j + "]: некорректный формат данных.");
                }
            }
        }

        return arraySum;
    }
}