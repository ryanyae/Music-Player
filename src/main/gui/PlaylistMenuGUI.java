package gui;

import model.ListOfPlaylists;
import model.listofsongs.Playlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlaylistMenuGUI {

    JFrame mainFrame;
    JPanel mainPanel;
    JPanel topPanel;
    JPanel botPanel;
    ListOfPlaylists currentPlaylists;

    @SuppressWarnings("methodlength")
    public PlaylistMenuGUI(JFrame frame, JFrame frame2, ListOfPlaylists currentPlaylists) {
        this.mainFrame = frame2;
        this.mainPanel = new JPanel();
        this.topPanel = new JPanel();
        this.botPanel = new JPanel();
        this.currentPlaylists = currentPlaylists;
        JButton createPlaylistButton = new JButton("create playlist");
        JButton backButton = new JButton("back");

//        mainFrame.setVisible(false);

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

        mainPanel.setLayout(new GridLayout(0, 1));
        mainPanel.add(botPanel, 0);
        mainPanel.add(topPanel, 0);
        mainFrame.add(mainPanel);
        mainFrame.setSize(450,200);
        mainFrame.setVisible(false);

        updateVisiblePlaylists(topPanel);
    }

    @SuppressWarnings("methodlength")
    private void createPlaylistActionMenu() {
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
            panel.add(jofCurrentPlaylists);
        }
    }

    private ArrayList<String> printPlaylists() {
        ArrayList<String> currentPlaylistNames = new ArrayList<>();

        for (Playlist p:currentPlaylists.getAllPlaylists()) {
            currentPlaylistNames.add(p.getPlaylistTitle());
        }

        return currentPlaylistNames;
    }

    public void setFrameVisible(boolean b) {
        mainFrame.setVisible(b);
    }
}
