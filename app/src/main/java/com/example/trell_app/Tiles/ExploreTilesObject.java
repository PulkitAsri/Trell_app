package com.example.trell_app.Tiles;

public class ExploreTilesObject {
    String postId;
    String thumbnailUrl;
    long noOfLikes;
    String username;
    String title;

    public ExploreTilesObject(String postId, String thumbnailUrl, long noOfLikes, String username, String title) {
        this.postId = postId;
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

    public long getNoOfLikes() {
        return noOfLikes;
    }

    public void setNoOfLikes(long noOfLikes) {
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

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
