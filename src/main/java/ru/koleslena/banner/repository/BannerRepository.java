package ru.koleslena.banner.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.koleslena.banner.model.Banner;

import java.util.List;

/**
 * @since 07.09.15.
 */
@Repository
public interface BannerRepository extends PagingAndSortingRepository<Banner, Long> {

    Page<Banner> findAll(Pageable pageable);

    Banner findOne(Long id);

    List<Banner> findAll();

}
