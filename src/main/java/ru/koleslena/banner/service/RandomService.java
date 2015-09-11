package ru.koleslena.banner.service;

/**
 * @since 10.09.15.
 */
public interface RandomService {

    /**
     * return random int value less than  maxValue
     *
     * @param maxValue
     * @return
     */
    int getRandomIntLessThanMax(int maxValue);
}
