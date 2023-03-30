package Client.UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Client.GameClient;

public class GameLobby {

    private JFrame frame;
    private int frameWidth;
    private int frameHeight;
    private GameClient client;

    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(8, 40);

    public GameLobby(int width, int height, GameClient client) {
        // Set Variables
        frameWidth = width;
        frameHeight = height;
        this.client = client;

        // Frame
        frame = new JFrame("Fakeopoly - Game Lobby");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setLocationRelativeTo(null); // Centers screen
        frame.setResizable(false);

        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Components

        // Add Components to Panel
        // panel.add(findServerBtn, BorderLayout.CENTER);

        // Add Panel to Frame
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    // Gets Frame
    public JFrame getFrame() {
        return frame;
    }
}
