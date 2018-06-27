package com.example.jpaconverterexample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CodeConverterConfig {

    public static String prefix;

    @Value("${code.converter.prefix}")
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
