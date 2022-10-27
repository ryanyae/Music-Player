package model.persistence;

import model.Artist;
import model.ListOfPlaylists;
import model.listofsongs.Playlist;
import model.Song;
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

    public ListOfPlaylists read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLOP(jsonObject);
    }

    private ListOfPlaylists parseLOP(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("previousPlaylists");
        ListOfPlaylists lop = new ListOfPlaylists();
        addPlaylists(lop, jsonArray);
        return lop;
    }

    private void addPlaylists(ListOfPlaylists lop, JSONArray jsonArray) {
        for (Object playlistJson:jsonArray) {
            JSONObject nextPlaylist = (JSONObject) playlistJson;
            addPlaylist(lop, nextPlaylist);
        }
    }

    private void addPlaylist(ListOfPlaylists lop, JSONObject nextPlaylist) {
        String playlistTitle = nextPlaylist.getString("title");
        JSONArray listOfSongsJson = nextPlaylist.getJSONArray("playables");

        parsePlaylistJson(listOfSongsJson, new Playlist(playlistTitle), lop);
    }

    private void parsePlaylistJson(JSONArray listOfSongsJson, Playlist playlist, ListOfPlaylists listOfPlaylists) {
        for (Object p:listOfSongsJson) {
            JSONObject nextPlayable = (JSONObject) p;
            addPlayable(playlist, nextPlayable);
        }
        listOfPlaylists.addNewPlaylist(playlist);
    }

    private void addPlayable(Playlist playlist, JSONObject p) {

        String songTitle = p.getString("title");
        JSONObject songMaker = p.getJSONObject("maker");
        String songFilePath = p.getString("filePath");


        Artist a = new Artist(songMaker.getString("name"));

        playlist.addToListOfSongs(new Song(a, songTitle,
                songFilePath));

    }

//    private ArrayList<String> jsonStringArrayConv(JSONArray songsMade) {
//        ArrayList<String> dummyList = new ArrayList<>();
//        for (Object json:songsMade) {
//            JSONObject s = (JSONObject) json;
//            dummyList.add(String.valueOf(s));
//        }
//        return dummyList;
//    }


    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }
}
