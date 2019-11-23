import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Loader {                                   //Модуль 10, урок 5 JSON READER

    public static void main (String[] args) throws IOException, ParseException {
        String path = "res/EXPORT.json";
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(new String(Files.readAllBytes(Paths.get(path))));
        for (Object key : object.keySet()) {
            JSONObject value = (JSONObject) object.get(key);
            System.out.println(value.get("Name") +": "+ value.get("Phone"));
        }
    }
}
