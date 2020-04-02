package main.genre;

import main.Music;
import org.springframework.stereotype.Component;

@Component
public class Deep implements Music {
    public String getGenre() {
        return "Deep";
    }
}
