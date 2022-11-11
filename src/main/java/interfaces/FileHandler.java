package interfaces;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public interface FileHandler<T> {

    ArrayList<T> readFromFile(String filename);

    void writeArrayListToFile(ArrayList<T> t, String string);

}
