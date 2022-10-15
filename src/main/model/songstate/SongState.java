package model.songstate;

public class SongState {
    private long time;
    private State state = State.PLAYING;

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
