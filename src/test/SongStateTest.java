import model.songstate.SongState;
import model.songstate.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SongStateTest {

    private SongState songStateTest1;

    @Test
    void constructorTest() {
        songStateTest1 = new SongState();
        assertEquals(songStateTest1.getState(), State.PLAYING);
        songStateTest1.setSongState(State.PAUSED);
        songStateTest1.setTimeStamp(100);

        assertEquals(songStateTest1.getState(), State.PAUSED);
        assertEquals(songStateTest1.getTime(), 100);
    }
}
