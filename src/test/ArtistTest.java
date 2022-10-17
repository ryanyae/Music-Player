import model.Artist;
import model.listofsongs.Album;
import model.playable.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ArtistTest {

    private Artist riotGamesTest;
    private Artist duaLipaTest;
    private Artist daBabyTest;

    private Song songTest1;
    private Song songTest2;
    private Song songTest3;
    private Song songTest4;
    private Song songTest5;

    private Album albumTest1;
    private Album albumTest2;
    private Album albumTest3;
    private Album albumTest4;

    @BeforeEach
    void setUp() {
        riotGamesTest = new Artist("Riot Games");
        duaLipaTest = new Artist("Dua Lipa");
        daBabyTest = new Artist("DaBaby");

        songTest1 = new Song(riotGamesTest, "Legends Never Die", "./resources/Legends Never Die.wav",
                albumTest1);
        albumTest1 = new Album(riotGamesTest, "Riot Games Album Test 1");
        albumTest1.addToListOfSongs(songTest1);
        riotGamesTest.newAlbumsMade(albumTest1);

        songTest2 = new Song(duaLipaTest, "One Kiss", "./resources/Dua Lipa - One Kiss.wav", albumTest2);
        albumTest2 = new Album(duaLipaTest, "Dua Lipa Album Test 1");
        albumTest2.addToListOfSongs(songTest2);
        duaLipaTest.newAlbumsMade(albumTest2);

        songTest4 = new Song(riotGamesTest, "Awaken", "/resources/League Of Legends - Awaken",
                albumTest3);
        albumTest3 = new Album(riotGamesTest, "Riot Games Album Test 2");
        albumTest3.addToListOfSongs(songTest4);
        riotGamesTest.newAlbumsMade(albumTest3);


        albumTest4 = new Album(duaLipaTest, "Dua Lipa Album Test 2");
        songTest3 = new Song(duaLipaTest, "Levitating", "./resources/Dua Lipa - Levitating", albumTest4);
        albumTest4.addToListOfSongs(songTest3);
        songTest5 = new Song(duaLipaTest, "Future Nostalgia", "resources/Dua Lipa - Future Nostalgia.wav",
                albumTest4);
        albumTest4.addToListOfSongs(songTest5);
        duaLipaTest.newAlbumsMade(albumTest4);
    }

    @Test
    void constructorTest() {
        assertEquals(riotGamesTest.getName(), "Riot Games");
        assertEquals(duaLipaTest.getName(), "Dua Lipa");
        assertEquals(daBabyTest.getName(), "DaBaby");
    }

    @Test
    void getSongsMadeTest() {
        assertEquals(riotGamesTest.getSongsMade(), new ArrayList<>(Arrays.asList(songTest1, songTest4)));
        assertEquals(duaLipaTest.getSongsMade(), new ArrayList<>(Arrays.asList(songTest2, songTest3, songTest5)));
    }

    @Test
    void newSongMadeTest() {
        Song songTest6 =  new Song(duaLipaTest, "That Kind of Woman", "stub", albumTest4);
        albumTest4.addToListOfSongs(songTest6);
        duaLipaTest.newSongMade(songTest6);

        assertTrue(albumTest4.getListOfSongs().contains(songTest6));
        assertTrue(duaLipaTest.getSongsMade().contains(songTest6));
    }

    @Test
    void getAlbumsMadeTest() {
        assertEquals(riotGamesTest.getAlbumsMade(), new ArrayList<>(Arrays.asList(albumTest1, albumTest3)));
        assertEquals(duaLipaTest.getAlbumsMade(),  new ArrayList<>(Arrays.asList(albumTest2, albumTest4)));
    }

    @Test
    void newAlbumsMadeTest() {
        assertFalse(duaLipaTest.newAlbumsMade(new Album(riotGamesTest, "Random Dua Lipa Album")));
        assertEquals(duaLipaTest.getAlbumsMade(), new ArrayList<>(Arrays.asList(albumTest2, albumTest4)));
        assertEquals(duaLipaTest.getSongsMade(), new ArrayList<>(Arrays.asList(songTest2, songTest3, songTest5)));


        Album randonAlbumTest = new Album(duaLipaTest, "Random Dua Lipa Album");

        randonAlbumTest.addToListOfSongs(songTest2);
        assertTrue(riotGamesTest.newAlbumsMade(randonAlbumTest));
        assertEquals(riotGamesTest.getAlbumsMade(), new ArrayList<>(Arrays.asList(albumTest1, albumTest3,
                randonAlbumTest)));
        assertEquals(riotGamesTest.getSongsMade(), new ArrayList<>(Arrays.asList(songTest1, songTest4, songTest2)));

    }
}
