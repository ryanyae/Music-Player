package model.persistence;

import model.ListOfPlaylists;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a writer that writes a ListOfPlaylists to JSON data
public class JsonWrite {
    private static final int TAB = 4;

    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs a new object that is able to write new JSON to the destination file
    public JsonWrite(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens a new write
    //          - will throw a FileNotFoundException if the destination file does not exist
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: will write JSON representation of a ListOfPlaylists to the destination file
    public void write(ListOfPlaylists listOfPlaylists) {
        JSONObject json = listOfPlaylists.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes string to the destination file
    private void saveToFile(String json) {
        writer.print(json);
    }

    // MODIFIES: this.
    // EFFECTS: closes the writer
    public void close() {
        writer.close();
    }
}
