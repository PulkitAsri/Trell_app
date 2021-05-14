package com.example.trell_app.VideoPlayback;

import java.util.HashMap;
import java.util.Map;

public class MediaObject {

    public MediaObject(){}




    private String thumbnail;
    private String mediaUrl;
    private String title;
    private String desc;
    private String date;
    private String userId;
    private Long views;
    private String postId;
    private String userName;
    private Long noOfLikes;
    private Long noOfComments;
    Map<String,Object> likes,comments;

    private boolean isLiked;



    public MediaObject(String postId, String userId, String thumbnail, String mediaUrl, String title, String desc, String date, long views, String userName, Object likes, long noOfLikes, Object comments, long noOfComments){
        this.thumbnail = thumbnail;
        this.mediaUrl = mediaUrl;
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.userId = userId;
        this.views = views;
        this.postId = postId;
        this.userName = userName;
        this.likes = (Map<String, Object>) likes;
        this.noOfLikes = noOfLikes;
        this.comments = (Map<String, Object>)comments;
        this.noOfComments = noOfComments;//just for now
        isLiked=false;
    }



    public MediaObject(String postId, String userId, String thumbnail, String mediaUrl, String title, String desc, String date, Long views, String userName, HashMap<String, Object> likes, Long noOfLikes, HashMap<String, Object> comments, Long noOfComments) {
        this.thumbnail = thumbnail;
        this.mediaUrl = mediaUrl;
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.userId = userId;
        this.views = views;
        this.postId = postId;
        this.userName = userName;
        this.likes = likes;
        this.noOfLikes = noOfLikes;
        this.comments = comments;
        this.noOfComments = noOfComments;
    }

//    public MediaObject(String thumbnail, String mediaUrl, String title, String desc, String date, String userId, String views, String postId, String userName) {
//        this.thumbnail = thumbnail;
//        this.mediaUrl = mediaUrl;
//        this.title = title;
//        this.desc = desc;
//        this.date = date;
//        this.userId = userId;
//        this.views = views;
//        this.postId = postId;
//        this.userName = userName;
//    }

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

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
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

    public Map<String, Object> getLikes() {
        return likes;
    }

    public void setLikes(Map<String, Object> likes) {
        this.likes = likes;
    }

    public Long getNoOfLikes() {
        return noOfLikes;
    }

    public void setNoOfLikes(Long noOfLikes) {
        this.noOfLikes = noOfLikes;
    }

    public Map<String, Object> getComments() {
        return comments;
    }

    public void setComments(Map<String, Object> comments) {
        this.comments = comments;
    }

    public Long getNoOfComments() {
        return noOfComments;
    }

    public void setNoOfComments(long noOfComments) {
        this.noOfComments = noOfComments;
    }


    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
