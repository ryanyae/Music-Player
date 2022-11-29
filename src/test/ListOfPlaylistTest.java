import model.Event;
import model.EventLog;
import model.ListOfPlaylists;
import model.listofsongs.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;


public class ListOfPlaylistTest {

    ListOfPlaylists listPlaylists;
    Playlist playlistTest1;
    Playlist playlistTest2;
    Playlist playlistTest3;
    Playlist playlistTest4;
    Playlist playlistTest5;

    Event testEvent;
    Event testEvent2;
    Event testEvent3;
    Event testEvent4;
    Event testEvent5;

    @BeforeEach
    void setUp() {
        EventLog.getInstance().clear();
        listPlaylists = new ListOfPlaylists();
        playlistTest1 = new Playlist("Playlist 1");
        playlistTest2 = new Playlist("Playlist 2");
        playlistTest3 = new Playlist("Playlist 3");
        playlistTest4 = new Playlist("Playlist 4");
        playlistTest5 = new Playlist("Playlist 5");

        listPlaylists.addNewPlaylist(playlistTest1);
        listPlaylists.addNewPlaylist(playlistTest2);
        listPlaylists.addNewPlaylist(playlistTest3);
        listPlaylists.addNewPlaylist(playlistTest4);

        testEvent = new Event("Event log cleared.");
        testEvent2 = new Event("Created new playlist named: Playlist 1");
        testEvent3 = new Event("Created new playlist named: Playlist 2");
        testEvent4 = new Event("Created new playlist named: Playlist 3");
        testEvent5 = new Event("Created new playlist named: Playlist 4");
    }

    @Test
    void addNewPlaylistTest() {
        assertTrue(listPlaylists.getAllPlaylists().contains(playlistTest1));
        assertTrue(listPlaylists.getAllPlaylists().contains(playlistTest2));
        assertTrue(listPlaylists.getAllPlaylists().contains(playlistTest3));
        assertTrue(listPlaylists.getAllPlaylists().contains(playlistTest4));
        assertFalse(listPlaylists.getAllPlaylists().contains(playlistTest5));
    }

    @Test
    void inAllPlaylistsTest() {
        assertTrue(listPlaylists.inAllPlaylist("Playlist 1"));
        assertTrue(listPlaylists.inAllPlaylist("Playlist 2"));
        assertTrue(listPlaylists.inAllPlaylist("Playlist 3"));
        assertTrue(listPlaylists.inAllPlaylist("Playlist 4"));
        assertFalse(listPlaylists.inAllPlaylist("Playlist 5"));
    }

    @Test
    void deletePlaylistTest() {
        listPlaylists.deletePlaylist(1);
        assertFalse(listPlaylists.inAllPlaylist("Playlist 2"));
        assertTrue(listPlaylists.inAllPlaylist("Playlist 1"));
        assertTrue(listPlaylists.inAllPlaylist("Playlist 3"));
        assertTrue(listPlaylists.inAllPlaylist("Playlist 4"));
        assertFalse(listPlaylists.inAllPlaylist("Playlist 5"));
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
        assertNotEquals(dummyEvent, testEvent4);
        assertNotEquals(dummyEvent, testEvent5);
        assertEquals(dummyEvent.hashCode(), testEvent.hashCode());
        assertNotEquals(dummyEvent.hashCode(), testEvent2.hashCode());
        assertNotEquals(dummyEvent.hashCode(), testEvent3.hashCode());
        assertNotEquals(dummyEvent.hashCode(), testEvent4.hashCode());
        assertNotEquals(dummyEvent.hashCode(), testEvent5.hashCode());
        assertTrue(dummyList.hasNext());

        dummyEvent = dummyList.next();
        assertEquals(dummyEvent.getDescription(), "Created new playlist named: Playlist 1");
        assertNotEquals(dummyEvent, testEvent);
        assertEquals(dummyEvent, testEvent2);
        assertNotEquals(dummyEvent, testEvent3);
        assertNotEquals(dummyEvent, testEvent4);
        assertNotEquals(dummyEvent, testEvent5);
        assertNotEquals(dummyEvent.hashCode(), testEvent.hashCode());
        assertEquals(dummyEvent.hashCode(), testEvent2.hashCode());
        assertNotEquals(dummyEvent.hashCode(), testEvent3.hashCode());
        assertNotEquals(dummyEvent.hashCode(), testEvent4.hashCode());
        assertNotEquals(dummyEvent.hashCode(), testEvent5.hashCode());
        assertTrue(dummyList.hasNext());

        dummyEvent = dummyList.next();
        assertEquals(dummyEvent.getDescription(), "Created new playlist named: Playlist 2");
        assertNotEquals(dummyEvent, testEvent);
        assertNotEquals(dummyEvent, testEvent2);
        assertEquals(dummyEvent, testEvent3);
        assertNotEquals(dummyEvent, testEvent4);
        assertNotEquals(dummyEvent, testEvent5);
        assertNotEquals(dummyEvent.hashCode(), testEvent.hashCode());
        assertNotEquals(dummyEvent.hashCode(), testEvent2.hashCode());
        assertEquals(dummyEvent.hashCode(), testEvent3.hashCode());
        assertNotEquals(dummyEvent.hashCode(), testEvent4.hashCode());
        assertNotEquals(dummyEvent.hashCode(), testEvent5.hashCode());
        assertTrue(dummyList.hasNext());

        dummyEvent = dummyList.next();
        assertEquals(dummyEvent.getDescription(), "Created new playlist named: Playlist 3");
        assertNotEquals(dummyEvent, testEvent);
        assertNotEquals(dummyEvent, testEvent2);
        assertNotEquals(dummyEvent, testEvent3);
        assertEquals(dummyEvent, testEvent4);
        assertNotEquals(dummyEvent, testEvent5);
        assertNotEquals(dummyEvent.hashCode(), testEvent.hashCode());
        assertNotEquals(dummyEvent.hashCode(), testEvent2.hashCode());
        assertNotEquals(dummyEvent.hashCode(), testEvent3.hashCode());
        assertEquals(dummyEvent.hashCode(), testEvent4.hashCode());
        assertNotEquals(dummyEvent.hashCode(), testEvent5.hashCode());
        assertTrue(dummyList.hasNext());

        dummyEvent = dummyList.next();
        assertEquals(dummyEvent.getDescription(), "Created new playlist named: Playlist 4");
        assertNotEquals(dummyEvent, testEvent);
        assertNotEquals(dummyEvent, testEvent2);
        assertNotEquals(dummyEvent, testEvent3);
        assertNotEquals(dummyEvent, testEvent4);
        assertEquals(dummyEvent, testEvent5);
        assertNotEquals(dummyEvent.hashCode(), testEvent.hashCode());
        assertNotEquals(dummyEvent.hashCode(), testEvent2.hashCode());
        assertNotEquals(dummyEvent.hashCode(), testEvent3.hashCode());
        assertNotEquals(dummyEvent.hashCode(), testEvent4.hashCode());
        assertEquals(dummyEvent.hashCode(), testEvent5.hashCode());
        assertFalse(dummyList.hasNext());
    }
}
