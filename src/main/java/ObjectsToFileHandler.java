import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ObjectsToFileHandler {
    static void SaveDataToFile(ArrayList<Object> objectList, String filename){
        JSONObject jsonObject = new JSONObject();
        for (Object o : objectList){
            jsonObject.put("TEMPLATE_VARIABLE", "TEMPLATE_VALUE");
        }
        try{
            FileWriter fileWriter = new FileWriter(filename);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
