package model.playable;

import model.Artist;
import model.listOfSongs.Album;

import java.util.ArrayList;

public class Podcast implements Playable {

    private String title;
    private Artist artist;
    private String filePath;

    ArrayList<Artist> featuredArtists;

    public Podcast(Artist maker, String title, String filePath) {
        this.artist = maker;
        this.title = title;
        this.filePath = filePath;
        featuredArtists = new ArrayList<>();
    }

    @Override
    public Artist getArtist() {
        return artist;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getFilePath() {
        return filePath;
    }

    @Override
    public void addFeature(Artist artist) {

    }

    @Override
    public ArrayList<Artist> getFeatures() {
        return featuredArtists;
    }

}
