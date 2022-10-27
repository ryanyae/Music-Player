import model.Artist;
import model.Album;
import model.playable.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AlbumTest {
    private Artist duaLipaTest;
    private Song songTest1;
    private Song songTest2;
    private Song songTest3;
    private Album albumTest1;

    @BeforeEach
    void setUp() {
        duaLipaTest = new Artist("Dua Lipa");
        songTest1 = new Song(duaLipaTest, "Future Nostalgia", "./data/Dua Lipa - Future Nostalgia.wav");
        songTest2 = new Song(duaLipaTest, "Levitating", "./data/Dua Lipa - Levitating");
        songTest3 = new Song(duaLipaTest, "One Kiss", "./data/Dua Lipa - One Kiss.wav");
        albumTest1 = new Album(duaLipaTest, "Dua Lipa Album Test");

    }

    @Test
    void constructorTest() {
        assertEquals(albumTest1.getArtist(), duaLipaTest);
        assertFalse(albumTest1.getListOfSongs().contains(songTest1.getTitle()));
        assertFalse(albumTest1.getListOfSongs().contains(songTest2.getTitle()));
        assertFalse(albumTest1.getListOfSongs().contains(songTest3.getTitle()));
        assertEquals(albumTest1.getAlbumTitle(), "Dua Lipa Album Test");
        try {
            albumTest1.getSongByIndex(0);
            fail("was not expecting to pass");
        } catch (IndexOutOfBoundsException e) {

        }
    }

    @Test
    void testAddToListOfSongs() {
        albumTest1.addToListOfSongs(songTest1);
        albumTest1.addToListOfSongs(songTest2);


        assertTrue(albumTest1.getListOfSongs().contains(songTest1.getTitle()));
        assertTrue(albumTest1.getListOfSongs().contains(songTest2.getTitle()));
        assertFalse(albumTest1.getListOfSongs().contains(songTest3.getTitle()));

        assertEquals(albumTest1.getSongByIndex(0), songTest1.getTitle());
        assertEquals(albumTest1.getSongByIndex(1), songTest2.getTitle());
    }
}
