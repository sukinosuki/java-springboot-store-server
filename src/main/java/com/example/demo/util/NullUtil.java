package com.example.demo.util;

public class NullUtil {

    public static String null2String(String str) {
        return str == null ? "" : str;
    }

    public static Integer null2Int(Integer i) {
        return i == null ? 0 : i;
    }

    public static Long null2Long(Long l){
        return l == null ? 0 : l;
    }
}
