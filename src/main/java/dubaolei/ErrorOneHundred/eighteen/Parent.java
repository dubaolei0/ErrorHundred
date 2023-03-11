package dubaolei.ErrorOneHundred.eighteen;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dubaolei
 * @version 1.0.0
 * @ClassName Parent.java
 * @Description TODO
 * @createTime 2022年12月09日 15:24:00
 */
public class Parent<T> {

    //用于记录value更新的次数，模拟日志记录的逻辑
    AtomicInteger updateCount = new AtomicInteger();

    private T value;

    //重写toString，输出值和值更新次数
    @Override
    public String toString() {
        return String.format("value: %s updateCount: %d", value, updateCount.get());
    }

    //设置值
    public void setValue(T value) {
        this.value = value;
        updateCount.incrementAndGet();
    }
}
