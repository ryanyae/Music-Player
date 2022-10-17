package model.playable;

import model.Artist;

import java.util.ArrayList;

// Represents an object that is playable, meaning it can be played using a method
public abstract class Playable {
    protected String title;                        // title of the playable
    protected Artist artist;                       // artist that made this playable
    protected String filePath;                     // file address of the playable in the project
    protected ArrayList<Artist> featuredArtists;   // list of artist that maybe features on the playable

    // REQUIRES: title length to of non-zero length and the playable's .wav file must exist in the resources folder
    // EFFECTS: instantiates a new Playable object with a given title, artist, filePath and an
    //          empty ArrayList of features
    //              - filePath is the location of the file within this object.
    public Playable(String title, Artist artist, String filePath) {
        this.title = title;
        this.artist = artist;
        this.filePath = filePath;
        featuredArtists = new ArrayList<>();
    }

    public Artist getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getFilePath() {
        return filePath;
    }

    public void addFeature(Artist artist) {
        featuredArtists.add(artist);
    }

    public ArrayList<Artist> getFeatures() {
        return featuredArtists;
    }
}
