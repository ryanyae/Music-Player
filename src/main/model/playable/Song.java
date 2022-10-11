package model.playable;

import model.Artist;
import model.listOfSongs.Album;

import java.util.ArrayList;

public class Song implements Playable {
    private Artist artist;
    private String title;
    private String filePath;

    ArrayList<Artist> featuredArtists;

    private Album album;

    public Song(Artist maker, String title, String filePath, Album album) {
        this.artist = maker;
        this.title = title;
        this.filePath = filePath;
        this.album = album;
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

    public Album getAlbum() {
        return album;
    }

    @Override
    public void addFeature(Artist artist) {
        featuredArtists.add(artist);
    }

    @Override
    public ArrayList<Artist> getFeatures() {
        return featuredArtists;
    }
}
