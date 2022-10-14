package model.listofsongs;

import model.playable.Playable;
import model.playable.Song;

import java.util.ArrayList;

// Represents a playlist that a user made, with given title. The user can freely play songs from this playlist.
// Is the accumulation of songs made by the user.
public class Playlist extends ListOfSongs {
    String title;

    ArrayList<Playable> songsInAlbum = new ArrayList<>();

    public Playlist(String title) {
        super(title);
    }

    // MODIFIES: this
    // EFFECTS: will delete given song from the songsInAlbum
    public void deleteSongFromList(Song song) {
        songsInAlbum.remove(song);
    }

    // MODIFIES: this
    // EFFECTS: add song to playlist
    //         - if a duplicate exists in the songsInAlbum than return false, otherwise return true
    public Boolean isSongInAlbum(Song song) {
        return songsInAlbum.contains(song);
    }
}
