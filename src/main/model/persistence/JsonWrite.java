package model.persistence;

import model.ListOfPlaylists;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWrite {
    private static final int TAB = 4;

    private PrintWriter writer;
    private String destination;

    public JsonWrite(String destination) {
        this.destination = destination;
    }

    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    public void write(ListOfPlaylists listOfPlaylists) {
        JSONObject json = listOfPlaylists.toJson();
        saveToFile(json.toString(TAB));
    }

    private void saveToFile(String json) {
        writer.print(json);
    }

    public void close() {
        writer.close();
    }
}
