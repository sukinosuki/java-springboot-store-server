package com.example.demo.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
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
