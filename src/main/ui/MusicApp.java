package ui;

import model.Artist;
import model.ListOfPlaylists;
import model.songstate.SongState;
import model.listofsongs.Album;
import model.listofsongs.Playlist;
import model.playable.Song;
import model.songstate.State;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

    ListOfPlaylists currentPlayLists;

    ArrayList<Song> allSongs;


    public MusicApp() {
        runMusicApp();
    }

    private void runMusicApp() {
        initializeWorld();
        boolean keepAlive = true;
        String userInput = null;
        input = new Scanner(System.in);
        currentPlayLists = new ListOfPlaylists();

        System.out.println("Welcome, select a song to listen to from our very limited list of available songs!");

        while (keepAlive) {
            initialMenu();
            userInput = input.next();
            userInput = userInput.toLowerCase();

            if (userInput.equals("q")) {
                keepAlive = false;
            } else {
                processMenuInput(userInput);
            }
        }

        System.out.println("Thank you for using this application");
    }

    // REQUIRES: song to exist in the "resources" folder
    // EFFECTS: plays given .wav audio file, you are able to pause and resume the song
    private void playSong(Song song) throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        File musicFile = new File(song.getFilePath());

        System.out.println("Now playing " + song.getTitle() + " by " + song.getArtist().getName());
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            songMenu(clip);
        } catch (Exception error) {
            System.out.println("Could not play this song, try again");
        }
    }

    // EFFECTS: initializes all the songs and albums that our application has
    private void initializeWorld() {
        legendsNeverDieSong = new Song(riotGames,
                "Legends Never Die", "./resources/Legends-Never-Die.wav",
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

        allSongs = new ArrayList<>(Arrays.asList(legendsNeverDieSong, awakenLeagueSong,
                duaLipaOneKiss, duaLipaLevitating, duaLipaFutureNostalgia));
    }


    // EFFECTS: displays the main menu, first menu that the user will see
    private void initialMenu() {
        System.out.println("--- Select from the choices down below---");
        System.out.println(" - p -> Go to your playlists");
        System.out.println(" - b -> browse from our collection of songs");
        System.out.println(" - q -> quit the application");
    }

    // EFFECTS: this will process whatever the user inputs for the main menu
    private void processMenuInput(String userInput) {
        switch (userInput) {
            case "p" -> playListMenu();
            case "b" -> allSongsMenu();
            case "v" -> printPlaylists();
            case "c" -> createPlaylistMenu();
            default -> System.out.println("""

                    ---------------------------------------------------------Invalid input try again
                    ---------------------------------------------------------""".indent(1));
        }
    }

    // EFFECTS: this is the menu that is displayed for when the user is in the playlist page
    private void playListMenu() {
        boolean keepAlive = true;

        while (keepAlive) {
            System.out.println("--- Select from the choices down below---");
            System.out.println(" - v -> to view all your playlists");
            System.out.println(" - c -> to create a new playlist");
            System.out.println(" - b -> go back");

            String playListMenuInput = input.next();
            playListMenuInput = playListMenuInput.toLowerCase();

            if (playListMenuInput.equals("b")) {
                keepAlive = false;
            } else {
                processMenuInput(playListMenuInput);
            }
        }
    }


    // Prints out all the individual playlists that the user has
    private void printPlaylists() {
        if (currentPlayLists.getAllPlaylists().size() == 0) {
            System.out.println("You have no playlists");
        } else {
            for (Playlist playList : currentPlayLists.getAllPlaylists()) {
                System.out.println(playList.getPlaylistTitle());
            }
        }
    }

    private void createPlaylistMenu() {
        System.out.println("Enter desired playlist name:");
        String createPlaylistInput = input.next();

        if (currentPlayLists.inAllPlaylist(createPlaylistInput)) {
            System.out.println("""

                    ------------------------------------------------------------
                    ERROR: Playlist with this title already exists, try again :(
                    ------------------------------------------------------------""".indent(1));
        } else {
            currentPlayLists.createNewPlaylist(new Playlist(createPlaylistInput));
            System.out.println("""

                    ------------------------------------------------------------
                    Playlist successfully added!
                    ------------------------------------------------------------""".indent(1));
        }
    }

    private void printAllSongs() {

        for (int x = 1; x <= allSongs.size(); x++) {
            System.out.println("------------------------"
                    + "\n" + "[" + x + "]" + allSongs.get((x - 1)).getTitle()
                    + "\n by " + allSongs.get((x - 1)).getArtist().getName());
        }
        System.out.println("------------------------");
    }

    private void allSongsMenu() {
        boolean keepAlive = true;



        while (keepAlive) {
            printAllSongs();
            System.out.println((allSongs.size() + 1) + " -> go back");
            System.out.println("--- Select from the choices down above ---"
                    + "\n To play a song, input the song's indexed number");
            String allSongsMenuInput = input.next();
            String exitCommand = Integer.toString(allSongs.size() + 1);

            try {
                if (allSongsMenuInput.equals(exitCommand)) {
                    keepAlive = false;
                    break;
                }
                int userSongMenuInputI = Integer.parseInt(allSongsMenuInput);
                if (userSongMenuInputI <= allSongs.size()) {
                    try {
                        playSong(allSongs.get(userSongMenuInputI - 1));
                        //https://stackoverflow.com/questions/3495926/can-i-catch-multiple-java-exceptions-in-the-same-catch-clause
                    } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                        System.out.println("Something went wrong :(");
                    }
                } else {
                    System.out.println("try again");
                }
            } catch (Exception error) {
                System.out.println("Something went wrong, try again");
            }
        }
    }

    private void songMenu(Clip clip) {
        boolean keepAlive = true;
        SongState songState = new SongState();
        while (keepAlive) {
            System.out.println("-----------------------");
            System.out.println("pause - to pause the song");
            System.out.println("resume - to resume the song");
            System.out.println("exit - to exit the song");
            System.out.println("-----------------------");
            String userSongMenuInput = input.next();
            userSongMenuInput = userSongMenuInput.toLowerCase();

            if (userSongMenuInput.equals("exit")) {
                keepAlive = false;
                clip.stop();
            }

            processSongMenuCommand(userSongMenuInput, clip, songState);
        }
    }

    private void processSongMenuCommand(String userSongMenuInput, Clip clip, SongState songState) {
        try {
            if (userSongMenuInput.equals("pause") && songState.getState().equals(State.PLAYING)) {
                songState.setTimeStamp(clip.getMicrosecondPosition());
                songState.setSongState(State.PAUSED);
                clip.stop();
            }
            if (userSongMenuInput.equals("resume") && songState.getState().equals(State.PAUSED)) {
                clip.setMicrosecondPosition(songState.getTime());
                songState.setSongState(State.PLAYING);
                clip.start();
            }
        } catch (Exception e) {
            // https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html
            throw new RuntimeException(e);
        }

    }
}
