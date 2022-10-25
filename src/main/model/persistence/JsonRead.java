package model.persistence;

import model.listofsongs.Playlist;
import model.playable.Playable;
import model.playable.Song;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonRead {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonRead(String source) {
        this.source = source;
    }

    public Playlist read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePlaylist(jsonObject);
    }

    private Playlist parsePlaylist(JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        Playlist p = new Playlist(title);
        addPlaylist(p, jsonObject);
        return p;
    }

    private void addPlaylist(Playlist p, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("playables");
        for (Object json:jsonArray) {
            JSONObject nextPlayable = (JSONObject) json;
            addPlayable(p, nextPlayable);
        }
    }

    private void addPlayable(Playlist p, JSONObject nextPlayable) {
        String name = nextPlayable.getString("title");
        String maker = nextPlayable.getString("maker");
        String filePath = nextPlayable.getString("filePath");
        String album = nextPlayable.getString("album");
        Playable playable = new Song(maker, name, filePath);
    }


    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(this.source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }
}
