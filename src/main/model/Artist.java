package model;

import model.listOfSongs.Album;
import model.playable.Playable;
import model.playable.Song;

import java.util.ArrayList;

// Represents that can make podcasts and songs, they are also able to make albums that are an accumulation of songs
// that the artist have made.
public class Artist {
    ArrayList<Playable> songsMade;

    ArrayList<Album> albumsMade;

    String name;

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

    public void newAlbumsMade(Album album) {
        albumsMade.add(album);
        for (Playable song:album.getListOfSongs()) {
            songsMade.add(song);
        }
    }

    public String getName() {
        return name;
    }
}
