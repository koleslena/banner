package ru.koleslena.banner.service.impl;

import org.springframework.stereotype.Service;
import ru.koleslena.banner.service.RandomService;

import java.util.Random;

/**
 * @since 10.09.15.
 */
@Service
public class RandomServiceImpl implements RandomService {
    @Override
    public int getRandomIntLessThanMax(int maxValue) {
        return new Random().nextInt(maxValue);
    }
}
