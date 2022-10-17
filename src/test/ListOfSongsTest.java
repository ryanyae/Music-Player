import model.Artist;
import model.listofsongs.Album;
import model.playable.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfSongsTest {

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

    @BeforeEach
    void setUp() {
        artistTest1 = new Artist("Riot Games");
        artistTest2 = new Artist("Dua Lipa");


        songTest1 = new Song(artistTest1, "Legends Never Die", "./resources/Legends-Never-Die.wav",
                albumTest1);
        albumTest1 = new Album(artistTest1, "Legends Album");
        albumTest1.addToListOfSongs(songTest1);
        artistTest1.newAlbumsMade(albumTest1);


        songTest2 = new Song(artistTest2, "One Kiss", "./resources/Dua Lipa - One Kiss.wav", albumTest2);
        albumTest2 = new Album(artistTest2, "One Kiss Album");
        albumTest2.addToListOfSongs(songTest2);
        artistTest2.newAlbumsMade(albumTest2);


        songTest4 = new Song(artistTest1, "Awaken", "stub", albumTest4);
        albumTest3 = new Album(artistTest1, "Awaken Album");
        albumTest3.addToListOfSongs(songTest4);
        artistTest1.newAlbumsMade(albumTest3);


        songTest3 = new Song(artistTest2, "Levitating", "stub", albumTest3);
        songTest5 = new Song(artistTest2, "Future Nostalgia", "stub", albumTest3);
        albumTest4 = new Album(artistTest2, "Future Nostalgia");
        albumTest4.addToListOfSongs(songTest3);
        albumTest4.addToListOfSongs(songTest5);
        artistTest2.newAlbumsMade(albumTest4);
    }

    @Test
    void constructorTest() {
        assertEquals(albumTest1.getArtist(), artistTest1);
        assertEquals(albumTest1.getListOfSongs(), new ArrayList<>(Arrays.asList(songTest1)));
        assertEquals(albumTest1.getPlaylistLength(), 1);
        assertEquals(albumTest1.getPlaylistTitle(), "Legends Album");

        assertEquals(albumTest4.getArtist(), artistTest2);
        assertEquals(albumTest4.getListOfSongs(), new ArrayList<>(Arrays.asList(songTest3, songTest5)));
        assertEquals(albumTest4.getPlaylistLength(), 2);
        assertEquals(albumTest4.getPlaylistTitle(), "Future Nostalgia");
    }

    @Test
    void addToListOfSongsTest() {
        albumTest1.addToListOfSongs(songTest2);
        assertTrue(albumTest1.getListOfSongs().contains(songTest2));
        assertEquals(albumTest1.getPlaylistLength(), 2);

        albumTest4.addToListOfSongs(songTest2);
        assertTrue(albumTest4.getListOfSongs().contains(songTest2));
        assertTrue(albumTest4.getListOfSongs().contains(songTest3));
        assertTrue(albumTest4.getListOfSongs().contains(songTest5));
        assertFalse(albumTest4.getListOfSongs().contains(songTest4));
    }

    @Test
    void getSongByIndexTest() {
        assertEquals(albumTest1.getSongByIndex(0), songTest1);
        assertEquals(albumTest2.getSongByIndex(0), songTest2);
        assertEquals(albumTest3.getSongByIndex(0), songTest4);
        assertEquals(albumTest4.getSongByIndex(1), songTest5);
    }
}
