package Client.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Client.GameClient;

public class GameLobby {

	private JFrame frame;
	private int frameWidth;
	private int frameHeight;
	private GameClient client;

	private JPanel panel;
	private JTextArea namesTA;

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
		panel = new JPanel();
		panel.setLayout(null);

		// Components
		namesTA = new JTextArea();
		namesTA.setBounds(frameWidth / 2 - 220, 50, 100, 300);
		namesTA.setText("Names: ");
		namesTA.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		namesTA.setEditable(false);

		// Buttons
		JButton readyBtn = new JButton("Ready");
		readyBtn.setBounds(frameWidth / 2 - 220, 420, 200, 40);
		readyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				readyBtnAction(readyBtn);
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
		panel.add(namesTA);
		panel.add(readyBtn);
		panel.add(backBtn);

		// Add Panel to Frame
		frame.setContentPane(panel);
		frame.setVisible(true);
	}

	// Gets Frame
	public JFrame getFrame() {
		return frame;
	}

	// Action when user clicks back button
	private void backBtnAction() {
		System.out.println("Opening Main Menu page.");
		try {
			client.getPlayerService().deletePlayer(client.getClientId());
			client.openMainMenu();
			frame.dispose();
		} catch (RemoteException e) {
			System.out.println(e);
		}
	}

	// Readies a player and updates server
	private void readyBtnAction(JButton readyBtn) {
		// Set color of button to symbolize readiness
		Color readyColor = new Color(184, 207, 229);
		if (readyBtn.getBackground() == new JButton().getBackground()) {
			readyBtn.setBackground(readyColor);
		} else {
			readyBtn.setBackground(null);
		}

		try {
			String allNames = "";
			int nop = client.getPlayerService().getNumberOfPlayers();
			for (int i = 0; i < nop; i++) {
				allNames += client.getPlayerService().getNameById(i) + "\n";
			}
			namesTA.setText(allNames);
			panel.repaint();
			panel.revalidate();
		} catch (RemoteException e) {
			readyBtn.setBackground(null);
			System.out.println(e);
		}
	}
}
