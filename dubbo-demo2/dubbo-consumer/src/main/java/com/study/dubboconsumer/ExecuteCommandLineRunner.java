package com.study.dubboconsumer;

import com.study.dubboapi.api.ServiceApi;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Order(2)
public class ExecuteCommandLineRunner implements CommandLineRunner {

    @Reference
    private ServiceApi serviceApi;

    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> {
            ExecutorService pool = Executors.newFixedThreadPool(24);

            Runnable task1 = () -> {
                serviceApi.methodA();
            };
            Runnable task2 = () -> {
                serviceApi.methodB();
            };
            Runnable task3 = () -> {
                serviceApi.methodC();
            };

            while (true) {
                Future<?> futureA = pool.submit(task1);
                Future<?> futureB = pool.submit(task2);
                Future<?> futureC = pool.submit(task3);
                try {
                    futureA.get();
                    futureB.get();
                    futureC.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
