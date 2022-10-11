package model.playable;

import model.Artist;
import model.listOfSongs.Album;

public class Podcast implements Playable {

    private String title;
    private Artist artist;
    private String filePath;

    private Album album;

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
        return album;
    }

}
