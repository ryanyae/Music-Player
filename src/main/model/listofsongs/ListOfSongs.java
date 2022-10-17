package model.listofsongs;

import model.playable.Playable;

import java.util.ArrayList;

// Abstract class that is a list of songs, you are able to change what songs are in the playlist.
// This class is extended by 2 other classes (Album and Playlist)
public abstract class ListOfSongs {
    protected String title;                         // title

    protected ArrayList<Playable> songsInAlbum;    // ArrayList that keeps track of all the songs in the ListOfSongs

    // REQUIRES: that the name to be a string of non-zero length
    // EFFECTS: creates a ListOfSongs
    protected ListOfSongs(String name) {
        this.title = name;
        this.songsInAlbum = new ArrayList<>();
    }

    public int getPlaylistLength() {
        return songsInAlbum.size();
    }

    public String getPlaylistTitle() {
        return title;
    }

    public ArrayList<Playable> getListOfSongs() {
        return songsInAlbum;
    }

    // MODIFIES: this
    // EFFECTS: adds given song songsInAlbum
    public void addToListOfSongs(Playable playable) {
        songsInAlbum.add(playable);
    }

    public Playable getSongByIndex(int i) {
        return songsInAlbum.get(i);
    }

}
