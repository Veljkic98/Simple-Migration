package com.dummydata.config;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class BeanConfig {

    @Bean
    public FakeValuesService generateFakeValuesService() {
        return new FakeValuesService(new Locale("en-US"), new RandomService());
    }

}
