package model.playable;

import model.Artist;

import java.util.ArrayList;

// Represents a Playable Podcast, different in the terms that this is not a song, but a podcast.
public class Podcast extends Playable {

    // EFFECTS: instantiates a new Playable object with a given title, artist, filePath and an
    //          empty ArrayList of features
    //              - filePath is the location of the file within this object.
    public Podcast(Artist maker, String title, String filePath) {
        super(title, maker, filePath);
    }
}
