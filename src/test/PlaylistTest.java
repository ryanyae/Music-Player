import model.Artist;
import model.Event;
import model.EventLog;
import model.Song;
import model.listofsongs.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class PlaylistTest {

    private Song songTest1;
    private Song songTest2;
    private Song songTest3;
    private Playlist playlistTest1;

    Event testEvent;
    Event testEvent2;
    Event testEvent3;

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

        testEvent = new Event("Event log cleared.");
        testEvent2 = new Event("Added song (Future Nostalgia by Dua Lipa) to Playlist Test");
        testEvent3 = new Event("Added song (Levitating by Dua Lipa) to Playlist Test");
    }

    @Test
    void testEventLog() {
        Iterator<Event> dummyList = EventLog.getInstance().iterator();
        Event dummyEvent;

        dummyEvent = dummyList.next();
        assertEquals(dummyEvent.getDescription(), "Event log cleared.");
        assertEquals(dummyEvent, testEvent);
        assertNotEquals(dummyEvent, testEvent2);
        assertNotEquals(dummyEvent, testEvent3);
        assertEquals(dummyEvent.hashCode(), testEvent.hashCode());
        assertNotEquals(dummyEvent.hashCode(), testEvent2.hashCode());
        assertNotEquals(dummyEvent.hashCode(), testEvent3.hashCode());
        assertTrue(dummyList.hasNext());

        dummyEvent = dummyList.next();
        assertNotEquals(dummyEvent, testEvent);
        assertEquals(dummyEvent, testEvent2);
        assertNotEquals(dummyEvent, testEvent3);
        assertNotEquals(dummyEvent.hashCode(), testEvent.hashCode());
        assertEquals(dummyEvent.hashCode(), testEvent2.hashCode());
        assertNotEquals(dummyEvent.hashCode(), testEvent3.hashCode());
        assertEquals(dummyEvent.getDescription(),
                "Added song (Future Nostalgia by Dua Lipa) to Playlist Test");
        assertTrue(dummyList.hasNext());

        dummyEvent = dummyList.next();
        assertNotEquals(dummyEvent, testEvent);
        assertNotEquals(dummyEvent, testEvent2);
        assertEquals(dummyEvent, testEvent3);
        assertNotEquals(dummyEvent.hashCode(), testEvent.hashCode());
        assertNotEquals(dummyEvent.hashCode(), testEvent2.hashCode());
        assertEquals(dummyEvent.hashCode(), testEvent3.hashCode());
        assertEquals(dummyEvent.getDescription(), "Added song "
                        + "(Levitating by Dua Lipa) to Playlist Test");
        assertFalse(dummyList.hasNext());
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


