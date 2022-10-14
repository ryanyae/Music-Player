package ui;

import model.Artist;
import model.ListOfPlaylists;
import model.listofsongs.Album;
import model.listofsongs.Playlist;
import model.playable.Playable;
import model.playable.Song;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class MusicApp {

    Artist riotGames = new Artist("Riot Games");
    Song legendsNeverDieSong;
    Song awakenLeagueSong;
    Album legendsAlbum;

    Artist duaLipa = new Artist("Dua Lipa");
    Song duaLipaOneKiss;
    Song duaLipaLevitating;
    Song duaLipaFutureNostalgia;
    Album duaLipaAlbum;

    Artist daBaby = new Artist("Da Baby");

    Scanner input;

    ListOfPlaylists currentPlayLists = new ListOfPlaylists();


    public MusicApp() {
        runMusicApp();
    }

    private void runMusicApp() {
        initializeWorld();
        boolean keepAlive = true;
        String userInput = null;
        input = new Scanner(System.in);

        System.out.println("Welcome, select a song to listen to from our very limited list of available songs!");


        while (keepAlive) {
            initialMenu();
            userInput = input.next();
            userInput = userInput.toLowerCase();

            if (userInput.equals("q")) {
                keepAlive = false;
            } else {
                processInitialMenuInput(userInput);
            }
        }

        System.out.println("Thank you for using this application");
    }

    // REQUIRES: song to exist in the "resources" folder
    // EFFECTS: plays given .wav audio file, you are able to pause and resume the song
    private void playSong(Song song) {

        File musicFile = new File(song.getFilePath());

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            System.out.println("Now playing " + song.getTitle() + " by " + song.getArtist());

            JOptionPane.showMessageDialog(null, "Press Ok to stop playing");

        } catch (Exception error) {
            System.out.println(error);
        }
    }

    // EFFECTS: initializes all the songs and albums that our application has
    private void initializeWorld() {
        legendsNeverDieSong = new Song(riotGames,
                "Legends Never Die", "./resources/League Of Legends - Legends Never Die.wav",
                legendsAlbum);
        awakenLeagueSong = new Song(riotGames,
                "Awaken", "./resources/League Of Legends - Awaken.wav", legendsAlbum);
        legendsAlbum = new Album(riotGames, "New Season Album");
        legendsAlbum.addToListOfSongs(awakenLeagueSong);
        legendsAlbum.addToListOfSongs(legendsNeverDieSong);
        riotGames.newAlbumsMade(legendsAlbum);


        duaLipaOneKiss = new Song(duaLipa,
                "One Kiss", "./resources/Dua Lipa - One Kiss.wav", duaLipaAlbum);
        duaLipaLevitating = new Song(duaLipa,
                "Levitating", "./resources/Dua Lipa - Levitating.wav", duaLipaAlbum);
        duaLipaFutureNostalgia = new Song(duaLipa,
                "Future Nostalgia", "./resources/Dua Lipa - Future Nostalgia.wav", duaLipaAlbum);
        duaLipaLevitating.addFeature(daBaby);
        duaLipaAlbum = new Album(duaLipa, "Dua Lipa Hits");
        duaLipaAlbum.addToListOfSongs(duaLipaOneKiss);
        duaLipaAlbum.addToListOfSongs(duaLipaLevitating);
        duaLipaAlbum.addToListOfSongs(duaLipaFutureNostalgia);
        duaLipa.newAlbumsMade(duaLipaAlbum);
    }


    // EFFECTS: displays the main menu, first menu that the user will see
    private void initialMenu() {
        System.out.println("--- Select from the choices down below---");
        System.out.println(" - p -> Go to your playlists");
        System.out.println(" - b -> browse from our collection of songs");
        System.out.println(" - q -> exit the application");
    }

    // EFFECTS: this will process whatever the user inputs for the main menu
    private void processInitialMenuInput(String userInput) {
        switch (userInput) {
            case "p":
                playListMenu();
                break;
            case "b":
                // stub
                break;
            default:
                System.out.println("Invalid input try again");
        }
    }

    // EFFECTS: this is the menu that is displayed for when the user is in the playlist page
    private void playListMenu() {

        System.out.println("--- Select from the choices down below---");
        System.out.println(" - v -> to view all your playlists");
        System.out.println(" - c -> to create a new playlist");

        String playListMenuInput = input.next();
        playListMenuInput = playListMenuInput.toLowerCase();

        processPlayListMenu(playListMenuInput);
    }

    // EFFECTS: will process whatever the user input for the playlist menu
    private void processPlayListMenu(String input) {

        switch (input) {
            case "v":
                printPlaylists();
                break;
            case "c":
                createPlaylistMenu();
                break;
            default:
                System.out.println("Invalid input try again");
        }
    }

    // Prints out all the individual playlists that the user has
    private void printPlaylists() {
        if (currentPlayLists.getAllPlaylists().size() == 0) {
            System.out.println("You have no playlists");
        } else {
            for (Playlist playList: currentPlayLists.getAllPlaylists()) {
                System.out.println(playList.getPlaylistTitle());
            }
        }
    }

    private boolean printSongsInPlayList(ArrayList<Playable> playlist) {
        if (playlist.size() == 0) {
            for (Playable song : playlist) {
                System.out.println(song.getTitle() + " by " + song.getArtist().getName());
            }
        }
        return false;
    }

    private void createPlaylistMenu() {
        System.out.println("Enter desired playlist name:");
        String createPlaylistInput = input.next();

        Playlist newPlaylist = new Playlist(createPlaylistInput);

        if (!currentPlayLists.createNewPlaylist(newPlaylist)) {
            System.out.println("Playlist already exists, try again :(");
        }
    }
}
