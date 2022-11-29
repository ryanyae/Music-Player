package model;

import model.persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// Represents an object that is playable, meaning it can be played using a method
public class Song implements Writable {
    protected String title;                        // title of the playable
    protected Artist artist;                       // artist that made this playable
    protected String filePath;                     // file address of the playable in the project
    protected ArrayList<Artist> featuredArtists;   // list of artist that maybe features on the playable
    protected String imagePath;

    // REQUIRES: title length to of non-zero length and the playable's .wav file must exist in the resources folder
    // EFFECTS: instantiates a new Playable object with a given title, artist, filePath and an
    //          empty ArrayList of features
    //              - filePath is the location of the file within this object.
    public Song(Artist artist, String title, String filePath) {
        this.title = title;
        this.artist = artist;
        this.filePath = filePath;
        featuredArtists = new ArrayList<>();
    }

    public Song(Artist artist, String title, String filePath, String imagePath) {
        this.title = title;
        this.artist = artist;
        this.filePath = filePath;
        featuredArtists = new ArrayList<>();
        this.imagePath = imagePath;
    }

    public Artist getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getFilePath() {
        return filePath;
    }

    public void addFeature(Artist artist) {
        featuredArtists.add(artist);
    }

    public ArrayList<Artist> getFeatures() {
        return featuredArtists;
    }

    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: creates a JSON representation of a Song object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("maker", artist.toJson());
        json.put("filePath", filePath);
        json.put("imagePath", imagePath);
        json.put("featuredArtists", featuredArtistsJson(featuredArtists));
        return json;
    }

    private JSONArray featuredArtistsJson(ArrayList<Artist> featuredArtists) {
        JSONArray jsonArray = new JSONArray();
        for (Artist a:featuredArtists) {
            jsonArray.put(a.toJson());
        }
        return jsonArray;
    }

    public String getImagePath() {
        return this.imagePath;
    }
}
