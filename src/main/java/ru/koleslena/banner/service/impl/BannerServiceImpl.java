package ru.koleslena.banner.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.koleslena.banner.cache.BannersHolder;
import ru.koleslena.banner.model.Banner;
import ru.koleslena.banner.model.Clicks;
import ru.koleslena.banner.model.Shows;
import ru.koleslena.banner.repository.BannerRepository;
import ru.koleslena.banner.repository.ClicksRepository;
import ru.koleslena.banner.repository.ShowsRepository;
import ru.koleslena.banner.service.BannerService;
import ru.koleslena.banner.view.BannerFile;
import ru.koleslena.banner.view.BannerShort;
import ru.koleslena.banner.view.BannerView;
import ru.koleslena.banner.view.BannerViewStatistics;

/**
 * @since 07.09.15.
 */
@Service
public class BannerServiceImpl implements BannerService {

    private static Logger logger = LoggerFactory.getLogger(BannerServiceImpl.class);

    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private ShowsRepository showsRepository;

    @Autowired
    private ClicksRepository clicksRepository;
    @Autowired
    private BannersHolder bannersHolder;

    @Override
    @Transactional(readOnly = true)
    public BannerViewStatistics getById(Long id) {
        Banner banner = bannerRepository.findOne(id);
        if (banner != null) {
            Clicks clicks1 = clicksRepository.findOne(banner.getId());
            Shows shows1 = showsRepository.findOne(banner.getId());
            Long clicks = clicks1 == null ? 0L : clicks1.getCounting();
            Long shows = shows1 == null ? 0L : shows1.getCounting();
            return createBannerViewStatistics(banner, clicks, shows);
        }
        return null;
    }

    private BannerViewStatistics createBannerViewStatistics(Banner banner, Long clicks, Long shows) {
        BannerViewStatistics bannerViewStatistics = new BannerViewStatistics();
        bannerViewStatistics.setId(banner.getId());
        bannerViewStatistics.setName(banner.getName());
        bannerViewStatistics.setRef(banner.getRef());
        bannerViewStatistics.setContent(banner.getContent());

        bannerViewStatistics.setClicks(clicks);
        bannerViewStatistics.setShows(shows);
        return bannerViewStatistics;
    }

    @Override
    @Transactional(readOnly = true)
    public BannerView getNext(Long id) {
        Banner next = bannersHolder.getNext(id);
        if (next != null) {
            return createBannerView(next);
        }
        return null;
    }

    private BannerView createBannerView(Banner next) {
        return new BannerView(next.getId(), next.getName(), next.getRef(), next.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BannerShort> getAll(Pageable pageable) {
        Page<Banner> all = bannerRepository.findAll(pageable);

        Page<BannerShort> map = all.map(new Converter<Banner, BannerShort>() {
            @Override
            public BannerShort convert(Banner source) {
                return new BannerShort(source.getId(), source.getName(), source.getRef());
            }
        });

        return map;
    }

    @Override
    @Transactional
    public BannerFile saveBanner(String name, String ref, byte[] content) {
        Banner banner = new Banner();
        banner.setName(name);
        banner.setRef(ref);
        banner.setContent(content);

        Banner save = bannerRepository.save(banner);

        //File file = getFile(save.getContent());

        return new BannerFile(save.getId(), save.getName(), save.getRef(), save.getContent());
    }

    /*private File getFile(byte[] content) {
        FileInputStream fileInputStream=null;

        File file = null;
        try {
            file = File.createTempFile("tmp", "file");

            fileInputStream = new FileInputStream(file);
            fileInputStream.read(content);

        }catch(Exception e){
            logger.error(e.getLocalizedMessage());
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                logger.error(e.getLocalizedMessage());
            }
        }

        return file;
    }*/
}
