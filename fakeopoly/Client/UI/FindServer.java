package Client.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Client.GameClient;

public class FindServer {

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

    public FindServer(int width, int height, GameClient client) {
        // Set Variables
        frameWidth = width;
        frameHeight = height;
        this.client = client;

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
        serverAddressLbl = new JLabel();
        serverAddressLbl.setBounds(frameWidth / 2 - 155, 100, 300, 30);
        serverAddressLbl.setText("Server Address:");

        serverAddressTF = new JTextField();
        serverAddressTF.setBounds(frameWidth / 2 - 155, 130, 300, 30);

        serverAddressErrorLbl = new JLabel();
        serverAddressErrorLbl.setBounds(frameWidth / 2 - 155, 160, 300, 30);
        serverAddressErrorLbl.setText("Invalid Server Address!");
        serverAddressErrorLbl.setForeground(Color.RED);
        serverAddressErrorLbl.setVisible(false);

        serverPortLbl = new JLabel();
        serverPortLbl.setBounds(frameWidth / 2 - 155, 200, 300, 30);
        serverPortLbl.setText("Server Port:");

        serverPortTF = new JTextField();
        serverPortTF.setBounds(frameWidth / 2 - 155, 230, 300, 30);

        serverPortErrorLbl = new JLabel();
        serverPortErrorLbl.setBounds(frameWidth / 2 - 155, 260, 300, 30);
        serverPortErrorLbl.setText("Invalid Server Port!");
        serverPortErrorLbl.setForeground(Color.RED);
        serverPortErrorLbl.setVisible(false);

        JButton joinBtn = new JButton("Join");
        joinBtn.setBounds(frameWidth / 2 - 220, 400, 200, 40);
        joinBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joinBtnAction();
            }
        });

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(frameWidth / 2 + 20, 400, 200, 40);
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
        if (serverAddressTF.getText() == null || serverAddressTF.getText().equals("")) {
            serverAddressErrorLbl.setVisible(true);
            hasErrors = true;
        } else {
            serverAddressErrorLbl.setVisible(false);
        }
        if (serverPortTF.getText() == null || serverPortTF.getText().equals("")) {
            serverPortErrorLbl.setVisible(true);
            hasErrors = true;
        } else {
            serverPortErrorLbl.setVisible(false);
        }

        if (hasErrors != true) {
            client.openMainMenu();
            frame.dispose();
        }
    }

    // Action when user clicks back button
    private void backBtnAction() {
        System.out.println("Opening Main Menu page.");
        client.openMainMenu();
        frame.dispose();
    }

    // Gets Frame
    public JFrame getFrame() {
        return frame;
    }
}
