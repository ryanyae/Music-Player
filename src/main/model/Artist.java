package model;

import model.persistence.Writable;
import org.json.JSONObject;


// Represents that can make podcasts and songs, they are also able to make albums that are an accumulation of songs
// that the artist have made.
public class Artist implements Writable {

    String name;                            // stage name of the artist

    // EFFECTS: creates a new artist with the given name, and two new empty ArrayLists that represents
    //          all the songs and albums that the artist may have made
    public Artist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        return json;
    }
}
