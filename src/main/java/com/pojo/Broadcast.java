package com.pojo;

import java.io.Serializable;

public class Broadcast implements Serializable {
    private Integer id;
    private String releaseTime;
    private String digest;
    private String title;
    private String source;
    private String founder;
    private String modifier;

    public Broadcast() {
    }

    public Broadcast(Integer id, String releaseTime, String digest, String title, String source, String founder, String modifier) {
        this.id = id;
        this.releaseTime = releaseTime;
        this.digest = digest;
        this.title = title;
        this.source = source;
        this.founder = founder;
        this.modifier = modifier;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}
