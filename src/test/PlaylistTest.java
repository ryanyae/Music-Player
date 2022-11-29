import model.*;
import model.listofsongs.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class PlaylistTest {

    private Song songTest1;
    private Song songTest2;
    private Song songTest3;
    private Playlist playlistTest1;

    @BeforeEach
    void setUp() {
        EventLog.getInstance().clear();
        Artist duaLipaTest = new Artist("Dua Lipa");
        songTest1 = new Song(duaLipaTest, "Future Nostalgia", "data/Dua Lipa - Future Nostalgia.wav");
        songTest2 = new Song(duaLipaTest, "Levitating", "./data/Dua Lipa - Levitating");
        songTest3 = new Song(duaLipaTest, "One Kiss", "./data/Dua Lipa - One Kiss.wav");
        playlistTest1 = new Playlist("Playlist Test");

        playlistTest1.addToListOfSongs(songTest1);
        playlistTest1.addToListOfSongs(songTest2);
    }

    @Test
    void testEventLog() {
        Iterator<Event> dummyList = EventLog.getInstance().iterator();

        assertEquals(dummyList.next().getDescription(), "Event log cleared.");
        assertTrue(EventLog.getInstance().iterator().hasNext());

        dummyList.next();
        assertTrue(dummyList.hasNext());

        dummyList.next();
        assertFalse(dummyList.hasNext());
//        assertEquals(EventLog.getInstance().iterator().next().getDescription(),
//                "Added song (Future Nostalgia by Dua Lipa) to Playlist Test");
//        assertEquals(EventLog.getInstance().iterator().next().getDescription(), "Added song "
//                + "(Levitating by Dua Lipa) to Playlist Test");
//        assertFalse(EventLog.getInstance().iterator().hasNext());
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


