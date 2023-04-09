package Shared.Objects;

import java.io.Serializable;

public class Message implements Serializable {
    private String playerName;
    private String message;
    private String time;

    public Message(String playerName, String message, String time) {
        this.playerName = playerName;
        this.message = message;
        this.time = time;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}
