package VitaSoft.TestTask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

@RestController
public class Controller {

    @RequestMapping(value = "/getmonth/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getMonth(@PathVariable int id) {
        if (id > 0 && id <= 12) {
            String month = Month.of(id).getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")).toUpperCase().replaceAll("", "-");
            return ResponseEntity.status(HttpStatus.OK).body(month.substring(1, month.length() - 1));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("INCORRECT INPUT DATA");
        }
    }

    @RequestMapping(value = "/sort", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public ResponseEntity<String> list(@RequestParam(name = "text") String str) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray obj = (JSONArray) parser.parse(str);
            ArrayList<String> list = new ArrayList<>();

            obj.stream()
                    .sorted(Comparator.comparing((s1) -> s1.toString().length()).thenComparing(Object::toString))
                    .forEach(x -> list.add("(" + x.toString().length() + "): " + x.toString()));

            ObjectMapper objectMapper = new ObjectMapper();
            String sortedJson = objectMapper.writeValueAsString(list);
            return ResponseEntity.status(HttpStatus.OK).body(sortedJson);
        } catch (ParseException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
