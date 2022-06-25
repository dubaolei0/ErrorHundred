package dubaolei.CompletableFuture;

import java.util.concurrent.*;

/**
 * @author dubaolei
 * @version 1.0.0
 * @ClassName CompletableFutureSupplyAsync.java
 * @Description CompletableFutureSupplyAsync
 * @createTime 2022年06月25日 18:02:00
 */
public class CompletableFutureSupplyAsync {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName()+"进行一连串操作1....");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });

        future1.thenRun(() -> {
            System.out.println(Thread.currentThread().getName()+"进行一连串操作2....");
            countDownLatch.countDown();
        });
        countDownLatch.await();
        System.out.println(1);

    }

}
