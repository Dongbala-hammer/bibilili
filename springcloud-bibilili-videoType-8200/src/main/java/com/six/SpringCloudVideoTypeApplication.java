package com.six;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author ZhouJinDong
 * @Date 2021/1/4
 */
@SpringBootApplication
@EnableDiscoveryClient
<<<<<<< Updated upstream:bilibili-live-8081/src/main/java/com/six/BiLiLiveApplication.java
public class BiLiLiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiLiLiveApplication.class);
=======
@EnableFeignClients
@EnableHystrix
public class SpringCloudVideoTypeApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudVideoTypeApplication.class);
>>>>>>> Stashed changes:springcloud-bibilili-videoType-8200/src/main/java/com/six/SpringCloudVideoTypeApplication.java
    }
}
