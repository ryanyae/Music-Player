package model;

import model.playable.Playable;
import model.playable.Song;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// Represents that can make podcasts and songs, they are also able to make albums that are an accumulation of songs
// that the artist have made.
public class Artist {
    ArrayList<String> songsMade;          // list of all the songs that the artist may have made

    ArrayList<String> albumsMade;            // list of all the albums that the artist may have made

    String name;                            // stage name of the artist


    // EFFECTS: creates a new artist with the given name, and two new empty ArrayLists that represents
    //          all the songs and albums that the artist may have made
    public Artist(String name) {
        this.name = name;
        this.songsMade = new ArrayList<>();
        this.albumsMade = new ArrayList<>();
    }

    public ArrayList<String> getSongsMade() {
        return songsMade;
    }

    public ArrayList<String> getAlbumsMade() {
        return albumsMade;
    }

    public void newSongMade(Song song) {
        songsMade.add(song.getTitle());
    }

    // REQUIRES: album.getListOfSongs().size() > 0
    // MODIFIES: this
    // EFFECTS: creates a new album for the given artist, and will add all the songs on the album into
    //          the artist's "songsMade" ArrayList, regardless of duplication.
    public boolean newAlbumsMade(Album album) {
        if (album.getListOfSongs().size() != 0) {
            albumsMade.add(album.getAlbumTitle());
            for (String songString :album.getListOfSongs()) {
                songsMade.add(songString);
            }
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("songsMade", songsMadeToJson());
        json.put("albumsMade", albumsMadeToJson());
        return json;
    }

    private JSONArray albumsMadeToJson() {
        JSONArray jsonArray = new JSONArray();
        for (String a:albumsMade) {
            jsonArray.put(a);
        }
        return jsonArray;
    }

    private JSONArray songsMadeToJson() {
        JSONArray jsonArray = new JSONArray();
        for (String p:songsMade) {
            jsonArray.put(p);
        }
        return jsonArray;
    }

}
