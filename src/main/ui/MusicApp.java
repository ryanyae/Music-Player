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
    ArrayList<Playable> legendsSongList;
    Album legendsAlbum;

    Artist duaLipa = new Artist("Dua Lipa");
    Song duaLipaOneKiss;
    ArrayList<Playable> oneKissSongList;
    Album duaLipaAlbum;


    public MusicApp() {
        runMusicApp();
    }

    private void runMusicApp() {

        legendsNeverDieSong = new Song(riotGames,
                "Legends Never Die", "./resources/Legends-Never-Die.wav", legendsAlbum);
        legendsAlbum = new Album(riotGames, "One Kiss Album", legendsSongList);
        legendsSongList = new ArrayList<>(Arrays.asList(legendsNeverDieSong));

        duaLipaOneKiss = new Song(duaLipa,
                "One Kiss", "./resources/Dua Lipa - One Kiss.wav", duaLipaAlbum);
        duaLipaAlbum = new Album(duaLipa, "One Kiss Album", oneKissSongList);
        oneKissSongList = new ArrayList<>(Arrays.asList(duaLipaOneKiss));
    }

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
