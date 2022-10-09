package model.playable;

import model.Artist;
import model.listOfSongs.Album;

public interface Playable {
    Artist getArtist();

    String getTitle();

    String getFilePath();

    Album getAlbum();
}
