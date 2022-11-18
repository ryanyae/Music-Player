package gui;

import model.ListOfPlaylists;
import model.Song;
import model.listofsongs.Playlist;
import model.songstate.SongState;
import model.songstate.State;

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

    SongState songState;

    ArrayList<Song> allSongs;

    ListOfPlaylists currentPlaylists;
    private static final String[] songs = {"Legends Never Die - Riot Games", "Awaken - Riot Games",
            "One Kiss - Dua Lipa", "Levitating - Dua Lipa  ft.DaBaby", "Future Nostalgia - Dua Lipa"};
    private final JList jofAllSongs = new JList(songs);

    @SuppressWarnings("methodlength")
    public BrowseSongMenuGUI(JFrame frame, JFrame frame2, ArrayList<Song> allSongs, ListOfPlaylists lop) {
        this.mainFrame = frame2;
        this.allSongs = allSongs;
        this.currentPlaylists = lop;

        this.mainFrame.setLocationRelativeTo(null);
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

        playSongFrame.setLocationRelativeTo(null);
        playSongFrame.setSize(400,300);
        songTitlePanel(songMainPanel, songButtonPanel, currentSong);
        songButtonPanel(playSongFrame, songMainPanel, songTitlePanel, currentSong);
        playSongFrame.add(songMainPanel);
        playSong(currentSong);
        playSongFrame.setVisible(true);
        mainFrame.setVisible(false);
    }

    private void songTitlePanel(JPanel songMainPanel, JPanel titlePanel, Song song) {
        JLabel titleLabel = new JLabel();
        JLabel image = new JLabel(new ImageIcon(song.getImagePath()));
        titleLabel.setText(song.getTitle() + " by " + song.getArtist().getName());
        image.setSize(10,10);
        titlePanel.add(image);
        titlePanel.add(titleLabel);
        songMainPanel.add(titlePanel);
    }

    @SuppressWarnings("methodlength")
    private void songButtonPanel(JFrame frame, JPanel songMainPanel, JPanel buttonPanel, Song song) {
        songState = new SongState();
        JButton backButton = new JButton("back");
        JButton resumeButton = new JButton("play");
        JButton pauseButton = new JButton("pause");
        JButton addButton = new JButton("add");

        buttonPanel.add(backButton);
        buttonPanel.add(resumeButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(addButton);

        songMainPanel.add(buttonPanel);
        
        resumeButton.setEnabled(false);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSongPlaylistMenu(frame, song);
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
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clip.setMicrosecondPosition(songState.getTime());
                songState.setSongState(State.PLAYING);
                clip.start();
                resumeButton.setEnabled(false);
                pauseButton.setEnabled(true);
            }
        });
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                songState.setTimeStamp(clip.getMicrosecondPosition());
                songState.setSongState(State.PAUSED);
                clip.stop();
                resumeButton.setEnabled(true);
                pauseButton.setEnabled(false);
            }
        });
    }

    @SuppressWarnings("methodlength")
    private void addSongPlaylistMenu(JFrame frame, Song song) {
        JFrame playlistPopup =  new JFrame("Add song to a playlist");
        JPanel popUpMainPanel = new JPanel();
        JPanel listPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("back");

        playlistPopup.setLocationRelativeTo(null);
        if (printPlaylists().size() == 0) {
            listPanel.add(new JLabel("no playlists to add to"));
        } else {
            JList playlistListOption = new JList(printPlaylists().toArray());
            listPanel.add(playlistListOption);

            playlistListOption.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JList list = playlistListOption;

                    if (e.getClickCount() >= 2) {
                        int index = list.locationToIndex(e.getPoint());
                        currentPlaylists.getPlaylistByIndex(index).addToListOfSongs(song);
                        playlistPopup.setVisible(false);
                        frame.setVisible(true);
                    }
                }
            });
        }

        playlistPopup.setSize(200, 200);

        buttonPanel.add(backButton);
        popUpMainPanel.add(listPanel);
        popUpMainPanel.add(buttonPanel);
        playlistPopup.add(popUpMainPanel);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playlistPopup.setVisible(false);
                frame.setVisible(true);
            }
        });

        frame.setVisible(false);
        playlistPopup.setVisible(true);
    }

    private ArrayList<String> printPlaylists() {
        ArrayList<String> dummyList = new ArrayList<>();
        for (Playlist p:currentPlaylists.getAllPlaylists()) {
            dummyList.add(p.getPlaylistTitle());
        }
        return dummyList;
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
