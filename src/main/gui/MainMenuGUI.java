package gui;

import model.Album;
import model.Artist;
import model.ListOfPlaylists;
import model.Song;
import model.listofsongs.Playlist;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

    private static final String[] songs = {"Legends Never Die - Riot Games", "Awaken - Riot Games",
            "One Kiss - Dua Lipa",
            "Levitating - Dua Lipa", "Future Nostalgia - Dua Lipa ft.DaBaby"};

    JButton createPlaylistButton;
    JButton backButton;

    private static ArrayList<Song> allSongs;

    private JList<Object> jofAllSongs;

    private static ListOfPlaylists currentPlaylists;

    JFrame frame;
    JFrame frame2;

    @SuppressWarnings("methodlength")
    public MainMenuGUI() {

        frame = new JFrame();
        frame2 = new JFrame();

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
                browsePlaylistMenu();
            }
        };

        //https://www.youtube.com/watch?v=2lZqRZPgfQ4&ab_channel=choobtorials
        ActionListener browseSongAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("browse songs...");
                // TODO
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
                // TODO
            }
        };

        // https://www.youtube.com/watch?v=2lZqRZPgfQ4&ab_channel=choobtorials
        playlistViewButton.addActionListener(playlistAction);
        browseSongLibrary.addActionListener(browseSongAction);
        savePlaylists.addActionListener(savePlaylistAciton);
        loadPreviousPlaylists.addActionListener(loadPlaylistAction);
    }


    private static void initializeAllSongs() {

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

    @SuppressWarnings("methodlength")
    public void browsePlaylistMenu() {
        JPanel mainPanel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel botPanel = new JPanel();
        createPlaylistButton = new JButton("create playlist");
        backButton = new JButton("back");
        ActionListener createPlaylistAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.setVisible(false);
                System.out.println("create Playlist...");
                JFrame createPlaylistFrame = new JFrame();
                createPlaylistFrame.setSize(200, 125);

                JPanel createMainPanel = new JPanel();
                JPanel inputPanel = new JPanel();
                JPanel inputButtonPanel = new JPanel();
                JTextField titleInput = new JTextField(12);
                JButton confirmButton = new JButton("create");
                JButton cancelButton = new JButton("back");

                inputPanel.add(titleInput);
                inputButtonPanel.add(confirmButton);
                inputButtonPanel.add(cancelButton);
                createMainPanel.add(inputPanel);
                createMainPanel.add(inputButtonPanel);
                createPlaylistFrame.add(createMainPanel);
                createPlaylistFrame.setVisible(true);

                ActionListener createPlaylist = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        currentPlaylists.addNewPlaylist(new Playlist(titleInput.getText()));
                        updateVisiblePlaylists(topPanel);
                        createPlaylistFrame.setVisible(false);
                        frame2.setVisible(true);
                    }
                };
                ActionListener backAction = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        createPlaylistFrame.setVisible(false);
                    }
                };

                confirmButton.addActionListener(createPlaylist);
                cancelButton.addActionListener(backAction);
            }
        };
        createPlaylistButton.addActionListener(createPlaylistAction);

        ActionListener backAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.setVisible(false);
                frame.setVisible(true);
            }
        };
        backButton.addActionListener(backAction);


        botPanel.add(createPlaylistButton);
        botPanel.add(backButton);


        topPanel.setBackground(Color.WHITE);
        botPanel.setBackground(Color.GRAY);

        frame2 = new JFrame();
        mainPanel.setLayout(new GridLayout(0, 1));
        mainPanel.add(botPanel, 0);
        mainPanel.add(topPanel, 0);
        frame2.add(mainPanel);
        frame2.setSize(450,200);
        frame2.setVisible(true);

        updateVisiblePlaylists(topPanel);
    }

    private void updateVisiblePlaylists(JPanel panel) {
        if (currentPlaylists.getLength() == 0) {
            JLabel noPlaylistLabel = new JLabel("No Playlists to display");
            panel.add(noPlaylistLabel);
        } else {
            jofAllSongs = new JList(printPlaylists().toArray());
            panel.remove(0);
            panel.add(jofAllSongs);
        }
    }


    private ArrayList<String> printPlaylists() {
        ArrayList<String> currentPlaylistNames = new ArrayList<>();

        for (Playlist p:currentPlaylists.getAllPlaylists()) {
            currentPlaylistNames.add(p.getPlaylistTitle());
        }

        return currentPlaylistNames;
    }

    public static void main(String[] args) {
        initializeAllSongs();
        currentPlaylists = new ListOfPlaylists();
        new MainMenuGUI();
    }
}
