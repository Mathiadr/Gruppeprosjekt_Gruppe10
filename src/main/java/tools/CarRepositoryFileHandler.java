package tools;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import interfaces.FileHandler;
import modules.Car;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
@JsonSerialize      (as = LocalDate.class)
@JsonDeserialize    (as = LocalDate.class)
public class CarRepositoryFileHandler implements FileHandler<Car> {
    // https://stackoverflow.com/a/38731094 ???????? Mulig løsning....
    // eller https://immutables.github.io/json.html
    // Eller... https://stackabuse.com/definitive-guide-to-jackson-objectmapper-serialize-and-deserialize-java-objects/


    @Override
    public ArrayList<Car> readFromFile(String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules(new Jdk8Module(), new JavaTimeModule());


        File file = new File(filename);
        try {
            return objectMapper.readValue(file, new TypeReference<ArrayList<Car>>() {});
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