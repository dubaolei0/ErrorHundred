package dubaolei.ErrorOneHundred;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author dubaolei
 * @version 1.0.0
 * @ClassName ThreadPoolExecutor.java
 * @Description 线程池
 * @createTime 2022年05月29日 16:15:00
 */
@RestController
@RequestMapping("/threadPoolExecutor")
public class ThreadPoolExecutorTest {
    private static final Logger log = LoggerFactory.getLogger(ThreadPoolExecutorTest.class);

    @GetMapping("/right")

    public int right() throws InterruptedException {

        //使用一个计数器跟踪完成的任务数

        AtomicInteger atomicInteger = new AtomicInteger();

        //创建一个具有2个核心线程、5个最大线程，使用容量为10的ArrayBlockingQueue阻塞队列作为工作队列的线程池，使用默认的AbortPolicy拒绝策略

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(

                2, 5,

                5, TimeUnit.SECONDS,

                new ArrayBlockingQueue<>(10),

                new ThreadFactoryBuilder().setNameFormat("demo-threadpool-%d").build(),

                new ThreadPoolExecutor.AbortPolicy());

        printStats(threadPool);

        //每隔1秒提交一次，一共提交20次任务

        IntStream.rangeClosed(1, 20).forEach(i -> {

            try {

                TimeUnit.SECONDS.sleep(1);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

            int id = atomicInteger.incrementAndGet();

            try {

                threadPool.submit(() -> {

                    log.info("{} started", id);

                    //每个任务耗时10秒

                    try {

                        TimeUnit.SECONDS.sleep(10);

                    } catch (InterruptedException e) {

                    }

                    log.info("{} finished", id);

                });

            } catch (Exception ex) {

                //提交出现异常的话，打印出错信息并为计数器减一

                log.error("error submitting task {}", id, ex);

                atomicInteger.decrementAndGet();

            }

        });

        TimeUnit.SECONDS.sleep(60);

        return atomicInteger.intValue();

    }
    private void printStats(ThreadPoolExecutor threadPool) {

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {

            log.info("=========================");
            // 线程池的线程数量
            log.info("Pool Size: {}", threadPool.getPoolSize());
            // 活跃线程数
            log.info("Active Threads: {}", threadPool.getActiveCount());
            // 队列中完成任务的数量
            log.info("Number of Tasks Completed: {}", threadPool.getCompletedTaskCount());
            // 队列中还有多少积压任务
            log.info("Number of Tasks in Queue: {}", threadPool.getQueue().size());

            log.info("=========================");

        }, 0, 1, TimeUnit.SECONDS);

    }

}
