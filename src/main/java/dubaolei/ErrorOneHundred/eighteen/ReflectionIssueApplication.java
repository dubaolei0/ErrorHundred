package dubaolei.ErrorOneHundred.eighteen;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

/**
 * @author dubaolei
 * @version 1.0.0
 * @ClassName ReflectionIssueApplication.java
 * @Description TODO
 * @createTime 2022年12月09日 14:27:00
 */
@Slf4j
public class ReflectionIssueApplication {
    private void age(int age) {
        log.info("int age = {}", age);
    }

    private void age(Integer age) {
        log.info("Integer age = {}", age);
    }

    public void test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ReflectionIssueApplication application = new ReflectionIssueApplication();
//        application.age(36);
//        application.age(Integer.valueOf("36"));

        application.getClass().getDeclaredMethod("age", Integer.class).
                invoke(this, Integer.valueOf("36"));
    }
}
