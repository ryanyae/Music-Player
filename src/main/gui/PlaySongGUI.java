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

import static javax.swing.JOptionPane.showMessageDialog;

public class PlaySongGUI implements GUI {

    JFrame frame;
    JPanel songMainPanel;
    JPanel songTitlePanel;
    JPanel songButtonPanel;

    Song song;

    Clip clip;

    SongState songState;

    ListOfPlaylists listOfPlaylists;

    GUI obj;

    public void setSong(Song song) {
        this.song = song;
    }

    public void setObj(GUI obj) {
        this.obj = obj;
    }

    public void view(JFrame frame) {
        this.frame = frame;
        this.frame.setSize(400, 300);
        frame.setTitle("Playing " + song.getTitle() + " by " + song.getArtist().getName());
        this.frame.getContentPane().removeAll();
        addFeatures(song);
        listOfPlaylists = ListOfPlaylists.getInstance();

        this.frame.revalidate();
        this.frame.repaint();
    }

    private void addFeatures(Song currentSong) {

        frame.setLayout(new GridLayout(0, 1));
        songMainPanel = new JPanel();
        songTitlePanel = new JPanel();
        songButtonPanel = new JPanel();
        frame.add(songMainPanel);
        playSong(currentSong);

        songTitlePanel(currentSong);
        songButtonPanel(frame, songMainPanel, songTitlePanel, currentSong);
    }

    // MODIFIES: this
    // EFFECTS: adds to a JPanel that is the title and contents of the song
    //          - will also attach an image which is the song's cover art
    private void songTitlePanel(Song song) {
        JLabel titleLabel = new JLabel();
        JLabel image = new JLabel(new ImageIcon(song.getImagePath()));
        titleLabel.setText(song.getTitle() + " by " + song.getArtist().getName());
        image.setSize(8,8);
        songButtonPanel.add(image);
        songButtonPanel.add(titleLabel);
        songMainPanel.add(songButtonPanel);
    }

    @SuppressWarnings("methodlength")
    // MODIFIES: this
    // EFFECTS: adds to a given JPanel all buttons necessary to interact with the song while it is playing
    //          - the back button will take the user back to the main browse song menu,
    //          - the pause button will pause the music, and will only be able to pressed while the
    //            song.getSongState == SongState.PLAYING
    //          - the play button will resume the music, and will only be able to pressed while the
    //            song.getSongState == SongState.PAUSED
    //          - the add button will allow the user to add the chosen song to any playlists, but this requires the user
    //            to have already made a playlist beforehand.
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
                obj.view(frame);
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
    // MODIFIES: this
    // EFFECTS: is the visual menu that the user will see inorder to be able to add a song to a chosen playlist
    //          - if no playlist exist than the panel will display "No playlists to add to"
    //          - else it will display all playlists that the user will be able to choose from
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

                    if (e.getClickCount() >= 2) {
                        int index = playlistListOption.locationToIndex(e.getPoint());
                        listOfPlaylists.getPlaylistByIndex(index).addToListOfSongs(song);
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

    // EFFECTS: returns a dummyList of strings that represent songs within a playlist
    private ArrayList<String> printPlaylists() {
        ArrayList<String> dummyList = new ArrayList<>();
        for (Playlist p:listOfPlaylists.getAllPlaylists()) {
            dummyList.add(p.getPlaylistTitle());
        }
        return dummyList;
    }

    public void playSong(Song song) {
        File musicFile = new File(song.getFilePath());
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception error) {
            showMessageDialog(null, "Could not play this song, try again");
        }
    }
}
