package ru.koleslena.banner.view;

/**
 * @since 10.09.15.
 */
public class BannerView {

    public static final String URL_CLICK = "/click/";
    public static final String SHOW_URL = "/show/";

    private Long id;
    private String name;
    private String ref;
    private byte[] content;

    public BannerView(Long id, String name, String ref, byte[] content) {
        this.id = id;
        this.name = name;
        this.ref = ref;
        this.content = content;
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

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getUrlClicks() {
        return String.format("%s%d", URL_CLICK, id);
    }

    public String getUrlShows() {
        return String.format("%s%d", SHOW_URL, id);
    }

}
