package model.playable;

import model.Artist;
import model.listofsongs.Album;
import org.json.JSONObject;

// Represents a song made by an artist
public class Song extends Playable {
    private Album album;

    // EFFECTS: instantiates a new Playable object with a given title, artist, filePath and an
    //          empty ArrayList of features
    //              - filePath is the location of the file within this object.
    public Song(Artist maker, String title, String filePath) {
        super(title, maker, filePath);
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Album getAlbum() {
        return album;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("maker", artist);
        json.put("filePath", filePath);
        json.put("album", album.toJson());
        return json;
    }
}
