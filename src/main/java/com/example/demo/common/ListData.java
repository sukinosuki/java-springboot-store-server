package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ListData<T> {

    public Long count;

    public List<T> data;

    public static <T> ListData<T> of  (List<T> data ,Long count) {

        return new ListData<>(count, data);
    }
}
