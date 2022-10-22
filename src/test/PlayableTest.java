import model.Artist;
import model.listofsongs.Album;
import model.playable.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayableTest {

    private Artist artistTest1;

    private Artist artistTest2;

    private Song songTest1;

    private Song songTest2;

    private Song songTest3;

    private Album albumTest1;
    private Album albumTest2;

    @BeforeEach
    void setUp() {
        artistTest1 = new Artist("Riot Games");
        songTest1 = new Song(artistTest1, "Legends Never Die", "./data/Legends-Never-Die.wav",
                albumTest1);
        albumTest1 = new Album(artistTest1, "Legends Album");
        albumTest1.addToListOfSongs(songTest1);

        artistTest2 = new Artist("Dua Lipa");
        songTest2 = new Song(artistTest2, "One Kiss", "./data/Dua Lipa - One Kiss.wav", albumTest2);
        songTest3 = new Song(artistTest2, "Levitating", "./data/Dua Lipa - Levitating.wav",
                albumTest2);
        albumTest2 = new Album(artistTest2, "Future Nostalgia");
        albumTest2.addToListOfSongs(songTest2);
        albumTest2.addToListOfSongs(songTest3);
    }

    @Test
    void constructorTest() {
        assertEquals(songTest1.getArtist(), artistTest1);
        assertEquals(songTest2.getArtist(), artistTest2);
        assertEquals(songTest3.getArtist(), artistTest2);

        assertEquals(songTest1.getTitle(), "Legends Never Die");
        assertEquals(songTest2.getTitle(), "One Kiss");
        assertEquals(songTest3.getTitle(), "Levitating");

        assertEquals(songTest1.getFilePath(), "./data/Legends-Never-Die.wav");
        assertEquals(songTest2.getFilePath(), "./data/Dua Lipa - One Kiss.wav");
        assertEquals(songTest3.getFilePath(), "./data/Dua Lipa - Levitating.wav");
    }
}

