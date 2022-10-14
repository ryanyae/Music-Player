package model.listofsongs;

import model.Artist;
import model.playable.Playable;

import java.util.ArrayList;

// Represents an album made by a artist, users will only be able to interact with the album, not modify it.
public class Album extends ListOfSongs {

    private Artist artist;                                     // Artist that made the album

    // REQUIRES: albumName to be of a non-zero length, and songsInAlbum to be > 0
    // EFFECTS: artist that created the album is set by given a, songs that are in the album
    //          is set by songsInAlbum, and the name of the Album is set by albumName.
    public Album(Artist a, String albumName) {
        super(albumName);
        this.artist = a;
        this.songsInAlbum = songsInAlbum;
    }

    public Artist getArtist() {
        return artist;
    }
}
