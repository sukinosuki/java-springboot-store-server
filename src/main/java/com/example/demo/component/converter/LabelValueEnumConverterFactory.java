package com.example.demo.component.converter;

import com.example.demo.common.enums.ILabelValue;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.HashMap;
import java.util.Map;

public class LabelValueEnumConverterFactory implements ConverterFactory<String, ILabelValue<String>> {
    private static final Map<Class<?>, Converter<?, ?>> CONVERTERS = new HashMap<>();

    @Override
    public <T extends ILabelValue<String>> Converter<String, T> getConverter(Class<T> targetType) {

        Converter<?, ?> converter = CONVERTERS.get(targetType);

        if (converter == null) {
            converter = new LabelValueEnumConverter<>(targetType);
            CONVERTERS.put(targetType, converter);
        }

        return (Converter<String, T>) converter;
    }
}
