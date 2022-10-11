import model.Artist;
import model.listOfSongs.Album;
import model.playable.Playable;
import model.playable.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ArtistTest {

    private Artist artistTest1;
    private Artist artistTest2;

    private Song songTest1;
    private Song songTest2;
    private Song songTest3;
    private Song songTest4;
    private Song songTest5;

    private Album albumTest1;
    private Album albumTest2;
    private Album albumTest3;
    private Album albumTest4;

    private ArrayList<Playable> arrayListTest1;
    private ArrayList<Playable> arrayListTest2;
    private ArrayList<Playable> arrayListTest3;
    private ArrayList<Playable> arrayListTest4;

    @BeforeEach
    void setUp() {
        artistTest1 = new Artist("Riot Games");
        artistTest2 = new Artist("Dua Lipa");


        songTest1 = new Song(artistTest1, "Legends Never Die", "./resources/Legends Never Die.wav",
                albumTest1);
        albumTest1 = new Album(artistTest1, "Riot Games Album Test 1",
                new ArrayList<>(Arrays.asList(songTest1)));
        artistTest1.newAlbumsMade(albumTest1);


        songTest2 = new Song(artistTest2, "One Kiss", "./resources/Dua Lipa - One Kiss.wav", albumTest2);
        albumTest2 = new Album(artistTest2, "Dua Lipa Album Test 1", new ArrayList<>(
                Arrays.asList(songTest2)));
        artistTest2.newAlbumsMade(albumTest2);


        songTest4 = new Song(artistTest1, "Awaken", "/resources/League Of Legends - Awaken",
                albumTest4);
        albumTest3 = new Album(artistTest1, "Riot Games Album Test 2",
                new ArrayList<>(Arrays.asList(songTest4)));
        artistTest1.newAlbumsMade(albumTest3);


        songTest3 = new Song(artistTest2, "Levitating", "./resources/Dua Lipa - Levitating", albumTest3);
        songTest5 = new Song(artistTest2, "Future Nostalgia", "stub", albumTest3);
        albumTest4 = new Album(artistTest2, "Dua Lipa Album Test 2",
                new ArrayList<>(Arrays.asList(songTest3, songTest5)));
        artistTest2.newAlbumsMade(albumTest4);

    }

    @Test
    void constructorTest() {
        assertEquals(artistTest1.getName(), "Riot Games");
        assertEquals(artistTest2.getName(), "Dua Lipa");
    }

    @Test
    void getSongsMadeTest() {
        assertEquals(artistTest1.getSongsMade(), new ArrayList<>(Arrays.asList(songTest1, songTest4)));
        assertEquals(artistTest2.getSongsMade(), new ArrayList<>(Arrays.asList(songTest2, songTest3, songTest5)));
    }

    @Test
    void newSongMadeTest() {
        Song songTest6 =  new Song(artistTest2, "That Kind of Woman", "stub", albumTest4);
        albumTest4.addToListOfSongs(songTest6);
        artistTest2.newSongMade(songTest6);

        assertTrue(albumTest4.getListOfSongs().contains(songTest6));
        assertTrue(artistTest2.getSongsMade().contains(songTest6));
    }

    @Test
    void getAlbumsMadeTest() {
        assertEquals(artistTest1.getAlbumsMade(), new ArrayList<>(Arrays.asList(albumTest1, albumTest3)));
        assertEquals(artistTest2.getAlbumsMade(),  new ArrayList<>(Arrays.asList(albumTest2, albumTest4)));
    }

    @Test
    void newAlbumsMadeTest() {
        assertFalse(artistTest2.newAlbumsMade(new Album(artistTest1, "Random Dua Lipa Album",
                new ArrayList<>())));
        assertEquals(artistTest2.getAlbumsMade(), new ArrayList<>(Arrays.asList(albumTest2, albumTest4)));
        assertEquals(artistTest2.getSongsMade(), new ArrayList<>(Arrays.asList(songTest2, songTest3, songTest5)));


        Album randonAlbumTest = new Album(artistTest2, "Random Dua Lipa Album",
                new ArrayList<>(Arrays.asList(songTest2)));
        assertTrue(artistTest1.newAlbumsMade(randonAlbumTest));
        assertEquals(artistTest1.getAlbumsMade(), new ArrayList<>(Arrays.asList(albumTest1, albumTest3,
                randonAlbumTest)));
        assertEquals(artistTest1.getSongsMade(), new ArrayList<>(Arrays.asList(songTest1, songTest4, songTest2)));

    }
}
