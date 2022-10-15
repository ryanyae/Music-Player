package model;

import model.listofsongs.Playlist;

import java.util.ArrayList;

// represents a list of playlists that the user may have, this user could have 0 playlist of 100.
public class ListOfPlaylists {

    ArrayList<Playlist> allPlaylists;       // all the playlists of the user


    // EFFECTS: creates a new ListOfPlaylists with an initially 0 playlists for the user.
    public ListOfPlaylists() {
        allPlaylists = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds new Playlist to the allPlaylists
    public void createNewPlaylist(Playlist playlist) {
        allPlaylists.add(playlist);
    }

    // MODIFIES: this
    // EFFECTS: check if there is a duplication of playlist titles in allPlaylists
    //              - if there is NO duplication, then add the playlist to the master list and return  true
    //          else return false
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
