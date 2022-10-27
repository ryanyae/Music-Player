package persistence;

import model.ListOfPlaylists;
import model.persistence.JsonRead;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReadTest {

    @Test
    void testReaderNonExistentFile() {
        JsonRead reader = new JsonRead("./data/noSuchFile.json");
        try {
            ListOfPlaylists listOfPlaylists = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonRead reader = new JsonRead("./data/readTestEmptySet.json");
        try {
            ListOfPlaylists listOfPlaylists = reader.read();
            assertEquals(0, listOfPlaylists.getLength());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonRead reader = new JsonRead("./data/readTestGeneralSet.json");
        try {
            ListOfPlaylists listOfPlaylists = reader.read();
            assertEquals(1, listOfPlaylists.getLength());
            assertEquals("1234", listOfPlaylists.getPlaylistByIndex(0).getPlaylistTitle());
            assertEquals(2, listOfPlaylists.getPlaylistByIndex(0).getPlaylistLength());

            assertEquals("Dua Lipa",
                    listOfPlaylists.getPlaylistByIndex(0).getSongByIndex(0).getArtist().getName());
            assertEquals("test-stub1",
                    listOfPlaylists.getPlaylistByIndex(0).getSongByIndex(0).getFilePath());
            assertEquals("Future Nostalgia",
                    listOfPlaylists.getPlaylistByIndex(0).getSongByIndex(0).getTitle());


            assertEquals("Riot Games",
                    listOfPlaylists.getPlaylistByIndex(0).getSongByIndex(1).getArtist().getName());
            assertEquals("test-stub2",
                    listOfPlaylists.getPlaylistByIndex(0).getSongByIndex(1).getFilePath());
            assertEquals("Legends Never Die",
                    listOfPlaylists.getPlaylistByIndex(0).getSongByIndex(1).getTitle());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
