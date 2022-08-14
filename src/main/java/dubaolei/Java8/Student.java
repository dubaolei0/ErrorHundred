package dubaolei.Java8;


import java.time.LocalDate;

/**
 * @author dubaolei
 * @version 1.0.0
 * @ClassName Student.java
 * @Description 学生类
 * @createTime 2022年06月30日 16:50:00
 */

public class Student {
    private String id;
    private String name;
    private LocalDate birthday;
    private int age;
    private double score;

    public Student(String id, String name, LocalDate birthday, int age, double score) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.age = age;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
