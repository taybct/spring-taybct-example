package io.github.taybct.lab.flux;

import io.github.taybct.tool.core.auto.ControllerRegisterAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, ControllerRegisterAutoConfigure.class})
public class FluxApplication {
    public static void main(String[] args) {
        SpringApplication.run(FluxApplication.class);
    }
}
