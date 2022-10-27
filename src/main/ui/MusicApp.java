package ui;

import model.Album;
import model.Artist;
import model.ListOfPlaylists;
import model.listofsongs.Playlist;
import model.persistence.JsonRead;
import model.persistence.JsonWrite;
import model.playable.Playable;
import model.playable.Song;
import model.songstate.SongState;
import model.songstate.State;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


// Represents the main function that will run the whole application.
public class MusicApp {

    private static final String JSON_PERSISTENCE = "./data/playlists.json";
    private JsonWrite jsonWriter;
    private JsonRead jsonReader;

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

    static ArrayList<Song> allSongs;

    public MusicApp() {
        jsonWriter = new JsonWrite(JSON_PERSISTENCE);
        jsonReader = new JsonRead(JSON_PERSISTENCE);
        runMusicApp();
    }

    // MODIFIES: this
    // EFFECTS: runs the first menu, and is the first and last menu that the user will see
    private void runMusicApp() {
        initializeWorld();
        boolean keepAlive = true;
        String userInput;
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

    // https://www.youtube.com/watch?v=TErboGLHZGA&t=55s&ab_channel=MaxO%27Didily
    // REQUIRES: song to exist in the "resources" folder
    // EFFECTS: plays given .wav audio file with the utilization of  java.sound.sampled library.
    //          - creates a new File object
    //          - prints a message
    //          - the try, catch is there so that if a file isn't able to be played than the whole code will
    //            not stop running
    //              - if the song isn't able to be played than the catch block will fire a message
    private void playSong(Playable playable) {
        File musicFile = new File(playable.getFilePath());
        System.out.println("Now playing " + playable.getTitle() + " by " + playable.getArtist().getName());
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            songMenu(clip, playable);
        } catch (Exception error) {
            System.out.println("Could not play this song, try again");
        }
    }

