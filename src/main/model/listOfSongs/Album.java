package model.listOfSongs;

import model.Artist;
import model.playable.Playable;
import model.playable.Song;

import java.util.ArrayList;

// Represents an album made by a artist, users will only be able to interact with the album, not modify it.
public class Album implements ListOfSongs {

    String albumName;                                  // Name of the album
    Artist artist;                                     // Artist that made the album
    ArrayList<Playable> songsInAlbum;                  // Songs that are in the album

    // REQUIRES: albumName to be of a non-zero length, and songsInAlbum to be > 0
    // EFFECTS: artist that created the album is set by given a, songs that are in the album
    //          is set by songsInAlbum, and the name of the Album is set by albumName.
    public Album(Artist a, String albumName, ArrayList<Playable> songsInAlbum) {
        this.artist = a;
        this.albumName = albumName;
        this.songsInAlbum = songsInAlbum;
    }

    public Artist getArtist() {
        return artist;
    }

    @Override
    public String getPlaylistTitle() {
        return albumName;
    }

    @Override
    public ArrayList<Playable> getListOfSongs() {
        return songsInAlbum;
    }

    // MODIFIES: this
    // EFFECTS: adds the given song to the list of songs in the album
    @Override
    public void addToListOfSongs(Song song) {
        songsInAlbum.add(song);
    }

    @Override
    public int getPlaylistLength() {
        return songsInAlbum.size();
    }


}
