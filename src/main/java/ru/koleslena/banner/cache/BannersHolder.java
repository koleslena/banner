package ru.koleslena.banner.cache;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.koleslena.banner.repository.BannerRepository;
import ru.koleslena.banner.model.Banner;
import ru.koleslena.banner.service.RandomService;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 08.09.15.
 */
@Service
public class BannersHolder {
    private List<Banner> banners = new ArrayList<>();

    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private RandomService randomService;

    public Banner getNext(final Long id) {

        if (banners.isEmpty()) {
            synchronized (banners) {
                if (banners.isEmpty()) {
                    loadBanners();
                }
            }
        }

        List<Banner> bannerList = banners;

        if (bannerList.isEmpty()) {
            return null;
        }

        if (bannerList.size() == 1 || id == null) {
            return bannerList.get(0);
        }

        Predicate<Banner> predicate = new Predicate<Banner>() {
                @Override
                public boolean apply(Banner input) {
                    return !input.getId().equals(id);
                }
            };

        List<Banner> list = FluentIterable.from(bannerList).filter(predicate).toList();

        int index = randomService.getRandomIntLessThanMax(list.size());

        return list.get(index);
    }

    public void loadBanners() {
        List<Banner> bannerList = bannerRepository.findAll();

        synchronized (banners) {
            banners = bannerList;
        }
    }
}
