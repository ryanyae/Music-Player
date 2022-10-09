package model.listOfSongs;

import model.Artist;
import model.playable.Playable;

import java.util.ArrayList;

public class Playlist implements listOfSongs {
    Artist artist;
    String title;

    ArrayList<Playable> songsInAlbum;

    public Playlist(Artist a, String title, ArrayList<Playable> songsInAlbum) {
        this.artist = a;
        this.title = title;
        this.songsInAlbum = songsInAlbum;
    }

    // EFFECTS: returns maker of the playlist
    @Override
    public Artist getArtist() {
        return artist;
    }

    @Override
    public int getPlaylistLength() {
        return songsInAlbum.size();
    }

    @Override
    public String getPlaylistTitle() {
        return title;
    }

    @Override
    public ArrayList<Playable> getListOfSongs() {
        return songsInAlbum;
    }
}
