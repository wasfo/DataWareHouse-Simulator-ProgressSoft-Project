package org.example.AppRunner;

import org.example.dto.FxDealDto;
import org.example.service.FXDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    private Environment environment;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("========================== Environments ============================");
        String var1 = environment.getProperty("SPRING_DATASOURCE_PASSWORD");
        String var2 = environment.getProperty("SPRING_DATASOURCE_USERNAME");
        String var3 = environment.getProperty("SPRING_DATASOURCE_URL");
        System.out.println("Password: " + var1);
        System.out.println("Username: " + var2);
        System.out.println("Url: " + var3);
        System.out.println("=================================================================");
    }
}