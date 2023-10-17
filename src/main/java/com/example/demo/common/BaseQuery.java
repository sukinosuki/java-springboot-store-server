package com.example.demo.common;

public abstract class BaseQuery {

    public Long page;

    public Long size;

    public Long getSize() {
        return this.getSafeSize();
    }

    public Long getSafeOffset() {
        return (getSafePage() - 1) * getSafeSize();
    }

    public Long getSafePage() {
        return (page == null || page <= 0) ? 1 : page;
    }

    public Long getSafeSize() {
        return (size == null || size <= 0) ? 10 : size;
    }

}
