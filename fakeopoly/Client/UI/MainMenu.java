package Client.UI;

import javax.swing.JFrame;

public class MainMenu {

    JFrame frame;

    public MainMenu() {
        // Layout GUI
        frame = new JFrame("Fakeopoly");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    // Gets Frame
    public JFrame getFrame() {
        return frame;
    }
}
