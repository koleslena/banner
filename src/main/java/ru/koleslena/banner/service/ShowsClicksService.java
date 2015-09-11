package ru.koleslena.banner.service;

import java.util.List;

/**
 * @since 08.09.15.
 */
public interface ShowsClicksService {

    void updateClicks(List<Long> ids, Long count);

    void updateShows(List<Long> ids, Long count);
}
