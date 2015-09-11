package ru.koleslena.banner.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @since 09.09.15.
 */
public class CollectionsUtils {

    /**
     * Переводит список id в мап с ключами количество вхождения и значениями список id
     *
     * @param list список id
     * @return
     */
    public static Map<Long, List<Long>> getCountListIdMap(List<Long> list) {
        Map<Long, List<Long>> m = new HashMap();
        m.put(1L, new ArrayList<Long>());
        for (Long id: list) {
            Long count = 1L;
            boolean contains = false;
            while (m.containsKey(count) && !contains) {
                if (m.get(count).contains(id)) {
                    contains = true;
                }
                count++;
            }
            if (contains) {
                m.get(count - 1).remove(id);
                if (!m.containsKey(count)) {
                    m.put(count, new ArrayList<Long>());
                }
                m.get(count).add(id);
            } else {
                m.get(1L).add(id);
            }
        }
        return m;
    }
}
