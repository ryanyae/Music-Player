package gui;

import model.Album;
import model.Artist;
import model.ListOfPlaylists;
import model.Song;
import model.listofsongs.Playlist;
import model.persistence.JsonRead;
import model.persistence.JsonWrite;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

// https://www.youtube.com/watch?v=5o3fMLPY7qY&ab_channel=AlexLee
public class MainMenuGUI {
    static Artist riotGames = new Artist("Riot Games");
    static Song legendsNeverDieSong;
    static Song awakenLeagueSong;
    static Album legendsAlbum;
    static Artist duaLipa = new Artist("Dua Lipa");
    static Song duaLipaOneKiss;
    static Song duaLipaLevitating;
    static Song duaLipaFutureNostalgia;
    static Album duaLipaAlbum;
    static Artist daBaby = new Artist("Da Baby");
    private static final String JSON_PERSISTENCE = "./data/savedPlaylists.json";
    private static JsonWrite jsonWriter;
    private static JsonRead jsonReader;


    private static ArrayList<Song> allSongs;


    private static ListOfPlaylists currentPlaylists;

    JFrame frame; // main menu frame
    JFrame frame2; // playlist menu frame
    JFrame frame3; // song library menu frame

    PlaylistMenuGUI playlistMenuObj;
    BrowseSongMenuGUI browseSongMenuObj;

    @SuppressWarnings("methodlength")
    public MainMenuGUI() {
        frame = new JFrame();
        frame2 = new JFrame();
        frame3 = new JFrame();

        frame.setResizable(false);
        frame2.setResizable(false);
        frame3.setResizable(false);

        frame.setLocationRelativeTo(null);
        playlistMenuObj = new PlaylistMenuGUI(frame, frame2, currentPlaylists);
        browseSongMenuObj = new BrowseSongMenuGUI(frame, frame3, allSongs, currentPlaylists);

        JButton playlistViewButton = new JButton("view all your playlists");
        JButton browseSongLibrary = new JButton("browse our song library");
        JButton savePlaylists = new JButton("save all your playlists");
        JButton loadPreviousPlaylists = new JButton("load your previous playlists");

        GridLayout layout = new GridLayout(0, 2);

        frame.setLayout(layout);
        frame.add(playlistViewButton);
        frame.add(browseSongLibrary);
        frame.add(savePlaylists);
        frame.add(loadPreviousPlaylists);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(450, 200);
        frame.setVisible(true);

        // https://www.youtube.com/watch?v=2lZqRZPgfQ4&ab_channel=choobtorials
        ActionListener playlistAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("browse songs...");
                frame.setVisible(false);
                playlistMenuObj.setFrameVisible(true);
            }
        };

        //https://www.youtube.com/watch?v=2lZqRZPgfQ4&ab_channel=choobtorials
        ActionListener browseSongAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("browse songs...");
                frame.setVisible(false);
                browseSongMenuObj.setFrameVisible(true);
            }
        };

        //https://www.youtube.com/watch?v=2lZqRZPgfQ4&ab_channel=choobtorials
        ActionListener savePlaylistAciton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("save playlists...");
                savePlaylist();
            }
        };

        //https://www.youtube.com/watch?v=2lZqRZPgfQ4&ab_channel=choobtorials
        ActionListener loadPlaylistAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("loading playlists...");
                loadPreviousPlaylists();
            }
        };

        // https://www.youtube.com/watch?v=2lZqRZPgfQ4&ab_channel=choobtorials
        playlistViewButton.addActionListener(playlistAction);
        browseSongLibrary.addActionListener(browseSongAction);
        savePlaylists.addActionListener(savePlaylistAciton);
        loadPreviousPlaylists.addActionListener(loadPlaylistAction);
    }

    @SuppressWarnings("methodlength")
    private static void initializeAllSongs() {

        legendsNeverDieSong = new Song(riotGames,
                "Legends Never Die", "./data/Legends-Never-Die.wav",
                "./data/legendsNeverDieCoverArt.jpeg");
        awakenLeagueSong = new Song(riotGames,
                "Awaken", "./data/League Of Legends - Awaken.wav",
                "./data/awakenCoverArt.jpg");
        legendsAlbum = new Album(riotGames, "New Season Album");
        legendsAlbum.addToListOfSongs(awakenLeagueSong);
        legendsAlbum.addToListOfSongs(legendsNeverDieSong);

        duaLipaOneKiss = new Song(duaLipa,
                "One Kiss", "./data/Dua Lipa - One Kiss.wav",
                "./data/oneKissCoverArt.jpeg");
        duaLipaLevitating = new Song(duaLipa,
                "Levitating", "./data/Dua Lipa - Levitating.wav",
                "./data/duaLipaLevitatingCoverArt.png");
        duaLipaFutureNostalgia = new Song(duaLipa,
                "Future Nostalgia", "./data/Dua Lipa - Future Nostalgia.wav",
                "./data/futureNostalgiaCoverArt.jpg");
        duaLipaLevitating.addFeature(daBaby);
        duaLipaAlbum = new Album(duaLipa, "Dua Lipa Hits");
        duaLipaAlbum.addToListOfSongs(duaLipaOneKiss);
        duaLipaAlbum.addToListOfSongs(duaLipaLevitating);
        duaLipaAlbum.addToListOfSongs(duaLipaFutureNostalgia);

        allSongs = new ArrayList<>(Arrays.asList(legendsNeverDieSong, awakenLeagueSong,
                duaLipaOneKiss, duaLipaLevitating, duaLipaFutureNostalgia));
    }

    // MODIFIES: this
    // EFFECTS: will load all previous playlists to current playlists, and add it on top of already existing playlists
    public void loadPreviousPlaylists() {
        try {
            ArrayList<Playlist> loadingPlaylists = jsonReader.read().getAllPlaylists();

            for (Playlist p:loadingPlaylists) {
                currentPlaylists.addNewPlaylist(p);
            }
            System.out.println("\n" + "Loaded all playlists from " + JSON_PERSISTENCE
                    + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // MODIFIES: this
    // EFFECTS: will save currentPlaylists to a JSON file that is readily readable
    public void savePlaylist() {
        try {
            if (currentPlaylists.getLength() == 0) {
                throw new Exception("No playlists to save");
            }
            jsonWriter.open();
            jsonWriter.write(currentPlaylists);
            jsonWriter.close();
            System.out.println("\n" + "Saved all playlists to " + JSON_PERSISTENCE
                    + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read from file: " + JSON_PERSISTENCE);
        } catch (Exception e) {
            System.out.println("\n" + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        initializeAllSongs();
        currentPlaylists = new ListOfPlaylists();
        jsonWriter = new JsonWrite(JSON_PERSISTENCE);
        jsonReader = new JsonRead(JSON_PERSISTENCE);
        new MainMenuGUI();
    }
}
