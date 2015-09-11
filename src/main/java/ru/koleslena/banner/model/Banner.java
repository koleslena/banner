package ru.koleslena.banner.model;

import javax.persistence.*;

/**
 * @since 07.09.15.
 */
@Entity
@SequenceGenerator(schema="banner", name="seqBannerGenerator", sequenceName = "banner_seq")
public class Banner {

    @Id
    @GeneratedValue(generator = "seqBannerGenerator", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String name;
    @Column
    private String ref;
    @Column
    @Lob
    private byte[] content;

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
}
