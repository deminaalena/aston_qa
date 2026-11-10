package org.example;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        System.out.println("ЗАДАНИЕ 1:");

        Set<Student> students = new HashSet<>();

        Student anna = new Student("Анна", "А-101", 1);
        anna.addGrade(5);
        anna.addGrade(5);
        anna.addGrade(4);

        Student boris = new Student("Борис", "А-101", 1);
        boris.addGrade(2);
        boris.addGrade(3);
        boris.addGrade(2);

        Student viktoria = new Student("Виктория", "Б-202", 2);
        viktoria.addGrade(3);
        viktoria.addGrade(3);
        viktoria.addGrade(3);

        Student gleb = new Student("Глеб", "Б-202", 2);
        gleb.addGrade(4);
        gleb.addGrade(2);
        gleb.addGrade(3);

        Student darya = new Student("Дарья", "В-303", 3);
        darya.addGrade(2);
        darya.addGrade(2);
        darya.addGrade(1);

        Student egor = new Student("Егор", "В-303", 3);
        egor.addGrade(5);
        egor.addGrade(4);
        egor.addGrade(5);

        Student zhanna = new Student("Жанна", "А-101", 1);

        Student kirill = new Student("Кирилл", "Г-404", 4);
        kirill.addGrade(3);
        kirill.addGrade(4);
        kirill.addGrade(4);

        students.add(anna);
        students.add(boris);
        students.add(viktoria);
        students.add(gleb);
        students.add(darya);
        students.add(egor);
        students.add(zhanna);
        students.add(kirill);

        StudentUtils.printStudents(students, 1);
        StudentUtils.printStudents(students, 2);
        StudentUtils.printStudents(students, 3);
        StudentUtils.printStudents(students, 4);

        System.out.println("\nУдаление студентов со средним баллом < 3:");
        StudentUtils.removeLowPerformers(students);
        StudentUtils.printStudents(students, 1);
        StudentUtils.printStudents(students, 2);
        StudentUtils.printStudents(students, 3);
        StudentUtils.printStudents(students, 4);

        System.out.println("\nПеревод на следующий курс:");
        StudentUtils.promoteStudents(students);
        StudentUtils.printStudents(students, 2);
        StudentUtils.printStudents(students, 3);
        StudentUtils.printStudents(students, 4);
        StudentUtils.printStudents(students, 5);

        System.out.println("___________");
        System.out.println("ЗАДАНИЕ 2:");

        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Иванов", "111");
        phoneBook.add("Иванов", "222");
        phoneBook.add("Петров", "333");
        phoneBook.add("Иванов", "111");

        System.out.println("Иванов: " + phoneBook.get("Иванов"));
        System.out.println("Петров: " + phoneBook.get("Петров"));
    }
}