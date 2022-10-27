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
//        riotGamesTest.newAlbumsMade(albumTest1);

        Song songTest2 = new Song(duaLipaTest, "One Kiss", "./data/Dua Lipa - One Kiss.wav");
        Album albumTest2 = new Album(duaLipaTest, "Dua Lipa Album Test 1");
        albumTest2.addToListOfSongs(songTest2);
//        duaLipaTest.newAlbumsMade(albumTest2);

        Song songTest4 = new Song(riotGamesTest, "Awaken", "/data/League Of Legends - Awaken");
        Album albumTest3 = new Album(riotGamesTest, "Riot Games Album Test 2");
        albumTest3.addToListOfSongs(songTest4);
//        riotGamesTest.newAlbumsMade(albumTest3);


        Album albumTest4 = new Album(duaLipaTest, "Dua Lipa Album Test 2");
        Song songTest3 = new Song(duaLipaTest, "Levitating", "./data/Dua Lipa - Levitating");
        albumTest4.addToListOfSongs(songTest3);
        Song songTest5 = new Song(duaLipaTest, "Future Nostalgia", "data/Dua Lipa - Future Nostalgia.wav");
        albumTest4.addToListOfSongs(songTest5);
//        duaLipaTest.newAlbumsMade(albumTest4);
    }

    @Test
    void constructorTest() {
        assertEquals(riotGamesTest.getName(), "Riot Games");
        assertEquals(duaLipaTest.getName(), "Dua Lipa");
        assertEquals(daBabyTest.getName(), "DaBaby");
    }

//    @Test
//    void getSongsMadeTest() {
//        assertTrue(riotGamesTest.getSongsMade().contains(songTest1.getTitle()));
//        assertTrue(riotGamesTest.getSongsMade().contains(songTest4.getTitle()));
//        assertFalse(riotGamesTest.getSongsMade().contains(songTest2.getTitle()));
//        assertFalse(riotGamesTest.getSongsMade().contains(songTest3.getTitle()));
//        assertFalse(riotGamesTest.getSongsMade().contains(songTest5.getTitle()));
//
//
//        assertFalse(duaLipaTest.getSongsMade().contains(songTest1.getTitle()));
//        assertFalse(duaLipaTest.getSongsMade().contains(songTest4.getTitle()));
//        assertTrue(duaLipaTest.getSongsMade().contains(songTest2.getTitle()));
//        assertTrue(duaLipaTest.getSongsMade().contains(songTest3.getTitle()));
//        assertTrue(duaLipaTest.getSongsMade().contains(songTest5.getTitle()));
//    }
//
//    @Test
//    void newSongMadeTest() {
//        Song songTest6 =  new Song(duaLipaTest, "That Kind of Woman", "stub");
//        albumTest4.addToListOfSongs(songTest6);
//        duaLipaTest.newSongMade(songTest6);
//
//        assertTrue(albumTest4.getListOfSongs().contains(songTest6.getTitle()));
//        assertTrue(duaLipaTest.getSongsMade().contains(songTest6.getTitle()));
//    }
//
//    @Test
//    void getAlbumsMadeTest() {
//        assertTrue(riotGamesTest.getAlbumsMade().contains(albumTest1.getAlbumTitle()));
//        assertTrue(riotGamesTest.getAlbumsMade().contains(albumTest3.getAlbumTitle()));
//        assertFalse(riotGamesTest.getAlbumsMade().contains(albumTest2.getAlbumTitle()));
//        assertFalse(riotGamesTest.getAlbumsMade().contains(albumTest4.getAlbumTitle()));
//
//        assertFalse(duaLipaTest.getAlbumsMade().contains(albumTest1.getAlbumTitle()));
//        assertFalse(duaLipaTest.getAlbumsMade().contains(albumTest3.getAlbumTitle()));
//        assertTrue(duaLipaTest.getAlbumsMade().contains(albumTest2.getAlbumTitle()));
//        assertTrue(duaLipaTest.getAlbumsMade().contains(albumTest4.getAlbumTitle()));
//    }
//
//    @Test
//    void newAlbumsMadeTest() {
//        assertFalse(duaLipaTest.newAlbumsMade(new Album(duaLipaTest, "Random Dua Lipa Album")));
//
//        assertTrue(duaLipaTest.getSongsMade().contains(songTest2.getTitle()));
//        assertTrue(duaLipaTest.getSongsMade().contains(songTest3.getTitle()));
//        assertTrue(duaLipaTest.getSongsMade().contains(songTest5.getTitle()));
//
//        Album randonAlbumTest = new Album(riotGamesTest, "Random Riot Games Album");
//        randonAlbumTest.addToListOfSongs(songTest2);
//
//        assertTrue(riotGamesTest.newAlbumsMade(randonAlbumTest));
//
//        assertTrue(riotGamesTest.getAlbumsMade().contains(albumTest1.getAlbumTitle()));
//        assertTrue(riotGamesTest.getAlbumsMade().contains(albumTest3.getAlbumTitle()));
//        assertTrue(riotGamesTest.getAlbumsMade().contains(randonAlbumTest.getAlbumTitle()));
//
//        assertTrue(riotGamesTest.getSongsMade().contains(songTest1.getTitle()));
//        assertTrue(riotGamesTest.getSongsMade().contains(songTest4.getTitle()));
//        assertTrue(riotGamesTest.getSongsMade().contains(songTest2.getTitle()));
//    }
}
