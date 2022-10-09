package model.playable;

import model.Artist;
import model.listOfSongs.Album;

public class Song implements Playable {
    private Artist artist;
    private String title;
    private String filePath;

    private Album album;

    public Song(Artist maker, String title, String filePath) {
        this.artist = maker;
        this.title = title;
        this.filePath = filePath;
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
    public Album getAlbum() {
        return null;
    }
}
