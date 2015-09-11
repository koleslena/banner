package ru.koleslena.banner.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.koleslena.banner.model.Shows;

import java.util.List;

/**
 * @since 08.09.15.
 */
@Repository
public interface ShowsRepository extends CrudRepository<Shows, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update Shows shows set shows.counting = shows.counting + :c where shows.id in :ids")
    void addCountShows(@Param("ids") List<Long> ids, @Param("c") Long c);
}
