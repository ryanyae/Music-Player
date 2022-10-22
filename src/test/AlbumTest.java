import model.Artist;
import model.listofsongs.Album;
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

    private Artist charliePuth;

    @BeforeEach
    void setUp() {
        duaLipaTest = new Artist("Dua Lipa");
        songTest1 = new Song(duaLipaTest, "Future Nostalgia", "./data/Dua Lipa - Future Nostalgia.wav",
                albumTest1);
        songTest2 = new Song(duaLipaTest, "Levitating", "./data/Dua Lipa - Levitating",
                albumTest1);
        songTest3 = new Song(duaLipaTest, "One Kiss", "./data/Dua Lipa - One Kiss.wav",
                albumTest1);
        albumTest1 = new Album(duaLipaTest, "Dua Lipa Album Test");

        albumTest1.addToListOfSongs(songTest1);
        albumTest1.addToListOfSongs(songTest2);

        charliePuth = new Artist("Charlie Puth");
    }

    @Test
    void constructorTest() {
        assertEquals(albumTest1.getArtist(), duaLipaTest);
        assertTrue(albumTest1.getListOfSongs().contains(songTest1));
        assertTrue(albumTest1.getListOfSongs().contains(songTest2));
        assertFalse(albumTest1.getListOfSongs().contains(songTest3));
        assertEquals(albumTest1.getPlaylistTitle(), "Dua Lipa Album Test");
    }
}
