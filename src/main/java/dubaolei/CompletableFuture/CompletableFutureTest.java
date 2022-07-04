package dubaolei.CompletableFuture;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;


/**
 * @author dubaolei
 * @version 1.0.0
 * @ClassName CompletableFutureTest.java
 * @Description TODO
 * @createTime 2022年07月02日 10:14:00
 */
@RestController
@RequestMapping("/CompletableFutureTest")
public class CompletableFutureTest {

    @GetMapping("/test1")
    public String completableFuture() {
        //任务1：洗水壶->烧开水
        CompletableFuture<Void> f1 =
                CompletableFuture.runAsync(() -> {
                    System.out.println("T1:洗水壶..."+Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("T1:烧开水..."+Thread.currentThread().getName());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

        //任务2：洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("T2:洗茶壶..."+Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("T2:洗茶杯..."+Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("T2:拿茶叶..."+Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "龙井";
                });

        //任务3：任务1和任务2完成后执行：泡茶
        CompletableFuture<String> f3 =
                f1.thenCombine(f2, (__, tf) -> {
                    System.out.println("T1:拿到茶叶:" + tf);
                    System.out.println("T1:泡茶..."+Thread.currentThread().getName());
                    return "上茶:" + tf;
                });
        
        //等待任务3执行结果
       return f3.join();
    }
}
