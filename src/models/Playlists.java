package models;

import java.io.*;

public class Playlists implements Serializable {
    private String title;
    private Float duration;

    public Playlists(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public Float getDuration() {
        return this.duration;
    }
}
