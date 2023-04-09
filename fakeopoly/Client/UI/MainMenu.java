package Client.UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Client.GameClient;

public class MainMenu {

    private JFrame frame;
    private int frameWidth;
    private int frameHeight;
    private GameClient client;

    public MainMenu(JFrame frame, int width, int height, GameClient client) {
        // Set Variables
        this.frame = frame;
        frameWidth = width;
        frameHeight = height;
        this.client = client;

        // Frame
        frame.setTitle("Fakeopoly - Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setResizable(false);

        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Components
        JButton findServerBtn = new JButton("Find Server");
        findServerBtn.setBounds(frameWidth / 2 - 105, 400, 200, 40);
        findServerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Opening Find Server page.");
                client.openFindServer();
            }
        });

        // Add Components to Panel
        panel.add(findServerBtn, BorderLayout.CENTER);

        // Add Panel to Frame
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    // Gets Frame
    public JFrame getFrame() {
        return frame;
    }
}
