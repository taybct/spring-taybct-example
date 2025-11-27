package io.github.taybct;

import com.alibaba.fastjson2.JSONObject;
import lombok.SneakyThrows;
import org.springframework.core.task.VirtualThreadTaskExecutor;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        System.out.println("Hello, World!");
        VirtualThreadTaskExecutor virtualThreadTaskExecutor = new VirtualThreadTaskExecutor();
        virtualThreadTaskExecutor.execute(() -> {
            System.out.println("I'm in a virtual thread");
        });
        Thread.sleep(1000L);
    }
}