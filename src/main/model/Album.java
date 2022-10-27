package model;

import java.util.ArrayList;

// Represents an album made by an artist, users will only be able to interact with the album, not modify it.
// Album is an extension of the abstract class "ListOfSongs" in which has many implementations of how a
//      list of songs may work.
public class Album {

    private final String title;
    private ArrayList<String> songsInAlbum;
    private final Artist artist;                                     // Artist that made the album

    // REQUIRES: albumName to be of a non-zero length, and songsInAlbum to be > 0
    // EFFECTS: artist that created the album is set by given the artist, songs that are on the album
    //          is set by songsInAlbum, and the name of the Album is set by albumName.
    public Album(Artist artist, String albumName) {
        this.title = albumName;
        this.artist = artist;
        this.songsInAlbum = new ArrayList<>();
    }

    public Artist getArtist() {
        return artist;
    }

    public void addToListOfSongs(Song playable) {
        songsInAlbum.add(playable.getTitle());
    }

    public ArrayList<String> getListOfSongs() {
        return songsInAlbum;
    }

    public String getSongByIndex(int i) {
        return songsInAlbum.get(i);
    }

    public String getAlbumTitle() {
        return title;
    }
}
