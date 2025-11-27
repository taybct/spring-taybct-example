package io.github.taybct.lab.elk;

import io.github.taybct.tool.cloud.annotation.TayBctCloudConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 启动类
 *
 * @author xijieyin
 */
@TayBctCloudConfig
@SpringBootApplication
@Slf4j
@EnableCaching
public class ESApplication {

    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        SpringApplication sa = new SpringApplication(ESApplication.class);
        sa.run(args);
        long time = System.currentTimeMillis() - beginTime;
        log.info("\033[40;32;0m" + "启动成功！耗时 : " + time + "\t\t\t\033[0m");
    }

}
