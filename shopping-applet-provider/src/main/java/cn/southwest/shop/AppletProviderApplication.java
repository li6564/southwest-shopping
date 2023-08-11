package cn.southwest.shop;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("cn.southwest.shop.mapper")
public class AppletProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppletProviderApplication.class,args);
    }
}
