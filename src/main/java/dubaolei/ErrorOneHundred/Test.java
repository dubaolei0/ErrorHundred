package dubaolei.ErrorOneHundred;

import com.alibaba.druid.support.json.JSONUtils;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author dubaolei
 * @version 1.0.0
 * @ClassName Test.java
 * @Description TODO
 * @createTime 2022年05月29日 10:03:00
 */
public class Test extends Thread{
    public static void main(String[] args) {
//        Interesting interesting = new Interesting();
//        new Thread(()->interesting.compare()).start();
//        new Thread(()->interesting.add()).start();
        ArrayList<String> objects = new ArrayList<>();

        ConcurrentHashMap<String, Long> collect = LongStream.rangeClosed(1, 5)

                .boxed()

                .collect(Collectors.toConcurrentMap(i -> UUID.randomUUID().toString(), Function.identity(),

                        (o1, o2) -> o1, ConcurrentHashMap::new));
        System.out.println(JSONUtils.toJSONString(collect));

    }

}
