import model.Artist;
import model.Album;
import model.listofsongs.Playlist;
import model.playable.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class PlaylistTest {

    private Artist duaLipaTest;
    private Song songTest1;
    private Song songTest2;
    private Song songTest3;
    private Album albumTest1;
    private Playlist playlistTest1;

    @BeforeEach
    void setUp() {
        duaLipaTest = new Artist("Dua Lipa");
        songTest1 = new Song(duaLipaTest, "Future Nostalgia", "data/Dua Lipa - Future Nostalgia.wav");
        songTest2 = new Song(duaLipaTest, "Levitating", "./data/Dua Lipa - Levitating");
        songTest3 = new Song(duaLipaTest, "One Kiss", "./data/Dua Lipa - One Kiss.wav");
        albumTest1 = new Album(duaLipaTest, "Dua Lipa Album Test");
        playlistTest1 = new Playlist("Playlist Test");

        playlistTest1.addToListOfSongs(songTest1);
        playlistTest1.addToListOfSongs(songTest2);
    }

    // regular test that will test for deleting only 1 instance of a song
    @Test
    void deleteSongFromListTest() {
        assertTrue(playlistTest1.isSongInAlbum(songTest1));

        playlistTest1.deleteSongFromList(0);

        assertFalse(playlistTest1.isSongInAlbum(songTest1));
        assertTrue(playlistTest1.isSongInAlbum(songTest2));
    }

    // test for when there are 2 instances of a song in a playlist and you only want to delete one
    // instance of it, specifically this will test when the song is indexed at 0 in the listOfSongs in the playlist.
    @Test
    void deleteSongFromListDuplicateTest() {
        playlistTest1.addToListOfSongs(songTest1);
        playlistTest1.addToListOfSongs(songTest3);

        // https://www.geeksforgeeks.org/java-util-collections-frequency-java/
        int timesAppears = Collections.frequency(playlistTest1.getListOfSongs(), songTest1);

        assertEquals(timesAppears, 2);
        playlistTest1.deleteSongFromList(0);
        assertEquals(playlistTest1.getListOfSongs().get(0), songTest2);
        assertEquals(playlistTest1.getListOfSongs().get(1), songTest1);
    }


}


