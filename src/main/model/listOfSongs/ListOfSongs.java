package model.listOfSongs;

import model.Artist;
import model.playable.Playable;
import model.playable.Song;

import java.util.ArrayList;

public interface ListOfSongs {
    Artist getArtist();

    int getPlaylistLength();

    String getPlaylistTitle();

    ArrayList<Playable> getListOfSongs();

    void addToListOfSongs(Song song);
}
