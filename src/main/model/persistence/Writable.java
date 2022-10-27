package model.persistence;

import org.json.JSONObject;

// Represents a class that is writable into JSON
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}