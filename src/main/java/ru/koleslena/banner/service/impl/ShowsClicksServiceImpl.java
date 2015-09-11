package ru.koleslena.banner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.koleslena.banner.repository.ClicksRepository;
import ru.koleslena.banner.repository.ShowsRepository;
import ru.koleslena.banner.service.ShowsClicksService;

import java.util.List;

/**
 * @since 08.09.15.
 */
@Service
public class ShowsClicksServiceImpl implements ShowsClicksService {

    @Autowired
    private ClicksRepository clicksRepository;

    @Autowired
    private ShowsRepository showsRepository;

    @Transactional
    @Override
    public void updateClicks(List<Long> ids, Long count) {
        clicksRepository.addCountClicks(ids, count);
    }

    @Transactional
    @Override
    public void updateShows(List<Long> ids, Long count) {
        showsRepository.addCountShows(ids, count);
    }
}
