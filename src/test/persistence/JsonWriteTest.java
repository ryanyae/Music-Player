package persistence;

import model.Album;
import model.Artist;
import model.ListOfPlaylists;
import model.listofsongs.Playlist;
import model.persistence.JsonRead;
import model.persistence.JsonWrite;
import model.playable.Song;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriteTest {


    @Test
    void testWriterInvalidFile() {
        try {
            ListOfPlaylists lop = new ListOfPlaylists();
            JsonWrite writer = new JsonWrite("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ListOfPlaylists lop = new ListOfPlaylists();
            JsonWrite writer = new JsonWrite("./data/testWriteEmptyListOfPlaylists.json");
            writer.open();
            writer.write(lop);
            writer.close();

            JsonRead reader = new JsonRead("./data/testWriteEmptyListOfPlaylists.json");
            lop = reader.read();
            assertEquals(0, lop.getLength());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriteGeneralListOfPlaylists() {
        try {
            ListOfPlaylists lop = new ListOfPlaylists();

            Artist artistTest1 = new Artist("Riot Games");
            Song songTest1 = new Song(artistTest1, "Legends Never Die", "./data/Legends-Never-Die.wav");
            Album albumTest1 = new Album(artistTest1, "Legends Album");
            albumTest1.addToListOfSongs(songTest1);

            Artist artistTest2 = new Artist("Dua Lipa");
            Song songTest2 = new Song(artistTest2, "One Kiss", "./data/Dua Lipa - One Kiss.wav");
            Song songTest3 = new Song(artistTest2, "Levitating", "./data/Dua Lipa - Levitating.wav");
            Album albumTest2 = new Album(artistTest2, "Future Nostalgia");
            albumTest2.addToListOfSongs(songTest2);
            albumTest2.addToListOfSongs(songTest3);

            Playlist testPlaylist = new Playlist("Test 1");
            testPlaylist.addToListOfSongs(songTest1);

            Playlist testPlaylist2 = new Playlist("Test 2");
            testPlaylist2.addToListOfSongs(songTest2);
            testPlaylist2.addToListOfSongs(songTest3);

            lop.addNewPlaylist(testPlaylist);
            lop.addNewPlaylist(testPlaylist2);

            JsonWrite writer = new JsonWrite("./data/testWriteGeneralListOfPlaylists");
            writer.open();
            writer.write(lop);
            writer.close();

            JsonRead reader = new JsonRead("./data/testWriteGeneralListOfPlaylists");
            lop = reader.read();
            assertEquals(2, lop.getLength());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
