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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class PlaylistMenuGUI {

    JFrame mainFrame;
    JPanel mainPanel;
    JPanel topPanel;
    JPanel botPanel;
    ListOfPlaylists currentPlaylists;

    Clip clip;
    SongState songState;

    @SuppressWarnings("methodlength")
    public PlaylistMenuGUI(JFrame frame, JFrame frame2, ListOfPlaylists currentPlaylists) {
        this.mainFrame = frame2;
        this.mainPanel = new JPanel();
        this.topPanel = new JPanel();
        this.botPanel = new JPanel();
        this.currentPlaylists = currentPlaylists;
        JButton createPlaylistButton = new JButton("create playlist");
        JButton backButton = new JButton("back");
        mainFrame.setLocationRelativeTo(null);
        updateVisiblePlaylists(topPanel);
        ActionListener createPlaylistAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                createPlaylistActionMenu();
            }
        };
        createPlaylistButton.addActionListener(createPlaylistAction);
        ActionListener backAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                frame.setVisible(true);
            }
        };
        backButton.addActionListener(backAction);
        botPanel.add(createPlaylistButton);
        botPanel.add(backButton);


        topPanel.setBackground(Color.WHITE);
        botPanel.setBackground(Color.GRAY);

        topPanel.setSize(450, 300);
        botPanel.setSize(450, 100);
        mainPanel.setLayout(new GridLayout(0, 1));
        mainPanel.add(botPanel, 0);
        mainPanel.add(topPanel, 0);
        mainFrame.add(mainPanel);
        mainFrame.setSize(450,400);
        mainFrame.setVisible(false);

        topPanel.remove(0);
        updateVisiblePlaylists(topPanel);
    }

    @SuppressWarnings("methodlength")
    private void createPlaylistActionMenu() {
        System.out.println("create Playlist...");
        JFrame createPlaylistFrame = new JFrame();
        createPlaylistFrame.setSize(200, 125);
        createPlaylistFrame.setLocationRelativeTo(null);

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
                topPanel.remove(0);
                updateVisiblePlaylists(topPanel);
                createPlaylistFrame.setVisible(false);
                mainFrame.setVisible(true);
            }
        };
        ActionListener backAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPlaylistFrame.setVisible(false);
                topPanel.remove(0);
                updateVisiblePlaylists(topPanel);
                mainFrame.setVisible(true);
            }
        };

        confirmButton.addActionListener(createPlaylist);
        cancelButton.addActionListener(backAction);

    }

    private void updateVisiblePlaylists(JPanel panel) {
        if (currentPlaylists.getLength() <= 0) {
            JLabel noPlaylistLabel = new JLabel("No Playlists to display");
            panel.add(noPlaylistLabel);
        } else {
            JList jofCurrentPlaylists = new JList(printPlaylists().toArray());
            jofCurrentPlaylists.setVisibleRowCount(3);
            panel.add(jofCurrentPlaylists);
            jofCurrentPlaylists.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JList list = jofCurrentPlaylists;

                    if (e.getClickCount() >= 2) {
                        int index = list.locationToIndex(e.getPoint());
                        mainFrame.setVisible(false);
                        menuForPlaylist(currentPlaylists.getPlaylistByIndex(index));
                    }
                }
            });
        }
    }

    @SuppressWarnings("methodlength")
    private void menuForPlaylist(Playlist playlistByIndex) {
        JFrame singlePlaylistMenu = new JFrame(playlistByIndex.getPlaylistTitle());
        JPanel mainPanel = new JPanel();
        JPanel losPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("back");
        singlePlaylistMenu.setLocationRelativeTo(null);

        if (playlistByIndex.getListOfSongs().size() == 0) {
            losPanel.add(new JLabel("No songs in the album"));
        } else {
            JList jofLos = new JList(printLOS(playlistByIndex.getListOfSongs()).toArray());
            losPanel.add(jofLos);
            jofLos.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JList list = jofLos;

                    if (e.getClickCount() >= 2) {
                        int index = list.locationToIndex(e.getPoint());
                        playlistByIndex.getListOfSongs().get(index);
                        singlePlaylistMenu.setVisible(false);
                        playSongFrame(singlePlaylistMenu, index, playlistByIndex);
                    }
                }
            });
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                singlePlaylistMenu.setVisible(false);
                mainFrame.setVisible(true);
            }
        });

        singlePlaylistMenu.setSize(200, 300);
        buttonPanel.add(backButton);
        mainPanel.add(losPanel);
        mainPanel.add(buttonPanel);
        singlePlaylistMenu.add(mainPanel);
        singlePlaylistMenu.setVisible(true);
    }

    private ArrayList<String> printLOS(ArrayList<Song> songs) {
        ArrayList<String> dummyList = new ArrayList<>();
        for (Song s:songs) {
            dummyList.add(s.getTitle() + " by " + s.getArtist().getName());
        }
        return dummyList;
    }

    private ArrayList<String> printPlaylists() {
        ArrayList<String> currentPlaylistNames = new ArrayList<>();

        for (Playlist p:currentPlaylists.getAllPlaylists()) {
            currentPlaylistNames.add(p.getPlaylistTitle());
        }

        return currentPlaylistNames;
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

    private void playSongFrame(JFrame frame, int index, Playlist p) {
        JFrame playSongFrame = new JFrame();
        JPanel songMainPanel = new JPanel();
        JPanel songTitlePanel = new JPanel();
        JPanel songButtonPanel = new JPanel();
        playSongFrame.setLocationRelativeTo(null);

        Song currentSong = p.getListOfSongs().get(index);

        playSongFrame.setSize(400,300);
        songTitlePanel(songMainPanel, songButtonPanel, currentSong);
        songButtonPanel(frame, playSongFrame, songMainPanel, songTitlePanel, currentSong);
        playSongFrame.add(songMainPanel);
        playSong(currentSong);
        playSongFrame.setVisible(true);
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
    private void songButtonPanel(JFrame frame, JFrame frame2, JPanel songMainPanel, JPanel buttonPanel, Song song) {
        songState = new SongState();
        JButton backButton = new JButton("back");
        JButton resumeButton = new JButton("play");
        JButton pauseButton = new JButton("pause");

        buttonPanel.add(backButton);
        buttonPanel.add(resumeButton);
        buttonPanel.add(pauseButton);

        songMainPanel.add(buttonPanel);

        resumeButton.setEnabled(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clip.stop();
                frame2.setVisible(false);
                frame.setVisible(true);
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

    public void setFrameVisible(boolean b) {
        mainFrame.setVisible(b);
    }
}
