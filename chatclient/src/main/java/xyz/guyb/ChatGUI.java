package xyz.guyb;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.*;


public class ChatGUI {


    private final JTextArea chatArea;
    private final JTextField messageInputField;

    private final UserInteraction userInteraction;

    public ChatGUI(ServerConnection serverConnection) {
        UserInteraction userInteraction = new UserInteraction(serverConnection, this);
        this.userInteraction = userInteraction;
        JFrame frame = new JFrame("Chat Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,500);
        frame.setLocationRelativeTo(null);

        // Create chat area and disable editing.
        chatArea = new JTextArea();
        chatArea.setEditable(false);

        // Enable scroll
        chatArea.setAutoscrolls(true);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        // Change caret positing when the chat ares is updated

        DefaultCaret caret = (DefaultCaret)chatArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        // Enable line wrap
        chatArea.setLineWrap(true);

        // Create input field for messages.
        messageInputField = new JTextField();

        // Create send button for sending messages.
        JButton sendButton = new JButton("Send");

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sendMessageButtonClicked();
            }
        });

        // Create panel for message input and send message button to be inline.
        JPanel inputPanel = new JPanel(new BorderLayout());

        // Create a panel to house the send button
        JPanel sendInputControls = new JPanel(new BorderLayout());

        // Add the send button towards the east
        sendInputControls.add(sendButton, BorderLayout.EAST);

        // Add elements to the main input panel
        inputPanel.add(messageInputField, BorderLayout.CENTER);
        inputPanel.add(sendInputControls, BorderLayout.EAST);

        messageInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sendMessageButtonClicked();
            }
        });

        // Add all elements to frame
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);

        // Once all elements are added, show the window.
        serverConnection.startMessageReceiver(userInteraction);

        frame.setVisible(true);


    }

    private void sendMessageButtonClicked() {
        String message = messageInputField.getText();
        if (!message.isEmpty()) {
            userInteraction.sendMessage(message);
            // Clear the chat field for a new message
            messageInputField.setText("");
        }
    }


    public void addChatMessage (String message) {
        chatArea.append(message + "\n");
    }
}