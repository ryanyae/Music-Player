package model;

import model.listofsongs.Album;
import model.playable.Playable;
import model.playable.Song;

import java.util.ArrayList;

// Represents that can make podcasts and songs, they are also able to make albums that are an accumulation of songs
// that the artist have made.
public class Artist {
    ArrayList<Playable> songsMade;          // list of all the songs that the artist may have made

    ArrayList<Album> albumsMade;            // list of all the albums that the artist may have made

    String name;                            // stage name of the artist


    // EFFECTS: creates a new artist with the given name, and two new empty ArrayLists that represents
    //          all the songs and albums that the artist may have made
    public Artist(String name) {
        this.name = name;
        this.songsMade = new ArrayList<>();
        this.albumsMade = new ArrayList<>();
    }

    public ArrayList<Playable> getSongsMade() {
        return songsMade;
    }

    public ArrayList<Album> getAlbumsMade() {
        return albumsMade;
    }

    public void newSongMade(Song song) {
        songsMade.add(song);
    }

    // REQUIRES: album.getListOfSongs().size() > 0
    // MODIFIES: this
    // EFFECTS: creates a new album for the given artist, and will add all the songs on the album into
    //          the artist's "songsMade" ArrayList, regardless of duplication.
    public boolean newAlbumsMade(Album album) {
        if (album.getListOfSongs().size() != 0) {
            albumsMade.add(album);
            for (Playable song :album.getListOfSongs()) {
                songsMade.add(song);
            }
            return true;
        }

        return false;
    }

    public String getName() {
        return name;
    }
}
