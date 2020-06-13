package prova;

import java.io.*;

public class Playlists implements Serializable {
    private String title;

    public Playlists(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }


}
