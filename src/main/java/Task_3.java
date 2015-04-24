import java.util.LinkedList;

public class Task_3 {

    public class Student {

        private String name;
        private int course;

        Student(String name, int course) {
            this.name = name;
            this.course = course;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCourse() {
            return course;
        }

        public void setCourse(int course) {
            this.course = course;
        }
    }

    public Student createStudent(String name, int course) {
        return new Student(name, course);
    }

    /*
     * Метод, который получает список студентов и номер курса и возвращает список тех студентов, которые обучаются на данном курсе.
     */

    public LinkedList<Student> printStudents(LinkedList<Student> list, int course) {

        LinkedList<Student> result = new LinkedList<Student>();

        for(Student student : list) {
            if(student.getCourse() == course) {
                result.add(student);
            }
        }

        return result;
    }

    /*
     * Метод, реализующий операцию объединения двух множеств.
     */

    public LinkedList<Student> union(LinkedList<Student> list1, LinkedList<Student> list2) {

        LinkedList<Student> result = list1;
        result.addAll(list2);

        return result;
    }

    /*
     * Метод, реализующий операцию пересечения двух множеств.
     */

    public LinkedList<Student> intersect(LinkedList<Student> list1, LinkedList<Student> list2) {

        LinkedList<Student> result = new LinkedList<Student>();

        for(Student student1 : list1) {
            for(Student student2 : list2) {
                if(student1.getCourse() == student2.getCourse()){
                    result.add(student1);
                }
            }
        }

        return result;
    }
}
