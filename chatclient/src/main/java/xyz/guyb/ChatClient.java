package xyz.guyb;

import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.io.IOException;

public class ChatClient {


    public static void main(String[] args) {

        FlatDarculaLaf.setup();
        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf());
        } catch ( Exception e){
            System.err.println(e.getMessage());
        }


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {


                ServerConnectionSettingsDialog settingsDialog = new ServerConnectionSettingsDialog(null);
                settingsDialog.setVisible(true);

                // Get server port and address from dialog
                String serverAddress = settingsDialog.getServerAddress();
                int serverPort = settingsDialog.getServerPort();

                // Check if the user has cancelled the dialog.
                if (serverAddress == null || serverPort == -1) {
                    System.exit(0);
                }

                // Attempt to connect to the server.
                try {
                    ServerConnection serverConnection = new ServerConnection(serverAddress, serverPort);
                    if (serverConnection.getSocket() != null){
                        // If the socket has been established...
                        ChatGUI chatGUI = new ChatGUI(serverConnection);


                    }else {
                        JOptionPane.showMessageDialog(null, "An error has occurred when connecting to the server");
                        System.exit(0);
                    }
                } catch (IOException e) {
                    // Show message box when connection fails.
                    JOptionPane.showMessageDialog(null, "An error has occurred when connecting to the server " + e.getMessage());
                    System.exit(0);
                }

            }
        });
    }

}