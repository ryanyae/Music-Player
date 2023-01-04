package gui;

import model.ListOfPlaylists;
import model.Song;
import model.listofsongs.Playlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ViewPlaylistMenuGUI implements GUI {

    JFrame frame;

    JPanel mainPanel;
    JPanel topPanel;
    JPanel botPanel;

    ListOfPlaylists currentPlaylists;
    PlaySongGUI psGUI;

    public ViewPlaylistMenuGUI() {
        currentPlaylists = ListOfPlaylists.getInstance();
        psGUI = PlaySongGUI.getInstance();
    }

    @Override
    public void view(JFrame frame) {
        this.frame = frame;
        this.frame.setTitle("Your Playlists");
        frame.getContentPane().removeAll();
        addFeatures();
        this.frame.revalidate();
        this.frame.repaint();
    }

    @SuppressWarnings("methodlength")
    private void addFeatures() {
        this.frame.setLocationRelativeTo(null);
        this.frame.setLayout(new GridLayout(0, 1));
        this.mainPanel = new JPanel();
        this.topPanel = new JPanel();
        this.botPanel = new JPanel();
        JButton createPlaylistButton = new JButton("create playlist");
        JButton backButton = new JButton("back");
        frame.setLocationRelativeTo(null);
        updateVisiblePlaylists();
        ActionListener createPlaylistAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                createPlaylistActionMenu();
            }
        };
        createPlaylistButton.addActionListener(createPlaylistAction);
        ActionListener backAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.revalidate();
                MainMenuGUI.viewMainMenu();
                frame.repaint();
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
        frame.add(mainPanel);
        frame.setSize(450,400);

        topPanel.remove(0);
        updateVisiblePlaylists();
    }

    // MODIFIES: this
    // EFFECTS: updates the panel that displays playlists in the main playlist menu
    //          - if there are no currentPlaylists than displays "No playlist to display"
    //          - else creates a JList of all playlists in currentPlaylists
    public void updateVisiblePlaylists() {
        if (currentPlaylists.getLength() <= 0) {
            JLabel noPlaylistLabel = new JLabel("No Playlists to display");
            topPanel.add(noPlaylistLabel);
        } else {
            JList jofCurrentPlaylists = new JList(printPlaylists().toArray());
            jofCurrentPlaylists.setVisibleRowCount(3);
            topPanel.add(jofCurrentPlaylists);
            jofCurrentPlaylists.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    if (e.getClickCount() >= 2) {
                        int index = jofCurrentPlaylists.locationToIndex(e.getPoint());
                        frame.setVisible(false);
                        menuForPlaylist(currentPlaylists.getPlaylistByIndex(index));
                    }
                }
            });
        }
    }

    // EFFECTS: will create a frame that is the menu for only a single playlist
    //          - able to play music from here
    //          - back buttons takes user back to the main playlists menu
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

                        psGUI.setSong(playlistByIndex.getSongByIndex(index));
                        psGUI.setObj(ViewPlaylistMenuGUI.this);
                        psGUI.view(frame);
                        frame.setVisible(true);
                    }
                }
            });
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                singlePlaylistMenu.setVisible(false);
            }
        });

        singlePlaylistMenu.setSize(200, 300);
        buttonPanel.add(backButton);
        mainPanel.add(losPanel);
        mainPanel.add(buttonPanel);
        singlePlaylistMenu.add(mainPanel);
        singlePlaylistMenu.setVisible(true);
    }

    // EFFECTS: will return a list of strings that represent the title of the songs inside a given playlist
    private ArrayList<String> printLOS(ArrayList<Song> songs) {
        ArrayList<String> dummyList = new ArrayList<>();
        for (Song s:songs) {
            dummyList.add(s.getTitle() + " by " + s.getArtist().getName());
        }
        return dummyList;
    }

    // EFFECTS: will return a list of strings that represent the title of the playlists from currentPlaylists
    private ArrayList<String> printPlaylists() {
        ArrayList<String> currentPlaylistNames = new ArrayList<>();

        for (Playlist p:currentPlaylists.getAllPlaylists()) {
            currentPlaylistNames.add(p.getPlaylistTitle());
        }

        return currentPlaylistNames;
    }

    // MODIFIES: this
    // EFFECTS: the menu that the user will see when trying to create a new playlist
    //          - has a text field for the user's desired title for a playlist
    //          - back buttons for the user to be able to leave this menu and back to the main playlist menu
    //          - create button that will create a new playlist that is empty with a title given by the previous text
    //            field
    @SuppressWarnings("methodlength")
    private void createPlaylistActionMenu() {
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
                updateVisiblePlaylists();
                createPlaylistFrame.setVisible(false);
                frame.setVisible(true);
            }
        };
        ActionListener backAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPlaylistFrame.setVisible(false);
                topPanel.remove(0);
                updateVisiblePlaylists();
                frame.setVisible(true);
            }
        };

        confirmButton.addActionListener(createPlaylist);
        cancelButton.addActionListener(backAction);
    }

    // EFFECTS: returns the topPanel
    public JPanel getTopPanel() {
        return topPanel;
    }
}
