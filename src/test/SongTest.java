import model.Artist;
import model.Album;
import model.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SongTest {

    private Artist artistTest3;

    private Song songTest3;

    @BeforeEach
    void setUp() {

        Artist artistTest1 = new Artist("Riot Games");
        Song songTest1 = new Song(artistTest1, "Legends Never Die", "./data/Legends-Never-Die.wav");
        Album albumTest1 = new Album(artistTest1, "Legends Album");
        albumTest1.addToListOfSongs(songTest1);

        Artist artistTest2 = new Artist("Dua Lipa");
        Song songTest2 = new Song(artistTest2, "One Kiss", "./data/Dua Lipa - One Kiss.wav");
        songTest3 = new Song(artistTest2, "Levitating", "./data/Dua Lipa - Levitating.wav");
        Album albumTest2 = new Album(artistTest2, "Future Nostalgia");
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
