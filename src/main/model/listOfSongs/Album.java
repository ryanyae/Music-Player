package model.listOfSongs;

import model.Artist;
import model.playable.Playable;

import java.util.ArrayList;

public class Album implements listOfSongs {

    String albumName;
    Artist artist;
    String title;

    ArrayList<Playable> songsInAlbum;

    public Album(Artist a, String albumName, String title, ArrayList<Playable> songsInAlbum) {
        this.artist = a;
        this.albumName = albumName;
        this.title = title;
        this.songsInAlbum = songsInAlbum;
    }

    @Override
    public Artist getArtist() {
        return artist;
    }

    @Override
    public String getPlaylistTitle() {
        return title;
    }

    @Override
    public ArrayList<Playable> getListOfSongs() {
        return songsInAlbum;
    }

    @Override
    public int getPlaylistLength() {
        return songsInAlbum.size();
    }
}
