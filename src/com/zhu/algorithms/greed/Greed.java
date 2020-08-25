package com.zhu.algorithms.greed;

import java.util.*;

/**
 * @author zpm
 * @version 1.0
 */
public class Greed {
    static Map<String, Set<String>> map = new HashMap<>(16);

    static {
        Set<String> set1 = new HashSet<>(16);
        Collections.addAll(set1, "北京", "上海", "天津");

        Set<String> set2 = new HashSet<>(16);
        Collections.addAll(set2, "广州", "北京", "深圳");

        Set<String> set3 = new HashSet<>(16);
        Collections.addAll(set3, "成都", "上海", "杭州");

        Set<String> set4 = new HashSet<>(16);
        Collections.addAll(set4, "上海", "天津");

        Set<String> set5 = new HashSet<>(16);
        Collections.addAll(set5, "杭州", "大连");

        map.put("k1", set1);
        map.put("k2", set2);
        map.put("k3", set3);
        map.put("k4", set4);
        map.put("k5", set5);
    }

    /**
     * 返回所有的地区
     *
     * @return
     */
    public static Set<String> getAllAreas() {
        Set<String> set = new HashSet<>(16);
        map.forEach((key, value) -> set.addAll(value));
        return set;
    }

    public static void main(String[] args) {
        greed();
    }

    public static void greed() {
        Set<String> allAreas = getAllAreas();
        System.out.println(allAreas);
        //存放选择的电台的集合
        List<String> selectedList = new ArrayList<>();

        String maxKey = null;

        while (allAreas.size() != 0) {
            for (String key : map.keySet()) {
                Set<String> areas = map.get(key);
                //取出交集放到前面的集合中
                areas.retainAll(allAreas);
                if (areas.size() > 0) {
                    if (maxKey == null || areas.size() > map.get(maxKey).size()) {
                        maxKey = key;
                    }
                }
            }
            if (maxKey != null) {
                selectedList.add(maxKey);
                allAreas.removeAll(map.get(maxKey));
                maxKey = null;
            }
        }
        System.out.println(selectedList);
    }
}
