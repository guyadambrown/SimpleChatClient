package xyz.guyb;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class ServerConnection {
    private  Socket socket;
    private UserInteraction userInteraction;
    private  PrintWriter out;


    public ServerConnection(String serverAddress, int serverPort) throws IOException {
        try {
            this.socket = new Socket(serverAddress, serverPort);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // Send message function to send string messages
    public void sendMessage (String message) {
        out.println(message);
    }


    public void startMessageReceiver(UserInteraction userInteraction){
        Thread receiverThread = new Thread(() -> {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = in.readLine();
                while (message != null){
                    System.out.println(message);
                    userInteraction.receiveMessage(message);
                    message = in.readLine();

                }
                System.err.println("Lost connection to the server");
                JOptionPane.showMessageDialog(null, "The client has lost connection to the server!");
                System.exit(0);
            } catch (IOException e){
                JOptionPane.showMessageDialog(null, "The client has lost connection to the server!");
                System.err.println(e.getMessage());
                System.exit(0);
            }
        });
        receiverThread.start();
    }

    public Socket getSocket () {
        return this.socket;
    }


}