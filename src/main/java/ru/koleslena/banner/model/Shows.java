package ru.koleslena.banner.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @since 08.09.15.
 */
@Entity
public class Shows {
    @Id
    private Long bannerId;
    @Column
    private  Long counting;

    public Long getBannerId() {
        return bannerId;
    }

    public void setBannerId(Long bannerId) {
        this.bannerId = bannerId;
    }

    public Long getCounting() {
        return counting;
    }

    public void setCounting(Long counting) {
        this.counting = counting;
    }
}
