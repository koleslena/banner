package ru.koleslena.banner.view;

/**
 * @since 11.09.15.
 */
public class BannerFile {

    private Long id;
    private String name;
    private String ref;
    private String img;

    public BannerFile(Long id, String name, String ref, byte[] content) {
        this.id = id;
        this.name = name;
        this.ref = ref;
        this.img = new sun.misc.BASE64Encoder().encode(content);
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
