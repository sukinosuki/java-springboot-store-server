package com.example.demo.common;

import java.util.List;

public interface BaseTree<T> {

    Long getId();

    Long getPid();

    List<T> getChildren();

    void setChildren(List<T> list);
}
