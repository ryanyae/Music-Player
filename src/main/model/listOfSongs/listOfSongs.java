package model.listOfSongs;

import model.Artist;
import model.playable.Playable;

import java.util.ArrayList;

public interface listOfSongs {
    Artist getArtist();

    int getPlaylistLength();

    String getPlaylistTitle();

    ArrayList<Playable> getListOfSongs();

}
