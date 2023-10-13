package com.example.demo.component.converter;

import com.example.demo.common.enums.ILabelValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class LabelValueEnumConverter<T extends ILabelValue<String>> implements Converter<String, T> {

    private final Map<String, T> enumMap = new HashMap<>();

    public LabelValueEnumConverter(Class<T> enumType) {

        for (T x : enumType.getEnumConstants()) {
            enumMap.put(x.getKey(), x);
        }

        log.info(String.format("enumMap: %s", enumMap));
    }

    @Override
    public T convert(String source) {
        T t = enumMap.get(source);

        log.info(String.format("转换 LabelValue, %s", t));
//        if (t == null) {
//            throw AppException.actionFailError(String.format("enum转换失败, value: %s", source));
//        }

        return t;
    }
}

