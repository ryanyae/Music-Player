package model.listOfSongs;

import model.Artist;
import model.playable.Playable;
import model.playable.Song;

import java.util.ArrayList;

public class Album implements ListOfSongs {

    String albumName;
    Artist artist;

    ArrayList<Playable> songsInAlbum;

    public Album(Artist a, String albumName, ArrayList<Playable> songsInAlbum) {
        this.artist = a;
        this.albumName = albumName;
        this.songsInAlbum = songsInAlbum;
    }

    @Override
    public Artist getArtist() {
        return artist;
    }

    @Override
    public String getPlaylistTitle() {
        return albumName;
    }

    @Override
    public ArrayList<Playable> getListOfSongs() {
        return songsInAlbum;
    }

    @Override
    public void addToListOfSongs(Song song) {
        songsInAlbum.add(song);
    }

    @Override
    public int getPlaylistLength() {
        return songsInAlbum.size();
    }
}
