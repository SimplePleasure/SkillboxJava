package main.genre;

import main.Music;
import org.springframework.stereotype.Component;

@Component
public class Rap implements Music {
    public String getGenre() {
        return "Rap";
    }
}
