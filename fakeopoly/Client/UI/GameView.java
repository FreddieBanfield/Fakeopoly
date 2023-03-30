package Client.UI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Client.GameClient;

public class GameView {

    private JFrame frame;
    private int frameWidth;
    private int frameHeight;
    private GameClient client;

    public GameView(int width, int height, GameClient client) {
        // Set Variables
        frameWidth = width;
        frameHeight = height;
        this.client = client;

        // Frame
        frame = new JFrame("Fakeopoly - Game View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setLocationRelativeTo(null); // Centers screen
        frame.setResizable(false);

        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Components

        // Add Components to Panel

        // Add Panel to Frame
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    // Gets Frame
    public JFrame getFrame() {
        return frame;
    }
}
