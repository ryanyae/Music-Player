package gui;

import model.ListOfPlaylists;
import model.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class NewBrowseSongMenuGUI implements GUI {

    JFrame frame;
    JPanel mainPanel;
    JPanel topPanel;
    JPanel buttonPanel;

    ArrayList<Song> allSongs;
    PlaySongGUI psGUI;
    ListOfPlaylists currentPlaylists;
    private static final String[] songs = {"Legends Never Die - Riot Games", "Awaken - Riot Games",
            "One Kiss - Dua Lipa", "Levitating - Dua Lipa  ft.DaBaby", "Future Nostalgia - Dua Lipa"};
    private final JList jofAllSongs = new JList(songs);

    public NewBrowseSongMenuGUI() {
        this.allSongs = MainMenuGUI.getAllSongs();
        this.currentPlaylists = ListOfPlaylists.getInstance();
        psGUI = new PlaySongGUI();
    }

    public void view(JFrame frame) {
        this.frame = frame;
        this.frame.setTitle("Song Library");
        frame.getContentPane().removeAll();
        addFeatures();
        this.frame.revalidate();
        this.frame.repaint();
    }

    @SuppressWarnings("methodlength")
    private void addFeatures() {
        this.frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());
        this.mainPanel = new JPanel();
        this.topPanel = new JPanel();
        this.buttonPanel = new JPanel();
        JButton backButton = new JButton("back");

        buttonPanel.add(backButton);
        topPanel.add(jofAllSongs);

        mainPanel.add(topPanel);
        mainPanel.add(buttonPanel);

        this.frame.add(mainPanel);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.revalidate();
                MainMenuGUI.viewMainMenu();
                frame.repaint();
            }
        });

        // https://stackoverflow.com/questions/4344682/double-click-event-on-jlist-element
        jofAllSongs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = jofAllSongs;

                if (e.getClickCount() >= 2) {
                    int index = list.locationToIndex(e.getPoint());
                    psGUI.setSong(allSongs.get(index));
                    psGUI.setObj(NewBrowseSongMenuGUI.this);
                    psGUI.view(frame);
                }
            }
        });
    }
}
