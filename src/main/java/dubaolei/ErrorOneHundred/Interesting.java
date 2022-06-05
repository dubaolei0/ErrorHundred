package dubaolei.ErrorOneHundred;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dubaolei
 * @version 1.0.0
 * @ClassName Interesting.java
 * @Description TODO
 * @createTime 2022年05月29日 10:02:00
 */
public class Interesting {
    private static final Logger log = LoggerFactory.getLogger(Interesting.class);

    volatile int a = 1;
    volatile int b = 1;

    public synchronized void add() {

        log.info("add start");

        for (int i = 0; i < 10000; i++) {
            a++;
            b++;
        }
        log.info("add done");
    }

    public void compare() {
        log.info("compare start");
        for (int i = 0; i < 10000; i++) {
            //a始终等于b吗？
            if (a < b) {
                log.info("a:{},b:{},{}", a, b, a > b);
                //最后的a>b应该始终是false吗？
            }
        }
        log.info("compare done");
    }

}
