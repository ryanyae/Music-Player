package model.listofsongs;

import model.Artist;

// Represents an album made by an artist, users will only be able to interact with the album, not modify it.
// Album is an extension of the abstract class "ListOfSongs" in which has many implementations of how a
//      list of songs may work.
public class Album extends ListOfSongs {

    private final Artist artist;                                     // Artist that made the album

    // REQUIRES: albumName to be of a non-zero length, and songsInAlbum to be > 0
    // EFFECTS: artist that created the album is set by given the artist, songs that are on the album
    //          is set by songsInAlbum, and the name of the Album is set by albumName.
    public Album(Artist artist, String albumName) {
        super(albumName);
        this.artist = artist;
    }

    public Artist getArtist() {
        return artist;
    }
}
