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
    public void createNewPlaylist(Playlist playlist) {
        allPlaylists.add(playlist);
    }

    public boolean inAllPlaylist(String titleInput) {
        for (Playlist dummyPlaylist:allPlaylists) {
            if (dummyPlaylist.getPlaylistTitle().equals(titleInput)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Playlist> getAllPlaylists() {
        return allPlaylists;
    }
}
