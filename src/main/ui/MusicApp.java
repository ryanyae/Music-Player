package ui;

import model.Artist;
import model.playable.Song;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicApp {

    Artist riotGames = new Artist("Riot Games");

    public MusicApp() {
        runMusicApp();
    }

    private void runMusicApp() {
        Song legendsNeverDieSong = new Song(riotGames,
                "Legends Never Die", "./resources/Legends-Never-Die.wav");
        playSong(legendsNeverDieSong);
    }

    public static void playSong(Song song) {

        File musicFile = new File(song.getFilePath());

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            System.out.println("Now playing " + song.getTitle() + " by " + song.getSongMaker());

        } catch (Exception error) {
            System.out.println(error);
        }
    }


}
