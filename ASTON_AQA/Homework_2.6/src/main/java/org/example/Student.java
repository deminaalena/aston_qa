package org.example;
import java.util.ArrayList;

public class Student {
        private String name;
        private String group;
        private int course;
        private ArrayList<Integer> grades;

        public Student(String name, String group, int course) {
            this.name = name;
            this.group = group;
            this.course = course;
            this.grades = new ArrayList<>();
        }

        public String getName() {
                return this.name;
        }

        public String getGroup() {
                return this.group;
        }

        public void setCourse(int course) {
                this.course = course;
        }

        public int getCourse() {
                return this.course;
        }

        public void addGrade(int grade) {
                this.grades.add(grade);
        }

        public double averageGrade()  {
                if (grades.isEmpty()) {
                        return 0;
                }

                double gradesSum = 0;

                for (int grade : grades) {
                        gradesSum += grade;
                }

                return gradesSum / grades.size();
        }

}
