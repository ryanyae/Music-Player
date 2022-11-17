package gui;

import model.Song;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class BrowseSongMenuGUI {

    JFrame mainFrame;
    JPanel mainPanel;
    JPanel topPanel;
    JPanel buttonPanel;

    Clip clip;

    ArrayList<Song> allSongs;
    private static final String[] songs = {"Legends Never Die - Riot Games", "Awaken - Riot Games",
            "One Kiss - Dua Lipa", "Levitating - Dua Lipa", "Future Nostalgia - Dua Lipa ft.DaBaby"};
    private final JList jofAllSongs = new JList(songs);

    @SuppressWarnings("methodlength")
    public BrowseSongMenuGUI(JFrame frame, JFrame frame2, ArrayList<Song> allSongs) {
        this.mainFrame = frame2;
        this.allSongs = allSongs;

        this.mainFrame.setSize(300,200);
        this.mainPanel = new JPanel();
        this.topPanel = new JPanel();
        this.buttonPanel = new JPanel();
        JButton backButton = new JButton("back");

        buttonPanel.add(backButton);
        topPanel.add(jofAllSongs);

        mainPanel.add(topPanel);
        mainPanel.add(buttonPanel);

        this.mainFrame.add(mainPanel);

        this.mainFrame.setVisible(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                frame.setVisible(true);
            }
        });

        // https://stackoverflow.com/questions/4344682/double-click-event-on-jlist-element
        jofAllSongs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = jofAllSongs;

                if (e.getClickCount() >= 2) {
                    int index = list.locationToIndex(e.getPoint());
                    playSongFrame(index);
                }
            }
        });
    }

    private void playSongFrame(int index) {
        JFrame playSongFrame = new JFrame();
        JPanel songMainPanel = new JPanel();
        JPanel songTitlePanel = new JPanel();
        JPanel songButtonPanel = new JPanel();
        Song currentSong = allSongs.get(index);

        playSongFrame.setSize(200,100);
        songTitlePanel(playSongFrame, songMainPanel, songButtonPanel, currentSong);
        songButtonPanel(playSongFrame, songMainPanel, songTitlePanel, currentSong);
        playSongFrame.add(songMainPanel);
        playSong(currentSong);
        playSongFrame.setVisible(true);
        mainFrame.setVisible(false);
    }

    private void songTitlePanel(JFrame frame, JPanel songMainPanel, JPanel titlePanel, Song song) {
        JLabel titleLabel = new JLabel();
        titleLabel.setText(song.getTitle() + " by " + song.getArtist().getName());
        titlePanel.add(titleLabel);
        songMainPanel.add(titlePanel);
    }

    private void songButtonPanel(JFrame frame, JPanel songMainPanel, JPanel buttonPanel, Song song) {
        handlePlayPause(songMainPanel, buttonPanel, song);
        JButton addButton = new JButton("add");
        JButton backButton = new JButton("back");
        songMainPanel.add(addButton);
        songMainPanel.add(backButton);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSongPlaylistMenu(song);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clip.stop();
                frame.setVisible(false);
                mainFrame.setVisible(true);
            }
        });
    }

    private void addSongPlaylistMenu(Song song) {
        // TODO
    }

    private void handlePlayPause(JPanel songMainPanel, JPanel buttonPanel, Song song) {
        // TODO
    }

    private void playSong(Song playable) {
        File musicFile = new File(playable.getFilePath());
        System.out.println("Now playing " + playable.getTitle() + " by " + playable.getArtist().getName());
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception error) {
            System.out.println("Could not play this song, try again");
        }
    }

    public void setFrameVisible(Boolean b) {
        this.mainFrame.setVisible(b);
    }
}
