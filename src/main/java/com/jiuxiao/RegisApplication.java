package com.jiuxiao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目主启动类
 * @Author: 悟道九霄
 * @Date: 2022年07月31日 11:35
 * @Version: 1.0.0
 */
@SpringBootApplication
//@MapperScan("com.jiuxiao.mapper")
public class RegisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisApplication.class, args);
    }
}