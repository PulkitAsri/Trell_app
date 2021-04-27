package com.example.trell_app.VideoPlayback;

public class MediaObject {

    public MediaObject(){
        //just for now
    }

    private String thumbnail,mediaUrl,title,desc,date,userId,views,postId,userName;

    public MediaObject(String thumbnail, String mediaUrl, String title, String desc, String date, String userId, String views, String postId, String userName) {
        this.thumbnail = thumbnail;
        this.mediaUrl = mediaUrl;
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.userId = userId;
        this.views = views;
        this.postId = postId;
        this.userName = userName;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
