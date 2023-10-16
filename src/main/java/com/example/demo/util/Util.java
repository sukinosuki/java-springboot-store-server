package com.example.demo.util;

import com.example.demo.common.BaseTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {

    public static <T extends BaseTree> List<T> listToTree(List<T> list) {
        Map<String, List<T>> zoneByParentIdMap = new HashMap<>();

        for (T zone : list) {
            String key = zone.getPid().toString();
            if (zoneByParentIdMap.containsKey(key)) {
                List<T> values = zoneByParentIdMap.get(key);
                values.add(zone);
            } else {
                ArrayList<T> values = new ArrayList<T>();
                values.add(zone);

                zoneByParentIdMap.put(key, values);
            }
        }

        for (T zone : list) {

            List<T> children = zoneByParentIdMap.get(zone.getId().toString());
            if (children == null) {
                children = new ArrayList<>();
            }
            zone.setChildren(children);

        }

        return list.stream().filter(it -> it.getPid() == 0).toList();
    }
}
