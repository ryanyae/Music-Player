package model.playable;

import model.Album;
import model.Artist;
import org.json.JSONObject;

// Represents a song made by an artist
public class Song extends Playable {

    // EFFECTS: instantiates a new Playable object with a given title, artist, filePath and an
    //          empty ArrayList of feature
    //              - filePath is the location of the file within this object.
    public Song(Artist maker, String title, String filePath) {
        super(title, maker, filePath);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("maker", artist.toJson());
        json.put("filePath", filePath);
        return json;
    }
}
