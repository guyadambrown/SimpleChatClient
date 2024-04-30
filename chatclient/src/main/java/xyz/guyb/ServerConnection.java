package xyz.guyb;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.Base64;

public class ServerConnection {
    private  Socket socket;
    private UserInteraction userInteraction;
    private  BufferedReader in;
    private  PrintWriter out;





    public ServerConnection(String serverAddress, int serverPort) throws IOException {
        try {
            this.socket = new Socket(serverAddress, serverPort);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // Send message function to send string messages
    public void sendMessage (String message) {
        out.println(message);
    }

    public void sendFile (File file) throws IOException {
        // Open an input stream to read from the file
        FileInputStream fileInputStream = new FileInputStream(file);

        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            int fileSize = (int) file.length();
            String filename = file.getName();
            String encodedFileName = Base64.getEncoder().encodeToString(filename.getBytes());

            out.println("/send " + fileSize + " " + encodedFileName);
            out.flush();

            // Create a byte buffer
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = bufferedInputStream.read(buffer)) > 0) {
                socket.getOutputStream().write(buffer, 0, bytesRead);
            }

        } finally {
            fileInputStream.close();
        }

    }


    public void startMessageReceiver(UserInteraction userInteraction, ChatGUI chatGUI){
        Thread receiverThread = new Thread(new Runnable() {
            @Override
            public void run() {
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
            }
        });
        receiverThread.start();
    }

    public Socket getSocket () {
        return this.socket;
    }


    public void close() throws IOException {
        socket.close();
    }
}