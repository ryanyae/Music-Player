package model;

import model.listofsongs.Playlist;

import java.util.ArrayList;

public class ListOfPlaylists {

    ArrayList<Playlist> allPlaylists;

    public ListOfPlaylists() {
        allPlaylists = new ArrayList<>();
    }


    // MODIFIES: this
    // EFFECTS: adds new Playlist to the allPlaylists
    public boolean createNewPlaylist(Playlist playlist) {
        if (!allPlaylists.contains(playlist)) {
            allPlaylists.add(playlist);
            return true;
        }
        return false;
    }

    public ArrayList<Playlist> getAllPlaylists() {
        return allPlaylists;
    }
}
