package com.example.trell_app.Tiles;

public class ExploreTilesObject {
    String thumbnailUrl;
    int noOfLikes;
    String username;
    String title;

    public ExploreTilesObject(String thumbnailUrl, int noOfLikes, String username, String title) {
        this.thumbnailUrl = thumbnailUrl;
        this.noOfLikes = noOfLikes;
        this.username = username;
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getNoOfLikes() {
        return noOfLikes;
    }

    public void setNoOfLikes(int noOfLikes) {
        this.noOfLikes = noOfLikes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
