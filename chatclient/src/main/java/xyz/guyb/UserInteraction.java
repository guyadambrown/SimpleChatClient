package xyz.guyb;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UserInteraction {
    private final ServerConnection serverConnection;
    private ChatGUI chatGUI;

    public UserInteraction(ServerConnection serverConnection, ChatGUI chatGUI) {
        this.serverConnection = serverConnection;
        this.chatGUI = chatGUI;

    }

    public  void sendMessage(String message) {
        serverConnection.sendMessage(message);
    }

    public void receiveMessage (String message) {
        chatGUI.addChatMessage (message);

    }

}