package Client.UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu {

    private JFrame frame;

    public MainMenu(int width, int height) {
        // Layout GUI
        frame = new JFrame("Fakeopoly");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null); // Centers screen
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton button = new JButton("Find Server");
        button.setBounds(width / 2 - 105, height / 2 - 20, 200, 40);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("I was clicked!");
            }
        });

        panel.add(button, BorderLayout.CENTER);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    // Gets Frame
    public JFrame getFrame() {
        return frame;
    }
}
