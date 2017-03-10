package com.intransition.labs.domain.content;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "chapter")
public class Chapter {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private int chapterOrder;

    @Column
    private String title;

    @Column(columnDefinition = "text")
    private String content;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "chapter_tag",
            joinColumns = @JoinColumn(name = "chapter_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    private Set<Tag> tags;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "creative_id", nullable = false)
    private Creative parentCreative;

    public int getChapterOrder() {
        return chapterOrder;
    }

    public void setChapterOrder(int order) {
        this.chapterOrder = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Creative getParentCreative() {
        return parentCreative;
    }

    public void setParentCreative(Creative parentCreative) {
        this.parentCreative = parentCreative;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

}
