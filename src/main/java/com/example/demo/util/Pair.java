package com.example.demo.util;

import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public final class Pair<S, T> {
    public final S first;
    public final T second;

    private Pair(S first, T second) {
        this.first = first;
        this.second = second;
    }

    public static <S, T> Pair<S, T> of(S first, T second) {
        return new Pair(first, second);
    }

    public S getFirst() {
        return this.first;
    }

    public T getSecond() {
        return this.second;
    }

    public static <S, T> Collector<Pair<S, T>, ?, Map<S, T>> toMap() {
        return Collectors.toMap(Pair::getFirst, Pair::getSecond);
    }

//    public boolean equals(@Nullable Object o) {
//        if (this == o) {
//            return true;
//        } else if (!(o instanceof org.springframework.data.util.Pair)) {
//            return false;
//        } else {
//            org.springframework.data.util.Pair<?, ?> pair = (org.springframework.data.util.Pair)o;
//            return !ObjectUtils.nullSafeEquals(this.first, pair.first) ? false : ObjectUtils.nullSafeEquals(this.second, pair.second);
//        }
//    }

    public int hashCode() {
        int result = ObjectUtils.nullSafeHashCode(this.first);
        result = 31 * result + ObjectUtils.nullSafeHashCode(this.second);
        return result;
    }

    public String toString() {
        return String.format("%s->%s", this.first, this.second);
    }
}

