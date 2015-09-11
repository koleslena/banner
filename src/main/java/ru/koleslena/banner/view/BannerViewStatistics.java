package ru.koleslena.banner.view;


/**
 * @since 07.09.15.
 */
public class BannerViewStatistics {

    private Long id;
    private String name;
    private String ref;
    private byte[] content;
    private Long clicks;
    private Long shows;

    public BannerViewStatistics() {
    }

    public BannerViewStatistics(Long id, String name, String ref, byte[] content, Long clicks, Long shows) {
        this.id = id;
        this.name = name;
        this.ref = ref;
        this.content = content;
        this.clicks = clicks;
        this.shows = shows;
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

    public Long getClicks() {
        return clicks;
    }

    public void setClicks(Long clicks) {
        this.clicks = clicks;
    }

    public Long getShows() {
        return shows;
    }

    public void setShows(Long shows) {
        this.shows = shows;
    }

}
