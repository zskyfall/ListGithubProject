package com.example.ginz.listgithubprojects;

public class Project {
    private String mName;
    private String mOwner;
    private String mLanguage;
    private String mHomeUrl;
    private String mDescription;
    private int mFork;

    public Project(String name, String owner, String language, String homeUrl, int fork, String description){
        this.mName = name;
        this.mOwner = owner;
        this.mLanguage = language;
        this.mHomeUrl = homeUrl;
        this.mFork = fork;
        this.mDescription = description;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getOwner() {
        return mOwner;
    }

    public void setOwner(String owner) {
        this.mOwner = owner;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        this.mLanguage = language;
    }

    public String getHomeUrl() {
        return mHomeUrl;
    }

    public void setHomeUrl(String homeUrl) {
        this.mHomeUrl = homeUrl;
    }

    public int getFork() {
        return mFork;
    }

    public void setFork(int fork) {
        this.mFork = fork;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }
}
