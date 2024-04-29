package xyz.guyb;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerConnectionSettingsDialog extends JDialog {

    private JTextField serverAddressField;
    private JTextField serverPortField;
    private JButton confirmButton;
    private JButton cancelButton;

    private String serverAddress;
    private int serverPort;

    public ServerConnectionSettingsDialog(Frame parent) {

        super(parent, "Connection settings", true);


        setSize(300,200);


        // Init components
        // Address
        serverAddressField = new JTextField("localhost", 8);
        JLabel serverAddressLabel = new JLabel("Address:");

        // Port
        serverPortField = new JTextField("8081");
        JLabel serverPortLabel = new JLabel("Port:");

        // Action buttons
        confirmButton = new JButton("Confirm");
        cancelButton = new JButton("Cancel");

       JPanel addressPortContainer = new JPanel();
       JPanel addressContainer = new JPanel();
       JPanel portContainer = new JPanel();

       addressPortContainer.add(addressContainer, BorderLayout.WEST);
       addressPortContainer.add(portContainer, BorderLayout.EAST);

       addressContainer.add(serverAddressLabel, BorderLayout.WEST);
       addressContainer.add(serverAddressField , BorderLayout.EAST);

       portContainer.add(serverPortLabel, BorderLayout.WEST);
       portContainer.add(serverPortField, BorderLayout.EAST);

       add(addressPortContainer, BorderLayout.CENTER);


        // New panel for buttons
        JPanel buttonPanel = new JPanel();

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add listeners
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (isValidInput()){
                    serverAddress = serverAddressField.getText();
                    serverPort = Integer.parseInt(serverPortField.getText());
                    setVisible(false);

                }else {
                    JOptionPane.showMessageDialog(ServerConnectionSettingsDialog.this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
            }
        });


    }



    private boolean isValidInput() {
        return !serverPortField.getText().isEmpty() && !serverPortField.getText().isEmpty();
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public int getServerPort() {
        return serverPort;
    }

}

