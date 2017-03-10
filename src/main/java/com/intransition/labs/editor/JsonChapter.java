package com.intransition.labs.editor;

import java.util.Set;

class JsonChapter {

    private Integer id;

    private String title;

    private String content;

    private Set<JsonTag> tags;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Set<JsonTag> getTags() {
        return tags;
    }

    public void setTags(Set<JsonTag> tags) {
        this.tags = tags;
    }

}
