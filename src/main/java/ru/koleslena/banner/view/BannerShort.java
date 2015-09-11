package ru.koleslena.banner.view;

/**
 * @since 11.09.15.
 */
public class BannerShort {

    private Long id;
    private String name;
    private String ref;

    public BannerShort(Long id, String name, String ref) {
        this.id = id;
        this.name = name;
        this.ref = ref;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
