package dubaolei.ErrorOneHundred.eighteen;

import java.util.Arrays;

/**
 * @author dubaolei
 * @version 1.0.0
 * @ClassName Child2.java
 * @Description TODO
 * @createTime 2022年12月09日 15:25:00
 */
public class Child1 extends Parent<String> {
    @Override
    public void setValue(String value) {
        System.out.println("Child2.setValue called");
        super.setValue(value);
    }

    public void test(){
        Child1 child1 = new Child1();

        Arrays.stream(child1.getClass().getDeclaredMethods())
                .filter(method -> method.getName().equals("setValue") && !method.isBridge())
                .findFirst().ifPresent(method -> {
                    try {
                        method.invoke(child1, "test");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }
}
