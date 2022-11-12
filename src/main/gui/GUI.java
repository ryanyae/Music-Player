package gui;

import model.Album;
import model.Artist;
import model.Song;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

// https://www.youtube.com/watch?v=5o3fMLPY7qY&ab_channel=AlexLee
public class GUI {
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

    private static String[] songs = {"Legends Never Die - Riot Games", "Awaken - Riot Games", "One Kiss - Dua Lipa",
        "Levitating - Dua Lipa", "Future Nostalgia - Dua Lipa ft.DaBaby"};

    private static ArrayList<Song> allSongs;

    private final JList<Object> jofAllSongs;


    @SuppressWarnings("methodlength")
    public GUI() {
        initializeAllSongs();

        JFrame frame =  new JFrame("Song Player");

        JButton playlistViewButton = new JButton("view all your playlists");
        JButton browseSongLibrary = new JButton("browse our song library");
        JButton savePlaylists = new JButton("save all your playlists");
        JButton loadPreviousPlaylists = new JButton("load your previous playlists");

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
//        panel.add(playlistViewButton);
//        panel.add(browseSongLibrary);
//        panel.add(savePlaylists);
//        panel.add(loadPreviousPlaylists);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // https://www.youtube.com/watch?v=2lZqRZPgfQ4&ab_channel=choobtorials
        ActionListener playlistAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("view playlists...");
            }
        };

        //https://www.youtube.com/watch?v=2lZqRZPgfQ4&ab_channel=choobtorials
        ActionListener browseSongAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("browse songs...");
            }
        };

        //https://www.youtube.com/watch?v=2lZqRZPgfQ4&ab_channel=choobtorials
        ActionListener savePlaylistAciton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("save playlists...");
            }
        };

        //https://www.youtube.com/watch?v=2lZqRZPgfQ4&ab_channel=choobtorials
        ActionListener loadPlaylistAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("loading playlists...");
            }
        };

        // https://www.youtube.com/watch?v=2lZqRZPgfQ4&ab_channel=choobtorials
        playlistViewButton.addActionListener(playlistAction);
        browseSongLibrary.addActionListener(browseSongAction);
        savePlaylists.addActionListener(savePlaylistAciton);
        loadPreviousPlaylists.addActionListener(loadPlaylistAction);

        // https://www.youtube.com/watch?v=aLkkYbHz16E&ab_channel=thenewboston
        jofAllSongs = new JList<>(songs);
        jofAllSongs.setVisibleRowCount(5);
        jofAllSongs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        frame.add(new JScrollPane(jofAllSongs));
        jofAllSongs.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(jofAllSongs.getSelectedIndex());
                playSong(allSongs.get(jofAllSongs.getSelectedIndex()));
            }
        });
    }


    private void initializeAllSongs() {

        legendsNeverDieSong = new Song(riotGames,
                "Legends Never Die", "./data/Legends-Never-Die.wav");
        awakenLeagueSong = new Song(riotGames,
                "Awaken", "./data/League Of Legends - Awaken.wav");
        legendsAlbum = new Album(riotGames, "New Season Album");
        legendsAlbum.addToListOfSongs(awakenLeagueSong);
        legendsAlbum.addToListOfSongs(legendsNeverDieSong);

        duaLipaOneKiss = new Song(duaLipa,
                "One Kiss", "./data/Dua Lipa - One Kiss.wav");
        duaLipaLevitating = new Song(duaLipa,
                "Levitating", "./data/Dua Lipa - Levitating.wav");
        duaLipaFutureNostalgia = new Song(duaLipa,
                "Future Nostalgia", "./data/Dua Lipa - Future Nostalgia.wav");
        duaLipaLevitating.addFeature(daBaby);
        duaLipaAlbum = new Album(duaLipa, "Dua Lipa Hits");
        duaLipaAlbum.addToListOfSongs(duaLipaOneKiss);
        duaLipaAlbum.addToListOfSongs(duaLipaLevitating);
        duaLipaAlbum.addToListOfSongs(duaLipaFutureNostalgia);


        allSongs = new ArrayList<>(Arrays.asList(legendsNeverDieSong, awakenLeagueSong,
                duaLipaOneKiss, duaLipaLevitating, duaLipaFutureNostalgia));
    }

    private void playSong(Song playable) {
        File musicFile = new File(playable.getFilePath());
        System.out.println("Now playing " + playable.getTitle() + " by " + playable.getArtist().getName());
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            // songMenu(clip, playable);
        } catch (Exception error) {
            System.out.println("Could not play this song, try again");
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}
