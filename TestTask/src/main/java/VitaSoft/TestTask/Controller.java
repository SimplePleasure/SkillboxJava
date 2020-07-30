package VitaSoft.TestTask;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
public class Controller {


    @GetMapping(value = "/month/{id}")
    public ResponseEntity<String> getMonth(@PathVariable int id) {
        if (id > 0 && id <= 12) {
            String month = Month.of(id).getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")).toUpperCase().replaceAll("", "-");
            return ResponseEntity.status(HttpStatus.OK).body(month.substring(1, month.length() - 1));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("INCORRECT INPUT DATA");
        }
    }


    @PostMapping(value = "/sort")
    public List<String> list(@RequestBody List<String> str) {
        return str.stream().sorted(Comparator.comparing(String::length).thenComparing(String::compareTo))
                .map(x -> "(" + x.length() + "): " + x).collect(Collectors.toList());
    }

}
