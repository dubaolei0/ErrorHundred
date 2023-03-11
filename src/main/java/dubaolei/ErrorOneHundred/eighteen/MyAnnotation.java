package dubaolei.ErrorOneHundred.eighteen;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author dubaolei
 * @version 1.0.0
 * @ClassName MyAnnotation.java
 * @Description TODO
 * @createTime 2022年12月09日 16:36:00
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String value();
}
