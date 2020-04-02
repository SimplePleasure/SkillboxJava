package main.genre;

import main.Music;
import org.springframework.stereotype.Component;

@Component
public class Rock implements Music {
    public String getGenre() {
        return "Rock";
    }
}
