package tools;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import interfaces.FileHandler;
import modules.Car;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CarRepositoryFileHandler implements FileHandler<Car> {

    @Override
    public ArrayList<Car> readFromFile(String filename){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules(new Jdk8Module(), new JavaTimeModule());


        File file = new File(filename);
        try {
            return objectMapper.readValue(file, new TypeReference<ArrayList<Car>>() {});
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void writeArrayListToFile(ArrayList<Car> carArrayList, String filename)  {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules(new Jdk8Module(), new JavaTimeModule());
        File file = new File(filename);
        if (carArrayList.isEmpty()) throw new NullPointerException("Repository is empty");
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, carArrayList);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}