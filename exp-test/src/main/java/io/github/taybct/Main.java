package io.github.taybct;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("Main thread started.");

        // 创建一个虚拟线程池，每个任务将由一个新的虚拟线程执行
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {

            // 提交多个任务
            for (int i = 0; i < 5; i++) {
                int taskNumber = i;
                executor.submit(() -> { // executor.submit() 提交任务时，这个执行器会为每个任务创建一个新的虚拟线程来执行它
                    System.out.println("Executing task " + taskNumber + " in Virtual Thread.");
                    System.out.println("Thread: " + Thread.currentThread().getName());
                    try {
                        // 模拟一些工作
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.err.println("Task " + taskNumber + " interrupted.");
                    }
                    System.out.println("Task " + taskNumber + " completed.");
                });
            }
        } // try-with-resources 会自动关闭 ExecutorService

        System.out.println("All tasks submitted and ExecutorService closed.");
        System.out.println("Main thread finished.");
    }
}