package ru.koleslena.banner.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import ru.koleslena.banner.model.Banner;
import ru.koleslena.banner.view.BannerFile;
import ru.koleslena.banner.view.BannerShort;
import ru.koleslena.banner.view.BannerView;
import ru.koleslena.banner.view.BannerViewStatistics;

/**
 * @since 07.09.15.
 */
public interface BannerService {

    BannerViewStatistics getById(Long id);

    BannerView getNext(Long id);

    Page<BannerShort> getAll(Pageable pageable);

    @Transactional
    BannerFile saveBanner(String name, String ref, byte[] content);
}