    // EFFECTS: initializes all the songs and albums that our application has
    private void initializeWorld() {
        legendsNeverDieSong = new Song(riotGames,
                "Legends Never Die", "./data/Legends-Never-Die.wav");
        awakenLeagueSong = new Song(riotGames,
                "Awaken", "./data/League Of Legends - Awaken.wav");
        legendsAlbum = new Album(riotGames, "New Season Album");
        legendsAlbum.addToListOfSongs(awakenLeagueSong);
        legendsAlbum.addToListOfSongs(legendsNeverDieSong);
        riotGames.newAlbumsMade(legendsAlbum);

        duaLipaOneKiss = new Song(duaLipa,
                "One Kiss", "./data/Dua Lipa - One Kiss.wav");
        duaLipaLevitating = new Song(duaLipa,
                "Levitating", "./data/Dua Lipa - Levitating.wav");
        duaLipaFutureNostalgia = new Song(duaLipa,
                "Future Nostalgia", "./data/Dua Lipa - Future Nostalgia.wav");
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
    //          - if the input is not recognized by this method than the switch case is defaulted to shooting a message.
    //          - this method will actually process the userInputs of both the runMusicApp() and playlistMenu()
    //          - will not account for when the user inputs "q" in runMusicApp() because, in runMusicApp(), that is the
    //              first thing that that method will check for.
    //          - when given an input it will direct the user to either playlistMenu() for "p", allSongsMenu() for "b",
    //              printPlaylistsMenu() for "v", and createPlaylistMenu() for "c".
    private void processMenuInput(String userInput) {
        switch (userInput) {
            case "p":
                playlistMenu();
                break;
            case "b":
                allSongsMenu();
                break;
            case "v":
                printPlaylistsMenu();
                break;
            case "c":
                createPlaylistMenu();
                break;
            case "d":
                deletePlaylistMenu();
                break;
            default:
                System.out.println("-------------------------"
                        + "\n ERROR: Invalid input"
                        + "\n -------------------------");
        }
    }

    // EFFECTS: props a menu, and given a user's input will delete a playlist indexed by the user.
    //              - user's input is a number
    //              - if no playlists exist in currentPlaylists than the method will print out a message
    //              - if there are playlist than the user has the option deleting any of those playlists
    //              - the method will print out the playlists indexed from 1 but the system will delete
    //                  based from being indexed from 0
    //              - if the user's input falls outside the domain of currentPlayLists.getLength() + 1, than
    //                  a error message will pop out for them.
    @SuppressWarnings("methodlength")
    private void deletePlaylistMenu() {
        if (currentPlayLists.getLength() == 0) {
            System.out.print("-------------------------"
                    + "\n No playlists to delete"
                    + "\n -------------------------");
        } else {
            for (int x = 1; x <= currentPlayLists.getLength(); x++) {
                System.out.println(x + " -> " + currentPlayLists.getPlaylistByIndex(x - 1).getPlaylistTitle());
            }
            System.out.println((currentPlayLists.getLength() + 1) + " -> go back");
            System.out.println("Delete playlist by inputting it's given index");
            String userInput = input.next();
            try {
                if (Integer.parseInt(userInput) != (currentPlayLists.getLength() + 1)) {
                    currentPlayLists.deletePlaylist(Integer.parseInt(userInput) - 1);
                }
            } catch (Exception error) {
                System.out.println("-------------------------"
                        + "\n hello"
                        + "\n -------------------------");
            }
        }
    }

    // EFFECTS: this is the menu that is displayed for when the user is in the playlist page
    //          - This is the master while, meaning that this is the screen in which the user starts and ends at
    //          - first thing that this method checks for is when the user inputs "b", which is when the while loop
    //              is broken out of
    private void playlistMenu() {
        boolean keepAlive = true;
        while (keepAlive) {
            System.out.println("--- Select from the choices down below---");
            System.out.println(" - v -> to view all your playlists");
            System.out.println(" - c -> to create a new playlist");
            System.out.println(" - d -> to delete a playlist");
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


    // EFFECTS: Prints out all the individual playlists that the user has made during the session
    //              - if no playlists exist than the system will output a message, telling the user that there are no
    //                  songs to print out
    //              - the playlists are printed in a way so that the user will need to input the number that the
    //                  playlist is indexed at inorder operate on that playlist
    @SuppressWarnings("methodlength")
    private void printPlaylistsMenu() {
        boolean keepAlive = true;
        while (keepAlive) {
            try {
                if (currentPlayLists.getAllPlaylists().size() == 0) {
                    System.out.println("-------------------------"
                            + "\n No playlists to display"
                            + "\n -------------------------");
                    System.out.println(" c -> create a new playlist");
                    System.out.println(" b -> go back");
                    String userInput = input.next();
                    userInput = userInput.toLowerCase();
                    if (userInput.equals("c")) {
                        createPlaylistMenu();
                        keepAlive = false;
                    } else if (userInput.equals("b")) {
                        keepAlive = false;
                    }
                } else {
                    for (int x = 1; x <= currentPlayLists.getLength(); x++) {
                        System.out.println(x
                                + " " + currentPlayLists.getPlaylistByIndex(x - 1).getPlaylistTitle());
                    }
                    System.out.println("- " + (currentPlayLists.getLength() + 1) + " -> create a new playlist");
                    System.out.println("- " + (currentPlayLists.getLength() + 2) + " -> go back");
                    String userInput = input.next();
                    if (Integer.parseInt(userInput) == (currentPlayLists.getLength() + 1)) {
                        createPlaylistMenu();
                        keepAlive = false;
                    } else if (Integer.parseInt(userInput) == (currentPlayLists.getLength() + 2)) {
                        keepAlive = false;
                    } else if (Integer.parseInt(userInput) < (currentPlayLists.getLength() + 1)) {
                        playlistsMenu(Integer.parseInt(userInput));
                        keepAlive = false;
                    }
                }
            } catch (Exception error) {
                System.out.println("Something went wrong, try again.");
            }
        }
    }

    // REQUIRES: 0 < int <= currentPlaylists.getLength()
    // EFFECTS: will get a playlist in "currentPlaylists" indexed with the given integer.
    //          - if there are no playables in the playlist than a message will be displayed in the terminal
    //          - if there are 1 or more songs in that playlist than all the songs in that playlist will be printed
    //            by printPlaylistSongs(...)
    private void playlistsMenu(int i) {
        if (currentPlayLists.getPlaylistByIndex(i - 1).getPlaylistLength() == 0) {
            System.out.println("-------------------------"
                    + "\n No songs to display"
                    + "\n -------------------------");
        } else {
            printPlaylistSongs(currentPlayLists.getPlaylistByIndex(i - 1));
        }
    }

    // REQUIRES: given playlist to exist
    // EFFECTS: will print all the effective playables inside a given playlist
    //              - prints all the song as a list indexed starting from 1
    //                  - although this prints indexed starting from 1, when the method looks for the songs in the
    //                      playlist it converts the user input as if it was indexed from 0.
    //              - than the user inputs which ever song that they would like to play
    //              - the while loop is killed right after whatever the user decides to do with the song
    private void printPlaylistSongs(Playlist playlist) {
        boolean keepAlive = true;
        while (keepAlive) {
            for (int x = 1; x <= playlist.getPlaylistLength(); x++) {
                System.out.println("-------------------------"
                        + "\n" + "- " + x + " -> " + playlist.getSongByIndex(x - 1).getTitle() + " by "
                        + playlist.getSongByIndex(x - 1).getArtist().getName()
                        + "\n ------------------------");
            }
            System.out.println("Choose a song from your playlist to play, indexed by their number");
            String userInput = input.next();
            singlePlaylistMenu(userInput, playlist);
            keepAlive = false;
        }
    }

    //  EFFECTS: playlist to exist, and the userInput to be within the domain of the playlist.getPlaylistLength()
    //              - because the user inputs are indexed from 1, the system and songs inside the playlists are still
    //                  indexed from 0.
    private void singlePlaylistMenu(String userInput, Playlist playlist) {
        playSong(playlist.getSongByIndex(Integer.parseInt(userInput) - 1));
    }

    // MODIFIES: this
    // EFFECTS: menu for creating a new playlist with a given name by the user
    //          - if the playlist already exists in the user's master list of playlist than an
    //            error will be handled by a message asking them to try again
    private void createPlaylistMenu() {
        System.out.println("Enter desired playlist name:");
        String createPlaylistInput = input.next();
        if (currentPlayLists.inAllPlaylist(createPlaylistInput)) {
            System.out.println("-------------------------------------------------------------------------------------"
                    + "\n ERROR: Playlist with this title already exists, try again :("
                    + "\n ------------------------------------------------------------");
        } else {
            this.currentPlayLists.addNewPlaylist(new Playlist(createPlaylistInput));
            System.out.println("-------------------------------------------------------------------------------------"
                    + "\n Playlist successfully added!"
                    + "\n ------------------------------------------------------------");
        }
    }

    // EFFECTS: will print out all the songs that are in allSongs
    //              - all songs is to be edited by a moderator, whenever a new song is downloaded into this project
    //                  it is the moderator's job to also properly implement the new song into allSongs
    private void printAllSongs() {

        for (int x = 1; x <= allSongs.size(); x++) {
            System.out.println("------------------------"
                    + "\n" + x + " " + allSongs.get((x - 1)).getTitle()
                    + "\n by " + allSongs.get((x - 1)).getArtist().getName());
        }
        System.out.println("------------------------");
    }

    // EFFECTS: for when the user is browsing through all the songs that exist in allSongs, this method prints all
    //              from allSongs indexed from 1.
    //          - one other options are added at the end of this list for when the user wants to leave this menu
    //              - this is done by adding 1 to the allSongs.size() and printing a message indexed at
    //                  allSongs.size() + 1
    private void allSongsMenu() {
        boolean keepAlive = true;
        while (keepAlive) {
            printAllSongs();
            System.out.println((allSongs.size() + 1) + " -> go back");
            System.out.println("--- Select from the choices down above ---"
                    + "\n To play a song, input the song's indexed number");
            String allSongsMenuInput = input.next();
            try {
                int userSongMenuInputI = Integer.parseInt(allSongsMenuInput);
                if (userSongMenuInputI == (allSongs.size() + 1)) {
                    keepAlive = false;
                } else if (userSongMenuInputI <= allSongs.size()) {
                    playSong(allSongs.get(userSongMenuInputI - 1));
                } else {
                    System.out.println("Try again");
                }
            } catch (Exception error) {
                System.out.println("Something went wrong, try again");
            }
        }
    }

    //  EFFECTS: this a menu for when the user is playing a single playable.
    //              - the user is able to pause, resume, add (add song to a playlist), exit which will stop the song and
    //                  return the user to the previous menu.
    //              - this method firstly checks if the user inputs "quit" and ends the song and returning them to the
    //                  previous menu
    //              - when the userInput anything other than "quit" or "add" this method will call
    //                  processSongMenuCommand(...) providing the user input,
    //                  a Clip object (which is the actual object that is playing the song),
    //                  and a States object "songState" (which is the current state of the song).
    private void songMenu(Clip clip, Playable playable) {
        boolean keepAlive = true;
        SongState songState = new SongState();
        while (keepAlive) {
            System.out.println("-----------------------");
            System.out.println("pause -> to pause the song");
            System.out.println("resume -> to resume the song");
            System.out.println("add -> add song to your playlist");
            System.out.println("exit -> to exit the song");
            System.out.println("-----------------------");
            String userSongMenuInput = input.next();
            userSongMenuInput = userSongMenuInput.toLowerCase();
            if (userSongMenuInput.equals("exit")) {
                keepAlive = false;
                clip.stop();
            } else if (userSongMenuInput.equals("add")) {
                addingSongToPlaylist(playable);
            }
            processSongMenuCommand(userSongMenuInput, clip, songState);
        }
    }

    // MODIFIES: this
    // EFFECTS: prints all the playlists that exist for the user indexing the list from 1
    //              but again the actual indexing is from 0, we index from 1 for the convenience of the user
    //          - firstly calls printPlaylistsToAdd()
    //          - and gives 2 more options for the user to add a new playlist or back out the current menu
    //              - when the user decides to create a new playlist, this method calls createPlaylistMenu()
    //              - gets the new playlist by .getPlaylistByIndex(currentPlayLists.getLength() - 1) because
    //                  that's the index in which the new playlist exists at
    //          - if anything fails the catch block will fire an error message
    @SuppressWarnings("methodlength")
    private void addingSongToPlaylist(Playable playable) {
        boolean keepAlive = true;
        while (keepAlive) {
            printPlaylistsToAddTo();
            System.out.println((currentPlayLists.getLength() + 1) + " -> make a new playlist");
            System.out.println((currentPlayLists.getLength() + 2) + " -> go back");
            String userInput = input.next();
            try {
                int intUserInput = Integer.parseInt(userInput);
                if (intUserInput == (currentPlayLists.getLength() + 2)) {
                    keepAlive = false;
                } else if (intUserInput == (currentPlayLists.getLength() + 1)) {
                    createPlaylistMenu();
                    this.currentPlayLists.getPlaylistByIndex(
                            currentPlayLists.getLength() - 1).addToListOfSongs(playable);
                    System.out.println(" -------------------------"
                            + "\n Song added successfully!"
                            + "\n -------------------------");
                    keepAlive = false;
                } else if (1 <= intUserInput && intUserInput <= currentPlayLists.getLength()) {
                    this.currentPlayLists.getPlaylistByIndex(intUserInput - 1).addToListOfSongs(playable);
                    System.out.println(" -------------------------"
                            + "\n Song added successfully!"
                            + "\n -------------------------");
                    keepAlive = false;
                }
            } catch (Exception error) {
                System.out.println("Could not add song to playlist, try again!");
                keepAlive = false;
            }
        }
    }

    // EFFECTS: displays all possible playlist that the user may want to add a song to.
    //          - again this list is indexed starting from 1 for the convenience of the user but when the system finds
    //              the playlists it is indexed from 0.
    private void printPlaylistsToAddTo() {
        boolean keepAlive = true;
        while (keepAlive) {
            if (currentPlayLists.getLength() == 0) {
                System.out.println("No playlists to display");
            } else {
                for (int x = 1; x <= currentPlayLists.getLength(); x++) {
                    System.out.println(x + " -> " + currentPlayLists.getPlaylistByIndex(x - 1).getPlaylistTitle());
                }
            }
            keepAlive = false;
        }
    }

    // MODIFIES: this
    // EFFECTS: updates songState, and pauses or resumes the song for the user
    //              - you can only pause the song if songState.getState().equals(State.PLAYING)
    //              - you can only resume the song if songState.getState().equals(State.PAUSED)
    @SuppressWarnings("methodlength")
    private void processSongMenuCommand(String userSongMenuInput, Clip clip, SongState songState) {
        try {
            if (userSongMenuInput.equals("resume") && !songState.getState().equals(State.PAUSED)) {
                // https://rollbar.com/guides/java/how-to-throw-exceptions-in-java/#:~:text=Throwing%20an%20exception%20
                // is%20as,%2C%20server%2C%20backend%2C%20etc.
                throw new Exception("Cannot resume an already playing song");
            }
            if (userSongMenuInput.equals("pause") && !songState.getState().equals(State.PLAYING)) {
                // https://rollbar.com/guides/java/how-to-throw-exceptions-in-java/#:~:text=Throwing%20an%20exception%20
                // is%20as,%2C%20server%2C%20backend%2C%20etc.
                throw new Exception("Cannot pause an already paused song");
            }
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
            // https://www.geeksforgeeks.org/throwable-getmessage-method-in-java-with-examples/#:~:
            // text=The%20getMessage()%20method%20of,exception%20as%20a%20string%20value.&text=Return%20Value%3A%20This%
            // 20method%20returns,message%20of%20this%20Throwable%20instance.&text=%2F%2F%20the%20getMessage()%20Method.
            System.out.println("--------------------------------------"
                    + "\n" + e.getMessage()
                    + "\n -------------------------------------");
        }
    }

    public void savePlaylist() {
        try {
            jsonWriter.open();
            jsonWriter.write(currentPlayLists);
            jsonWriter.close();
            System.out.println("Saved all playlists to " + JSON_PERSISTENCE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read from file: " + JSON_PERSISTENCE);
        }
    }

    public void loadPreviousPlaylists() {
        try {
            currentPlayLists = jsonReader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Song> getAllSongs() {
        return allSongs;
    }
}
