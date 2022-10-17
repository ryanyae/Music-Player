package model.playable;

import model.Artist;
import model.listofsongs.Album;

import java.util.ArrayList;

// Represents a song made by an artist
public class Song extends Playable {
    private Album album;

    // EFFECTS: instantiates a new Playable object with a given title, artist, filePath and an
    //          empty ArrayList of features
    //              - filePath is the location of the file within this object.
    public Song(Artist maker, String title, String filePath, Album album) {
        super(title, maker, filePath);
        this.album = album;
    }

    public Album getAlbum() {
        return album;
    }
}
