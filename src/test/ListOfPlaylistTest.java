import model.Event;
import model.EventLog;
import model.ListOfPlaylists;
import model.listofsongs.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;


public class ListOfPlaylistTest {

    ListOfPlaylists listPlaylists;
    Playlist playlistTest1;
    Playlist playlistTest2;
    Playlist playlistTest3;
    Playlist playlistTest4;
    Playlist playlistTest5;

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

        assertEquals(dummyList.next().getDescription(), "Event log cleared.");
        assertTrue(dummyList.hasNext());

        assertEquals(dummyList.next().getDescription(), "Created new playlist named: Playlist 1");
        assertTrue(dummyList.hasNext());

        assertEquals(dummyList.next().getDescription(), "Created new playlist named: Playlist 2");
        assertTrue(dummyList.hasNext());

        assertEquals(dummyList.next().getDescription(), "Created new playlist named: Playlist 3");
        assertTrue(dummyList.hasNext());

        assertEquals(dummyList.next().getDescription(), "Created new playlist named: Playlist 4");
        assertFalse(dummyList.hasNext());
    }
}
