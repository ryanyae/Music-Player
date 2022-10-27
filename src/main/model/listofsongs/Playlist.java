package model.listofsongs;

import model.Song;

// Represents a playlist that a user made, with a given title. The user can freely play songs from this playlist.
// Is the accumulation of songs made by the user.
public class Playlist extends ListOfSongs {


    // EFFECTS: creates a new Playlist object which is an extension of the ListOfSongs abstact class
    public Playlist(String title) {
        super(title);
    }

    // REQUIRES; 0 =< index <= playlist.size()
    // MODIFIES: this
    // EFFECTS: will check if
    public void deleteSongFromList(int index) {
        songsInAlbum.remove(index);
    }

    // MODIFIES: this
    // EFFECTS: returns true if the given song is already in the playlist, otherwise false.
    public Boolean isSongInAlbum(Song song) {
        return songsInAlbum.contains(song);
    }
}
