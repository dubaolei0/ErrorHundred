package dubaolei.Java8;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.Soundbank;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author dubaolei
 * @version 1.0.0
 * @ClassName ThreadLocal.java
 * @Description TODO
 * @createTime 2022年06月29日 23:06:00
 */
@RestController
public class ThreadLocalTest {

    public static void main(String[] args) {
        List<Student> students = Lists.newArrayList();
        students.add(new Student("1", "张三", LocalDate.of(2009, Month.JANUARY, 1), 12, 12.123));
        students.add(new Student("2", "李四", LocalDate.of(2010, Month.FEBRUARY, 2), 11, 22.123));
        students.add(new Student("3", "王五", LocalDate.of(2011, Month.MARCH, 3), 10, 32.123));
        students.add(new Student("3", "杜六", LocalDate.of(2012, Month.MARCH, 4), 9, 42.123));

        /**
         * 中间操作
         */
        // 升序 (sorted)
        List<Student> collect7 = students.stream().sorted(Comparator.comparing(Student::getAge)).collect(Collectors.toList());
        // 降序 (sorted & reversed)
        List<Student> collect8 = students.stream().sorted(Comparator.comparing(Student::getAge).reversed()).collect(Collectors.toList());
        // 过滤 (filter)
        List<Student> collect9 = students.stream().filter(student -> student.getAge() == 11).collect(Collectors.toList());
        // 映射（map）
        List<String> idList1 = students.stream().map(Student::getId).collect(Collectors.toList());
        // 去重（distinct）
        students.stream().distinct().forEach(System.out::println);


        /**
         * 数据统计
         */
        // 元素数量   counting
        Long count = students.stream().collect(Collectors.counting());
        System.out.println(count);

        // 平均值  averagingDouble、averagingInt、averagingLong
        Double averagingDouble = students.stream().collect(Collectors.averagingDouble(Student::getScore));
        System.out.println(averagingDouble);
            // 精度转换：
        students.stream().collect(Collectors.averagingInt(s -> (int)s.getScore()));
        students.stream().collect(Collectors.averagingLong(s -> (long)s.getScore()));
            // 如果是求学生的平均年龄，因为年龄是int类型，就可以随意使用任何一个函数了：
        students.stream().collect(Collectors.averagingInt(Student::getAge));
        students.stream().collect(Collectors.averagingDouble(Student::getAge));
        students.stream().collect(Collectors.averagingLong(Student::getAge));

        // 和：summingDouble、summingInt、summingLong
        Integer summingInt = students.stream().collect(Collectors.summingInt(Student::getAge));
        System.out.println(summingInt);

        //最大值/最小值元素：maxBy、minBy  这两个函数就是求聚合元素中指定比较器中的最大/最小元素。比如，求年龄最大/最小的Student对象：
        Optional<Student> maxBy = students.stream().collect(Collectors.maxBy(Comparator.comparing(Student::getAge)));
        Student student = maxBy.get();

        /** 聚合分组
         *  1、聚合元素：toList、toSet、toCollection
         *  这几个函数是将聚合之后的元素，重新封装到队列中，然后返回。比如，得到所有Student的 ID 列表，只需要根据需要的结果类型使用不同的方法即可
         */

        // List: [1, 2, 3]
        List<String> idList = students.stream().map(Student::getId).collect(Collectors.toList());
        // Set: [1, 2, 3]
        Set<String> idSet = students.stream().map(Student::getId).collect(Collectors.toSet());
        // TreeSet: [1, 2, 3]
        Collection<String> idTreeSet = students.stream().map(Student::getId).collect(Collectors.toCollection(TreeSet::new));

        /**
         *  2、聚合元素：toMap、toConcurrentMap
         *  这两个方法的作用是将聚合元素，重新组装为Map结构，也就是 k-v 结构。两者用法一样，区别是toMap返回的是Map，toConcurrentMap返回ConcurrentMap
         *  也就是说，toConcurrentMap返回的是线程安全的 Map 结构。
         */
        // 聚合Student的 id
        Map<String, Student> collect = students.stream().collect(Collectors.toMap(Student::getId, Function.identity()));
        // 但是，如果 id 有重复的，会抛出java.lang.IllegalStateException: Duplicate key异常，所以，为了保险起见，我们需要借助toMap另一个重载方法：
        Map<String, Student> collect2 = students.stream().collect(Collectors.toMap(Student::getId, Function.identity(), (x, y) -> x));

        //根据 id 分组的Student的姓名
        Map<String, String> collect1 = students.stream().collect(Collectors.toMap(Student::getId, Student::getName));

        // 相同年龄得分最高的Student对象集合
        Map<Integer, Student> collect3 = students.stream().collect(Collectors.toMap(Student::getAge, Function.identity(), BinaryOperator.maxBy(Comparator.comparing(Student::getAge))));


        /**
         * 3、分组：groupingBy、groupingByConcurrent
         * groupingBy与toMap都是将聚合元素进行分组，区别是，toMap结果是 1:1 的 k-v 结构，groupingBy的结果是 1:n 的 k-v 结构。
         */
        //对Student的年龄分组
        Map<String, List<Student>> collect4 = students.stream().collect(Collectors.groupingBy(Student::getId));
        Map<Integer, Set<Student>> collect5 = students.stream().collect(Collectors.groupingBy(Student::getAge, Collectors.toSet()));
        // 分组：partitioningBy
        // partitioningBy与groupingBy的区别在于，partitioningBy借助Predicate断言，可以将集合元素分为true和false两部分。比如，按照年龄是否大于 11 分组：
        Map<Boolean, List<Student>> collect6 = students.stream().collect(Collectors.partitioningBy(s -> s.getAge() > 11));
        // collect6{false:[{"age":11,"birthday":"2010-02-02","id":"2","name":"李四","score":22.123},{"age":10,"birthday":"2011-03-03","id":"3","name":"王五","score":32.123}],true:[{"age":12,"birthday":"2009-01-01","id":"1","name":"张三","score":12.123}]}
        System.out.println("collect6"+ JSON.toJSONString(collect6));

        /**
         *  forEach迭代
         */

        students.stream().forEach(student1 -> {

        });
        /**
         * reduce
         */
        // 求和
        students.stream().map(Student::getAge).reduce(0,Integer::sum);
        students.stream().map(Student::getAge).reduce(Integer::sum).orElse(0);

        // 最大值最小值
        students.stream().map(Student::getAge).reduce(Integer::min).orElse(0);

    }

}
