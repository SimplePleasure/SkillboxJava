package main;

import java.util.ArrayList;
import java.util.List;

public class Player {

    List<Music> playerList = new ArrayList<Music>();
    String playerName;
    int volume;


    public void setPlayerList(List<Music> playerList) {
        this.playerList = playerList;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        String result = playerName + "\t" + "volume: " + volume + "\n";
        for (Music m : playerList) {
            result += m.getGenre() + "\n";
        }
        return result;
    }

}
