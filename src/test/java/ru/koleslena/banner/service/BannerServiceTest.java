package ru.koleslena.banner.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.Assert;
import ru.koleslena.banner.cache.BannersHolder;
import ru.koleslena.banner.config.AppTestConfig;
import ru.koleslena.banner.model.Banner;

import static org.mockito.Mockito.when;

/**
 * @since 11.09.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfig.class})
public class BannerServiceTest {

    private RandomService randomServiceMock = Mockito.mock(RandomService.class);

    @Autowired
    private BannersHolder bannersHolder;

    @Before
    public void setup() {
        Mockito.reset(randomServiceMock);
        ReflectionTestUtils.setField(bannersHolder, "randomService", randomServiceMock);
    }

    @Test
    public void validateGetNextBanner() throws Exception {

        int expectedValue = 27;
        String format = "name %d";

        when(randomServiceMock.getRandomIntLessThanMax(98)).thenReturn(expectedValue);

        Banner next = bannersHolder.getNext(1L);

        Assert.notNull(next);
        Assert.isTrue(next.getId().equals(new Long(expectedValue + 2)));
        Assert.isTrue(next.getName().equals(String.format(format, expectedValue + 2)));

        when(randomServiceMock.getRandomIntLessThanMax(98)).thenReturn(expectedValue);

        next = bannersHolder.getNext(50L);

        Assert.notNull(next);
        Assert.isTrue(next.getId().equals(new Long(expectedValue + 1)));
        Assert.isTrue(next.getName().equals(String.format(format, expectedValue + 1)));
    }
}
