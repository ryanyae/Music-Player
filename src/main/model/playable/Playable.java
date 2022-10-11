package model.playable;

import model.Artist;
import model.listOfSongs.Album;

import java.util.ArrayList;

public interface Playable {

    Artist getArtist();

    String getTitle();

    String getFilePath();

    void addFeature(Artist artist);

    ArrayList<Artist> getFeatures();
}
