package Client.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Client.GameClient;

public class FindServer implements Serializable{

    private JFrame frame;
    private int frameWidth;
    private int frameHeight;
    private GameClient client;
    private JLabel serverAddressLbl;
    private JTextField serverAddressTF;
    private JLabel serverAddressErrorLbl;
    private JLabel serverPortLbl;
    private JTextField serverPortTF;
    private JLabel serverPortErrorLbl;
    private JLabel playerNameLbl;
    private JTextField playerNameTF;
    private JLabel playerNameErrorLbl;
    private JLabel playerColorLbl;
    private JButton colorBtn;
    private JLabel playerColorErrorLbl;

    private Color playerColor;

    public FindServer(int width, int height, GameClient client) {
        // Set Variables
        frameWidth = width;
        frameHeight = height;
        this.client = client;
        int yOffset = -60;
        playerColor = Color.WHITE;

        // Frame
        frame = new JFrame("Fakeopoly - Find Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setLocationRelativeTo(null); // Centers screen
        frame.setResizable(false);

        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Components
        // Server address
        serverAddressLbl = new JLabel();
        serverAddressLbl.setBounds(frameWidth / 2 - 155, yOffset + 100, 300, 30);
        serverAddressLbl.setText("Server Address:");

        serverAddressTF = new JTextField();
        serverAddressTF.setBounds(frameWidth / 2 - 155, yOffset + 130, 300, 30);

        serverAddressErrorLbl = new JLabel();
        serverAddressErrorLbl.setBounds(frameWidth / 2 - 155, yOffset + 160, 300, 30);
        serverAddressErrorLbl.setText("Invalid Server Address!");
        serverAddressErrorLbl.setForeground(Color.RED);
        serverAddressErrorLbl.setVisible(false);

        // Server Port
        serverPortLbl = new JLabel();
        serverPortLbl.setBounds(frameWidth / 2 - 155, yOffset + 180, 300, 30);
        serverPortLbl.setText("Server Port:");

        serverPortTF = new JTextField();
        serverPortTF.setBounds(frameWidth / 2 - 155, yOffset + 210, 300, 30);

        serverPortErrorLbl = new JLabel();
        serverPortErrorLbl.setBounds(frameWidth / 2 - 155, yOffset + 240, 300, 30);
        serverPortErrorLbl.setText("Invalid Server Port!");
        serverPortErrorLbl.setForeground(Color.RED);
        serverPortErrorLbl.setVisible(false);

        // Player Name
        playerNameLbl = new JLabel();
        playerNameLbl.setBounds(frameWidth / 2 - 155, yOffset + 260, 300, 30);
        playerNameLbl.setText("Player Name:");

        playerNameTF = new JTextField();
        playerNameTF.setBounds(frameWidth / 2 - 155, yOffset + 290, 300, 30);

        playerNameErrorLbl = new JLabel();
        playerNameErrorLbl.setBounds(frameWidth / 2 - 155, yOffset + 320, 300, 30);
        playerNameErrorLbl.setText("Invalid Player Name!");
        playerNameErrorLbl.setForeground(Color.RED);
        playerNameErrorLbl.setVisible(false);

        // Player Color
        playerColorLbl = new JLabel();
        playerColorLbl.setBounds(frameWidth / 2 - 155, yOffset + 340, 300, 30);
        playerColorLbl.setText("Player Color:");

        colorBtn = new JButton("Pick a Color:");
        colorBtn.setBounds(frameWidth / 2 - 155, yOffset + 370, 300, 40);
        colorBtn.setBackground(playerColor);
        colorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colorBtnAction();
            }
        });

        playerColorErrorLbl = new JLabel();
        playerColorErrorLbl.setBounds(frameWidth / 2 - 155, yOffset + 400, 300, 30);
        playerColorErrorLbl.setText("Invalid Player Color!");
        playerColorErrorLbl.setForeground(Color.RED);
        playerColorErrorLbl.setVisible(false);

        // Buttons
        JButton joinBtn = new JButton("Join");
        joinBtn.setBounds(frameWidth / 2 - 220, 420, 200, 40);
        joinBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joinBtnAction();
            }
        });

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(frameWidth / 2 + 20, 420, 200, 40);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backBtnAction();
            }
        });

        // Add Components to Panel
        panel.add(serverAddressLbl, BorderLayout.CENTER);
        panel.add(serverAddressTF, BorderLayout.CENTER);
        panel.add(serverAddressErrorLbl, BorderLayout.CENTER);
        panel.add(serverPortLbl, BorderLayout.CENTER);
        panel.add(serverPortTF, BorderLayout.CENTER);
        panel.add(serverPortErrorLbl, BorderLayout.CENTER);
        panel.add(playerNameLbl, BorderLayout.CENTER);
        panel.add(playerNameTF, BorderLayout.CENTER);
        panel.add(playerNameErrorLbl, BorderLayout.CENTER);
        panel.add(playerColorLbl, BorderLayout.CENTER);
        panel.add(colorBtn, BorderLayout.CENTER);
        panel.add(playerColorErrorLbl, BorderLayout.CENTER);
        panel.add(joinBtn, BorderLayout.CENTER);
        panel.add(backBtn, BorderLayout.CENTER);

        // Add Panel to Frame
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    // Action when user clicks back button
    private void joinBtnAction() {
        System.out.println("Attempting to join Game...");
        boolean hasErrors = false;
        int serverPort = -1;
        if (serverAddressTF.getText() == null || serverAddressTF.getText().equals("")) {
            serverAddressErrorLbl.setText("Server Address is required to connect!");
            serverAddressErrorLbl.setVisible(true);
            hasErrors = true;
        } else {
            serverAddressErrorLbl.setVisible(false);
        }
        if (serverPortTF.getText() == null || serverPortTF.getText().equals("")) {
            serverPortErrorLbl.setText("Server Port is required to connect!");
            serverPortErrorLbl.setVisible(true);
            hasErrors = true;
        } else {
            try {
                serverPort = Integer.parseInt(serverPortTF.getText());
                serverPortErrorLbl.setVisible(false);
            } catch (Exception e) {
                System.out.println(e);
                serverPortErrorLbl.setText("Server Port must only contain digits!");
                serverPortErrorLbl.setVisible(true);
                hasErrors = true;
            }
        }
        if (playerNameTF.getText() == null || playerNameTF.getText().equals("")
                || playerNameTF.getText().length() >= 25) {
            playerNameErrorLbl.setText("Player name is Invalid, ensure it is less than 25 chars!");
            playerNameErrorLbl.setVisible(true);
            hasErrors = true;
        } else {
            playerNameErrorLbl.setVisible(false);
        }
        if (playerColor == null) {
            playerColorErrorLbl.setText("Please select a player color!");
            playerColorErrorLbl.setVisible(true);
            hasErrors = true;
        } else {
            playerColorErrorLbl.setVisible(false);
        }

        if (hasErrors != true) {
            client.setServerAddress(serverAddressTF.getText());
            client.setServerPort(serverPort);

            try {
                if (!client.connectToServer(playerNameTF.getText(), playerColor))
                    throw new Exception("Connection to server failed.");
                client.openGameLobby();
                frame.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e);
            }
        }
    }

    // Action when user clicks back button
    private void backBtnAction() {
        System.out.println("Opening Main Menu page.");
        client.openMainMenu();
        frame.dispose();
    }

    // Action when user clicks color button
    private void colorBtnAction() {
        playerColor = JColorChooser.showDialog(
                frame,
                "Choose Background Color",
                playerColor);
        colorBtn.setBackground(playerColor);
    }

    // Gets Frame
    public JFrame getFrame() {
        return frame;
    }
}
