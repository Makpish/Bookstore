package com.know.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NameInput {

    @JsonProperty
    public String name;

    @JsonProperty
    public Long authorId;

    public NameInput() {}

    public NameInput(String name, Long authorId) {
        this.name = name;
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
