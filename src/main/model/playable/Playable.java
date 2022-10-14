package model.playable;

import model.Artist;

import java.util.ArrayList;

public abstract class Playable {
    protected String title;
    protected Artist artist;
    protected String filePath;
    protected ArrayList<Artist> featuredArtists;

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
