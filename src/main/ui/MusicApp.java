package ui;

import model.Artist;
import model.listOfSongs.Album;
import model.playable.Playable;
import model.playable.Song;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class MusicApp {

    Artist riotGames = new Artist("Riot Games");
    Song legendsNeverDieSong;
    Song awakenLeagueSong;
    Album legendsAlbum;

    Artist duaLipa = new Artist("Dua Lipa");
    Song duaLipaOneKiss;
    Song duaLipaLevitating;
    Song duaLipaFutureNostalgia;
    Album duaLipaAlbum;

    Artist daBaby = new Artist("Da Baby");


    public MusicApp() {
        runMusicApp();
    }

    private void runMusicApp() {

        legendsNeverDieSong = new Song(riotGames,
                "Legends Never Die", "./resources/League Of Legends - Legends Never Die.wav",
                legendsAlbum);
        awakenLeagueSong = new Song(riotGames,
                "Awaken", "./resources/League Of Legends - Awaken.wav", legendsAlbum);
        legendsAlbum = new Album(riotGames, "New Season Album", new ArrayList<>(Arrays.asList(
                legendsNeverDieSong, awakenLeagueSong)));
        riotGames.newAlbumsMade(legendsAlbum);


        duaLipaOneKiss = new Song(duaLipa,
                "One Kiss", "./resources/Dua Lipa - One Kiss.wav", duaLipaAlbum);
        duaLipaLevitating = new Song(duaLipa,
                "Levitating", "./resources/Dua Lipa - Levitating.wav", duaLipaAlbum);
        duaLipaFutureNostalgia = new Song(duaLipa,
                "Future Nostalgia", "./resources/Dua Lipa - Future Nostalgia.wav", duaLipaAlbum);
        duaLipaLevitating.addFeature(daBaby);
        duaLipaAlbum = new Album(duaLipa, "Dua Lipa Hits", new ArrayList<>(Arrays.asList(duaLipaOneKiss,
                duaLipaLevitating, duaLipaFutureNostalgia)));
        duaLipa.newAlbumsMade(duaLipaAlbum);

        System.out.println(riotGames.getSongsMade());
    }

    // REQUIRES: song to exist in the "resources" folder
    // EFFECTS: plays given .wav audio file, you are able to pause and resume the song
    public static void playSong(Song song) {

        File musicFile = new File(song.getFilePath());

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            System.out.println("Now playing " + song.getTitle() + " by " + song.getArtist());

            JOptionPane.showMessageDialog(null, "Press Ok to stop playing");

        } catch (Exception error) {
            System.out.println(error);
        }
    }


}
