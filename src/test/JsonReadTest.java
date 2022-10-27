import model.ListOfPlaylists;
import model.listofsongs.Playlist;
import model.persistence.JsonRead;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReadTest {

    @Test
    void testReaderNonExistentFile() {
        JsonRead reader = new JsonRead("./data/noSuchFile.json");
        try {
            ListOfPlaylists lop = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonRead reader = new JsonRead("./data/readTestEmptySet.json");
        try {
            ListOfPlaylists lop = reader.read();
            assertEquals(0, lop.getLength());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
