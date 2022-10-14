package model.listofsongs;

import model.playable.Playable;
import model.playable.Song;

import java.util.ArrayList;

public abstract class ListOfSongs {
    protected String title;

    protected ArrayList<Playable> songsInAlbum;

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

    public void addToListOfSongs(Song song) {
        songsInAlbum.add(song);
    }

}
