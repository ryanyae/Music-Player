import model.Artist;
import model.listofsongs.Album;
import model.playable.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SongTest {

    private Artist artistTest1;

    private Artist artistTest2;

    private Artist artistTest3;

    private Song songTest1;

    private Song songTest2;

    private Song songTest3;

    private Album albumTest1;
    private Album albumTest2;

    @BeforeEach
    void setUp() {

        artistTest1 = new Artist("Riot Games");
        songTest1 = new Song(artistTest1, "Legends Never Die", "./resources/Legends-Never-Die.wav",
                albumTest1);
        albumTest1 = new Album(artistTest1, "Legends Album");
        albumTest1.addToListOfSongs(songTest1);

        artistTest2 = new Artist("Dua Lipa");
        songTest2 = new Song(artistTest2, "One Kiss", "./resources/Dua Lipa - One Kiss.wav", albumTest2);
        songTest3 = new Song(artistTest2, "Levitating", "./resources/Dua Lipa - Levitating.wav",
                albumTest2);
        albumTest2 = new Album(artistTest2, "Future Nostalgia");
        albumTest2.addToListOfSongs(songTest2);
        albumTest2.addToListOfSongs(songTest3);

        artistTest3 = new Artist("DaBaby");
    }

    @Test
    void addFeatureTest() {
        songTest3.addFeature(artistTest3);
        assertTrue(songTest3.getFeatures().contains(artistTest3));
    }
}
