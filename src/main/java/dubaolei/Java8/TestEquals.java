package dubaolei.Java8;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dubaolei
 * @version 1.0.0
 * @ClassName TestEquals.java
 * @Description 11
 * @createTime 2022年11月10日 18:09:00
 */
public class TestEquals {

    public static void main(String[] args) {
        List<Student> objects = null;
//        Student student1= new Student();
//        student1.setAge(1);
//        student1.setBirthday(null);
//        objects.add(student1);
//
//        Student student2= new Student();
//        student2.setAge(2);
//        student2.setBirthday(null);
//        objects.add(student2);
        List<Student> cateLogCodeList = objects.stream().filter(s -> s != null).collect(Collectors.toList());
        System.out.println("1");


    }


}
