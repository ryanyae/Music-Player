import model.Artist;
import model.Album;
import model.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArtistTest {

    private Artist riotGamesTest;
    private Artist duaLipaTest;
    private Artist daBabyTest;

    @BeforeEach
    void setUp() {
        riotGamesTest = new Artist("Riot Games");
        duaLipaTest = new Artist("Dua Lipa");
        daBabyTest = new Artist("DaBaby");

        Song songTest1 = new Song(riotGamesTest, "Legends Never Die", "./data/Legends Never Die.wav");
        Album albumTest1 = new Album(riotGamesTest, "Riot Games Album Test 1");
        albumTest1.addToListOfSongs(songTest1);

        Song songTest2 = new Song(duaLipaTest, "One Kiss", "./data/Dua Lipa - One Kiss.wav");
        Album albumTest2 = new Album(duaLipaTest, "Dua Lipa Album Test 1");
        albumTest2.addToListOfSongs(songTest2);

        Song songTest4 = new Song(riotGamesTest, "Awaken", "/data/League Of Legends - Awaken");
        Album albumTest3 = new Album(riotGamesTest, "Riot Games Album Test 2");
        albumTest3.addToListOfSongs(songTest4);


        Album albumTest4 = new Album(duaLipaTest, "Dua Lipa Album Test 2");
        Song songTest3 = new Song(duaLipaTest, "Levitating", "./data/Dua Lipa - Levitating");
        albumTest4.addToListOfSongs(songTest3);
        Song songTest5 = new Song(duaLipaTest, "Future Nostalgia", "data/Dua Lipa - Future Nostalgia.wav");
        albumTest4.addToListOfSongs(songTest5);
    }

    @Test
    void constructorTest() {
        assertEquals(riotGamesTest.getName(), "Riot Games");
        assertEquals(duaLipaTest.getName(), "Dua Lipa");
        assertEquals(daBabyTest.getName(), "DaBaby");
    }
}
