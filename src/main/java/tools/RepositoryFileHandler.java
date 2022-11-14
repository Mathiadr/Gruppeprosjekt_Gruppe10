package tools;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.FileHandler;
import modules.Car;
import modules.Listing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RepositoryFileHandler implements FileHandler<Listing> {

    @Override
    public ArrayList<Listing> readFromFile(String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filename);
        try {
            ArrayList<Listing> listingArrayList = objectMapper.readValue(file, new TypeReference<ArrayList<Listing>>(){});
            System.out.println(listingArrayList);
            return listingArrayList;
        } catch (IOException e) {
            System.err.println(e);
        }
        return null;
    }

    @Override
    public void writeArrayListToFile(ArrayList<Listing> carArrayList, String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filename);

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, carArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}