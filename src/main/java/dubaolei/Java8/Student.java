package dubaolei.Java8;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author dubaolei
 * @version 1.0.0
 * @ClassName Student.java
 * @Description TODO
 * @createTime 2022年06月30日 16:50:00
 */
@Data
@AllArgsConstructor
public class Student {
    private String id;
    private String name;
    private LocalDate birthday;
    private int age;
    private double score;
}
