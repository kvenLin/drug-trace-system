package com.uchain.drugtracesystem;

import com.uchain.drugtracesystem.security.OptionFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Configuration
public class DrugTraceSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrugTraceSystemApplication.class, args);
    }

    @Bean
    public OptionFilter getFilter(){
        return new OptionFilter();
    }
}
