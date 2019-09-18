package com.yarinov.filemanagerapp.Video;

public class VideoInfo {

    private String videoName;
    private String videoSize;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(String videoSize) {
        this.videoSize = videoSize;
    }

    public VideoInfo(String videoName, String videoSize) {
        this.videoName = videoName;
        this.videoSize = videoSize;
    }

    public VideoInfo() {
    }
}
