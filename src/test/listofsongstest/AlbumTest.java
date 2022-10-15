package listofsongstest;

import model.Artist;
import model.listofsongs.Album;
import model.listofsongs.Playlist;
import model.playable.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlbumTest {
    private Artist duaLipaTest;
    private Song songTest1;
    private Song songTest2;
    private Song songTest3;
    private Album albumTest1;

    @BeforeEach
    void setUp() {
        duaLipaTest = new Artist("Dua Lipa");
        songTest1 = new Song(duaLipaTest, "Future Nostalgia", "resources/Dua Lipa - Future Nostalgia.wav",
                albumTest1);
        songTest2 = new Song(duaLipaTest, "Levitating", "./resources/Dua Lipa - Levitating",
                albumTest1);
        songTest3 = new Song(duaLipaTest, "One Kiss", "./resources/Dua Lipa - One Kiss.wav",
                albumTest1);
        albumTest1 = new Album(duaLipaTest, "Dua Lipa Album Test");

        albumTest1.addToListOfSongs(songTest1);
        albumTest1.addToListOfSongs(songTest2);
        albumTest1.addToListOfSongs(songTest3);
    }

    @Test
    void constructorTest() {
        assertEquals(albumTest1.getArtist(), duaLipaTest);
    }
}
