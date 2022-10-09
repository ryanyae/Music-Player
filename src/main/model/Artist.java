package model;

import model.listOfSongs.Album;
import model.playable.Playable;

import java.util.ArrayList;

public class Artist {
    ArrayList<Playable> songsMade;

    ArrayList<Album> albumsMade;

    String name;

    public Artist(String name) {
        this.name = name;
    }

    public ArrayList<Playable> getSongsMade() {
        return songsMade;
    }

    public ArrayList<Album> getAlbumsMade() {
        return albumsMade;
    }
}
