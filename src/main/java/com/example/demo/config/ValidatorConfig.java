package com.example.demo.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.validation.Validation;
import javax.validation.Validator;

@ConfigurationProperties
public class ValidatorConfig {

    @Bean
    Validator validator(AutowireCapableBeanFactory springFactory) {
        return Validation
                .byProvider(HibernateValidator.class).configure()
                .failFast(true)
                .buildValidatorFactory()
                .getValidator();
    }
}
