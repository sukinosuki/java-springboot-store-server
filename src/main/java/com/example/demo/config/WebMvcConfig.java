package com.example.demo.config;

import com.example.demo.component.converter.LabelValueEnumConverterFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
//        WebMvcConfigurer.super.addFormatters(registry);

        // TIP: 枚举转换只对@RequestParam有效
        registry.addConverterFactory(new LabelValueEnumConverterFactory());
    }
}
