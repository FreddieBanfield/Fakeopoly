package Client.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Client.GameClient;
import Shared.Objects.Message;

public class GameLobby {

	private JFrame frame;
	private int frameWidth;
	private int frameHeight;
	private GameClient client;

	private JPanel panel;
	private JScrollPane messageBoard;
	private JTextArea namesTA;
	private JTextArea messagesTA;
	private JTextField messageTF;

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

		messagesTA = new JTextArea();
		messagesTA.setBounds(0, 0, 340, 270);
		messagesTA.setText("");
		messagesTA.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		messagesTA.setEditable(false);

		messageBoard = new JScrollPane(messagesTA);
		messageBoard.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		messageBoard.setBounds(frameWidth / 2 - 120, 50, 340, 270);

		messageTF = new JTextField();
		messageTF.setBounds(frameWidth / 2 - 120, 320, 310, 30);
		messageTF.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		messageTF.setBackground(Color.LIGHT_GRAY);

		// Buttons
		JButton sendMessageBtn = new JButton("Send");
		sendMessageBtn.setBounds(frameWidth / 2 + 190, 320, 30, 30);
		sendMessageBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessageAction();
			}
		});

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
		panel.add(messageBoard);
		panel.add(messageTF);
		panel.add(sendMessageBtn);
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

	public void sendMessageAction() {
		String playerName;
		try {
			// Create Message object
			playerName = client.getPlayerService().getNameById(client.getClientId());
			String messageContent = messageTF.getText();
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
			Date date = new Date();
			String time = formatter.format(date);
			Message message = new Message(playerName, messageContent, time);

			client.getPlayerService().addMessage(message);
			messageTF.setText("");
		} catch (RemoteException e) {
			System.out.println(e);
		}
	}

	// Readies a player and updates server
	private void readyBtnAction(JButton readyBtn) {
		// Set color of button to symbolize readiness
		Color readyColor = new Color(184, 207, 229);

		// Update readiness of player
		try {
			if (readyBtn.getBackground() == new JButton().getBackground()) {
				client.getPlayerService().setIsReadyById(true, client.getClientId());
				readyBtn.setBackground(readyColor);
			} else {
				client.getPlayerService().setIsReadyById(false, client.getClientId());
				readyBtn.setBackground(null);
			}
		} catch (RemoteException e) {
			readyBtn.setBackground(null);
			System.out.println(e);
		}

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

	// ------------ Getters and Setters -----------------

	public void setPlayerList(String[] playerNames) {
		String result = "Players:\n-----------\n\n";
		for (int i = 0; i < playerNames.length; i++) {
			result += playerNames[i] + "\n";
		}
		namesTA.setText(result);
		panel.repaint();
		panel.revalidate();
	}

	public void setMessageBoard(ArrayList<Message> messages) {
		String result = "Message Board\n---------------------\n\n";
		for (Message message : messages) {
			result += message.getPlayerName() + ": " + message.getTime() + "\n";
			result += message.getMessage() + "\n\n";
		}
		messagesTA.setText(result);
		panel.repaint();
		panel.revalidate();
	}

	public void startGame() {
		frame.dispose();
		client.openGameView();
	}
}
