package ru.koleslena.banner.utils;

import org.junit.Assert;
import ru.koleslena.banner.utils.CollectionsUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @since 09.09.15.
 */
public class CollectionsUtilsTest {

    @org.junit.Test
    public void testGetCountListIdMap() throws Exception {

        List<Long> list = new ArrayList<>(Arrays.asList(1L, 1L, 1L, 2L));

        Map<Long, List<Long>> countListIdMap = CollectionsUtils.getCountListIdMap(list);

        Assert.assertEquals(countListIdMap.get(1L).size(), 1);
        Assert.assertEquals(countListIdMap.get(2L).size(), 0);
        Assert.assertEquals(countListIdMap.get(3L).size(), 1);
    }
}