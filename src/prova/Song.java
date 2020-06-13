package prova;

import java.io.*;

public class Song implements Serializable {
    private String artist;
    private String title;
    private String length;
    private String genre;
    private int rating;

    public Song(String artist, String title, String length, String genre, int rating) {
        this.artist = artist;
        this.title = title;
        this.length = length;
        this.genre = genre;
        this.rating = rating;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setLength(String length) throws InvalidLengthException {
        if (length.matches("(\\d.*):(\\d.*)")) {
            this.length = length;
        } else {
            throw new InvalidLengthException(length);
        }
    }

    public String getLength() {
        return this.length;

    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setRating(int rating) throws InvalidRatingException {
        if (rating > 0 || rating < 6) {
            this.rating = rating;
        } else {
            throw new InvalidRatingException(rating);
        }

    }

    public int getRating() {
        return this.rating;
    }


} //End class
