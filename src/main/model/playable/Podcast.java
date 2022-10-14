package model.playable;

import model.Artist;

import java.util.ArrayList;

// Represents a Playable Podcast, different in the terms that this is not a song, but a podcast.
public class Podcast extends Playable {

    //EFFECTS: constructs a new podcast object with given title, maker, and filePath
    public Podcast(Artist maker, String title, String filePath) {
        this.artist = maker;
        this.title = title;
        this.filePath = filePath;
        featuredArtists = new ArrayList<>();
    }
}
