package gui;

import model.ListOfPlaylists;
import model.listofsongs.Playlist;
import ui.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class BrowsePlaylistMenu extends JFrame {

    JPanel mainPanel;
    JPanel topPanel;
    JPanel botPanel;

    JButton createPlaylistButton;
    JButton backButton;

    @SuppressWarnings("methodlength")
    public BrowsePlaylistMenu(JFrame frame, ListOfPlaylists listOfPlaylists) {
        JFrame finalFrame = frame;

        createPlaylistButton = new JButton("create playlist");
        backButton = new JButton("back");
        ActionListener createPlaylistAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("create Playlist...");
            }
        };
        createPlaylistButton.addActionListener(createPlaylistAction);

        ActionListener backAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        };
        backButton.addActionListener(backAction);

        mainPanel = new JPanel();
        topPanel = new JPanel();
        botPanel = new JPanel();

        botPanel.add(createPlaylistButton);
        botPanel.add(backButton);


        topPanel.setBackground(Color.WHITE);
        botPanel.setBackground(Color.GRAY);

        frame = new JFrame();
        mainPanel.setLayout(new GridLayout(0, 1));
        mainPanel.add(botPanel, 0);
        mainPanel.add(topPanel, 0);
        frame.add(mainPanel);
        frame.setSize(450,200);
        frame.setVisible(true);



        if (listOfPlaylists.getLength() == 0) {
            JLabel noPlaylistLabel = new JLabel("No Playlists to display");
            topPanel.add(noPlaylistLabel);
        } else {
            printPlaylists(listOfPlaylists);
        }
    }

    private void printPlaylists(ListOfPlaylists listOfPlaylists) {
        ArrayList<String> currentPlaylistNames = new ArrayList<>();

        for (Playlist p:listOfPlaylists.getAllPlaylists()) {
            currentPlaylistNames.add(p.getPlaylistTitle());
        }
    }
}
