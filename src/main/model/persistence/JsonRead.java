package model.persistence;

import model.ListOfPlaylists;
import model.exceptions.SongNotFoundException;
import model.listofsongs.Playlist;
import model.playable.Song;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.MusicApp;

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
            listOfPlaylists.addNewPlaylist(playlist);
        }
    }

    private void addPlayable(Playlist playlist, JSONObject p) {
        String songTitle = p.getString("title");
        try {
            playlist.addToListOfSongs(searchForSong(songTitle));
        } catch (SongNotFoundException e) {
            System.out.println("could not properly load song");
        }
    }

    private Song searchForSong(String songTitle) throws SongNotFoundException {

        for (Song s: MusicApp.getAllSongs()) {
            if (s.getTitle().equals(songTitle)) {
                return s;
            }
        }
        throw new SongNotFoundException();
    }


    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }
}
