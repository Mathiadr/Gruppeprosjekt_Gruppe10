package tools;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.FileHandler;
import modules.Car;
import modules.CarRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CarRepositoryFileHandler implements FileHandler<Car> {

    @Override
    public ArrayList<Car> readFromFile(String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filename);
        try {
            ArrayList<Car> carArrayList = objectMapper.readValue(file, new TypeReference<ArrayList<Car>>() {});
            System.out.println(carArrayList);
            return carArrayList;
        } catch (IOException e) {
            System.err.println(e);
        }
        return null;
    }

    @Override
    public void writeArrayListToFile(ArrayList<Car> carArrayList, String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filename);

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, carArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}