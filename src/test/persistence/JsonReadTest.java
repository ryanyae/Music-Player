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
            assertTrue(listOfPlaylists.getPlaylistByIndex(0).getPlaylistTitle().equals("1234"));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
