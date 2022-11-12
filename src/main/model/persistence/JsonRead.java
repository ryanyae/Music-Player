package model.persistence;

import model.Artist;
import model.ListOfPlaylists;
import model.Song;
import model.listofsongs.Playlist;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads ListOfPlaylists from JSON data stored in file
public class JsonRead {
    protected String source;

    // EFFECTS: constructs reader to read from source file
    public JsonRead(String source) {
        this.source = source;
    }

    // EFFECTS: will read a ListOfPlaylists from file and returns it
    // throws IOException if an error occurs reading data from file
    public ListOfPlaylists read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLOP(jsonObject);
    }

    // EFFECTS: will parse a ListOfPlaylists from JSON object and returns it
    private ListOfPlaylists parseLOP(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("previousPlaylists");
        ListOfPlaylists lop = new ListOfPlaylists();
        addPlaylists(lop, jsonArray);
        return lop;
    }

    // EFFECTS: for each JSONObject element in the ArrayList of lop, this method will add them to the ListOfPlaylists
    //          - this method does do any of the actual adding, addPlaylist(...) is responsible for that
    private void addPlaylists(ListOfPlaylists lop, JSONArray jsonArray) {
        for (Object playlistJson:jsonArray) {
            JSONObject nextPlaylist = (JSONObject) playlistJson;
            addPlaylist(lop, nextPlaylist);
        }
    }

    // EFFECTS: given a JSONObject that represents 1 playlist, it will add that playlist to the given listOfPlaylists
    private void addPlaylist(ListOfPlaylists lop, JSONObject nextPlaylist) {
        String playlistTitle = nextPlaylist.getString("title");
        JSONArray listOfSongsJson = nextPlaylist.getJSONArray("playables");

        parsePlaylistJson(listOfSongsJson, new Playlist(playlistTitle), lop);
    }

    // EFFECTS: parse through a list of songs from a given playlist and adds it to the given playlist object
    private void parsePlaylistJson(JSONArray listOfSongsJson, Playlist playlist, ListOfPlaylists listOfPlaylists) {
        for (Object p:listOfSongsJson) {
            JSONObject nextPlayable = (JSONObject) p;
            addPlayable(playlist, nextPlayable);
        }
        listOfPlaylists.addNewPlaylist(playlist);
    }

    // EFFECTS: adds a give JSONObject, that represents a song object, to a given playlist
    private void addPlayable(Playlist playlist, JSONObject p) {

        String songTitle = p.getString("title");
        JSONObject songMaker = p.getJSONObject("maker");
        JSONArray featuredList = p.getJSONArray("featuredArtists");
        String songFilePath = p.getString("filePath");

        Artist a = new Artist(songMaker.getString("name"));
        Song s = new Song(a, songTitle, songFilePath);
        playlist.addToListOfSongs(s);

        for (Object json:featuredList) {
            JSONObject nextArtist = (JSONObject) json;
            s.addFeature(new Artist(nextArtist.getString("name")));
        }

    }

    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }
}
