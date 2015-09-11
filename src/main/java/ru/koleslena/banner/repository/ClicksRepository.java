package ru.koleslena.banner.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.koleslena.banner.model.Clicks;

import java.util.List;

/**
 * @since 08.09.15.
 */
@Repository
public interface ClicksRepository extends CrudRepository<Clicks, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update Clicks cl set cl.counting = cl.counting + :c where cl.id in :ids")
    void addCountClicks(@Param("ids") List<Long> ids, @Param("c") Long c);
}
