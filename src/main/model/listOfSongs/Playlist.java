package model.listOfSongs;

import model.Artist;
import model.playable.Playable;
import model.playable.Song;

import java.util.ArrayList;
import java.util.Scanner;

public class Playlist implements ListOfSongs {
    Artist artist;
    String title;

    ArrayList<Playable> songsInAlbum;

    public Playlist(Artist a, String title, ArrayList<Playable> songsInAlbum) {
        this.artist = a;
        this.title = title;
        this.songsInAlbum = songsInAlbum;
    }

    public void addSong(Song song) {
        songsInAlbum.add(song);
    }

    // EFFECTS: returns maker of the playlist
    @Override
    public Artist getArtist() {
        return artist;
    }

    @Override
    public int getPlaylistLength() {
        return songsInAlbum.size();
    }

    @Override
    public String getPlaylistTitle() {
        return title;
    }

    @Override
    public ArrayList<Playable> getListOfSongs() {
        return songsInAlbum;
    }

    @Override
    public void addToListOfSongs(Song song) {
        songsInAlbum.add(song);
    }


    public void deleteSongFromList(Song song) {

    }

    // MODIFIES: this
    // EFFECTS: add song to playlist
    //          - if the song is already in the playlist than it will ask the user if they want
    //            a duplicate in their playlist
    //             - if yes, then add the song and return true
    //             - if no, than just return false
    public Boolean addSongToList(Song song) {
        try {
            Scanner wantDuplicate = new Scanner(System.in);
            System.out.println("This song is already in your playlist, type 'y' if you want to still add, "
                    + "type 'n' otherwise.");
            String input = wantDuplicate.next();
            if (input.contains("y")) {
                songsInAlbum.add(song);
                System.out.println("Song added to " + this.title);
                return true;
            }
        } catch (Exception error) {
            return false;
        }
        return false;
    }
}
