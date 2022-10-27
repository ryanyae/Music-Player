package model.listofsongs;

import model.persistence.Writable;
import model.playable.Playable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// Abstract class that is a list of songs, you are able to change what songs are in the playlist (deleting or adding).
// This class is extended by 2 other classes (Album and Playlist)
public abstract class ListOfSongs implements Writable {
    protected final String title;                   // title

    protected ArrayList<Playable> songsInAlbum;    // ArrayList that keeps track of all the songs in the ListOfSongs

    // REQUIRES: that the name to be a string of non-zero length
    // EFFECTS: creates a ListOfSongs
    protected ListOfSongs(String name) {
        this.title = name;
        this.songsInAlbum = new ArrayList<>();
    }

    public int getPlaylistLength() {
        return songsInAlbum.size();
    }

    public String getPlaylistTitle() {
        return title;
    }

    public ArrayList<Playable> getListOfSongs() {
        return songsInAlbum;
    }

    // MODIFIES: this
    // EFFECTS: adds given song songsInAlbum
    public void addToListOfSongs(Playable playable) {
        songsInAlbum.add(playable);
    }

    public Playable getSongByIndex(int i) {
        return songsInAlbum.get(i);
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("playables", playablesToJson());
        return json;
    }

    private JSONArray playablesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Playable p:songsInAlbum) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }
}
