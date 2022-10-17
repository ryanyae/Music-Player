package model.songstate;

// Represents the state at which a song is at, state refers to if the song is currently PLAYING at the moment
//     or if its PAUSED. PAUSED and PLAYING are part of an enum because a song can only be in one of the two states
//     Time is the representation of when the song may be PAUSED at, if the song is PAUSED.
public class SongState {
    private long time;          // time in which the song maybe PAUSED at, if at all PAUSED
    private State state;        // PAUSED or PLAYING, state in which the song is either PAUSED or PLAYING

    // EFFECTS: creates a new SongState object that will keep track of the different states of a song
    public SongState() {
        this.state = State.PLAYING;
    }

    public void setTimeStamp(long time) {
        this.time = time;
    }

    public void setSongState(State state) {
        this.state = state;
    }

    public long getTime() {
        return time;
    }

    public State getState() {
        return state;
    }
}
