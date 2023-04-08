package Client.UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Client.GameClient;

public class GameLobby {

	private JFrame frame;
	private int frameWidth;
	private int frameHeight;
	private GameClient client;

	private JPanel panel;
	private JLabel namesLbl;

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
		namesLbl = new JLabel();
		namesLbl.setBounds(100, 100, 300, 30);
		namesLbl.setText("Names: ");

		// Buttons
		JButton singleBtn = new JButton("Single");
		singleBtn.setBounds(frameWidth / 2 - 220, 320, 200, 40);
		singleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				singleBtnAction();
			}
		});

		JButton readyBtn = new JButton("Ready");
		readyBtn.setBounds(frameWidth / 2 - 220, 420, 200, 40);
		readyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				readyBtnAction();
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
		//updateUserList();
		// Add Components to Panel
		panel.add(namesLbl);
		panel.add(singleBtn);
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

	// Delete when done with testing
	private void singleBtnAction() {
		try {
			namesLbl.setText(client.getPlayerService().getNameById(client.getClientId()));
			panel.repaint();
			panel.revalidate();
		} catch (RemoteException e) {
			System.out.println(e);
		}
	}

	// Readies a player and updates server
	private void readyBtnAction() {
		updateUserList();
	}

	public void updateUserList(){
		try {
			String allNames = "";
			int nop = client.getPlayerService().getNumberOfPlayers();
			for (int i = 0; i < nop; i++) {
				allNames += client.getPlayerService().getNameById(i) + " ";
				System.out.println(allNames);
			}
			namesLbl.setText(allNames);
			panel.repaint();
			panel.revalidate();
		} catch (RemoteException e) {
			System.out.println(e);
		}
	}
}
