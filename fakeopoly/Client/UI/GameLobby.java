package Client.UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;

import Client.GameClient;
import Shared.Interfaces.ChatServerIF;
import Shared.Objects.Player;

public class GameLobby implements ActionListener, Serializable {

    private JFrame frame;
    private int frameWidth;
    private int frameHeight;	
    private JPanel textPanel, inputPanel;
	private JTextField textField;
    private GameClient client;
    private String name, message;
    private Font meiryoFont = new Font("Meiryo", Font.PLAIN, 14);
    private Border blankBorder = BorderFactory.createEmptyBorder(10,10,20,10);//top,r,b,l
    protected JTextArea textArea, userArea;
    public JButton privateMsgButton, startButton, sendButton;
    public JPanel clientPanel, userPanel;
    private JList<String> list;
    private DefaultListModel<String> listModel;

    public GameLobby(int width, int height, GameClient client) {
       
        // Set Variables
        frameWidth = width;
        frameHeight = height;
        int yOffset = -60;
        this.client = client;
        // Frame
        frame = new JFrame("Fakeopoly - Game Lobby");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setLocationRelativeTo(null); // Centers screen
        frame.setResizable(false);

        // Panel
        //JPanel panel = new JPanel();
       // panel.setLayout(null);

        // Components
        Container c = frame.getContentPane();
		JPanel outerPanel = new JPanel(new BorderLayout());

		outerPanel.add(getInputPanel(), BorderLayout.CENTER);
		outerPanel.add(getTextPanel(), BorderLayout.NORTH);

		c.setLayout(new BorderLayout());
		c.add(outerPanel, BorderLayout.CENTER);
		c.add(getUsersPanel(), BorderLayout.WEST);

		name = client.player.getName();
		if(name.length() != 0){
			frame.setTitle(name + "'s console ");
			textField.setText("");
			textArea.append("username : " + name + " connecting to chat...\n");
			//getConnected(name);
			
			startButton.setEnabled(false);
			sendButton.setEnabled(true);
			
		}

		frame.pack();
		frame.setAlwaysOnTop(true);
        // Add Components to Panel

        // Add Panel to Frame
        //frame.setContentPane(panel);
        frame.setVisible(true);
    }
	public JPanel getTextPanel(){
		String welcome = "Welcome enter your name and press Start to begin\n";
		textArea = new JTextArea(welcome, 14, 34);
		textArea.setMargin(new Insets(10, 10, 10, 10));
		textArea.setFont(meiryoFont);

		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textPanel = new JPanel();
		textPanel.add(scrollPane);

		textPanel.setFont(new Font("Meiryo", Font.PLAIN, 14));
		return textPanel;
	}

    public JPanel getInputPanel(){
		inputPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		inputPanel.setBorder(blankBorder);
		textField = new JTextField();
		textField.setFont(meiryoFont);
		inputPanel.add(textField);
		return inputPanel;
	}

    public JPanel getUsersPanel(){

		userPanel = new JPanel(new BorderLayout());
		String  userStr = " Current Users      ";

		JLabel userLabel = new JLabel(userStr, JLabel.CENTER);
		userPanel.add(userLabel, BorderLayout.NORTH);
		userLabel.setFont(new Font("Meiryo", Font.PLAIN, 16));
		String[] noClientsYet = {"No other users"};
		setClientPanel(noClientsYet);

		clientPanel.setFont(meiryoFont);
		userPanel.add(makeButtonPanel(), BorderLayout.SOUTH);
		userPanel.setBorder(blankBorder);

		return userPanel;
	}
	public JPanel makeButtonPanel() {
		sendButton = new JButton("Send ");
		sendButton.addActionListener(this);
		sendButton.setEnabled(false);

        privateMsgButton = new JButton("Send PM");
        privateMsgButton.addActionListener(this);
        privateMsgButton.setEnabled(false);

		startButton = new JButton("Start ");
		startButton.addActionListener(this);

		JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
		buttonPanel.add(privateMsgButton);
		buttonPanel.add(new JLabel(""));
		buttonPanel.add(startButton);
		buttonPanel.add(sendButton);

		return buttonPanel;
	}
    public void setClientPanel(String[] currClients) {
    	clientPanel = new JPanel(new BorderLayout());
        listModel = new DefaultListModel<String>();

        for(String s : currClients){
        	listModel.addElement(s);
        }
        if(currClients.length > 1){
        	privateMsgButton.setEnabled(true);
        }

        //Create the list and put it in a scroll pane.
        list = new JList<String>(listModel);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setVisibleRowCount(8);
        list.setFont(meiryoFont);
        JScrollPane listScrollPane = new JScrollPane(list);

        clientPanel.add(listScrollPane, BorderLayout.CENTER);
        userPanel.add(clientPanel, BorderLayout.CENTER);
    }
    @Override
	public void actionPerformed(ActionEvent e){

		try {
			//get connected to chat service
			if(e.getSource() == startButton){
				name = client.player.getName();
				if(name.length() != 0){
					frame.setTitle(name + "'s console ");
					textField.setText("");
					textArea.append("username : " + name + " connecting to chat...\n");
					//getConnected(name);
					
					startButton.setEnabled(false);
					sendButton.setEnabled(true);
					
				}

			}

			//get text and clear textField
			if(e.getSource() == sendButton){
				message = textField.getText();
				textField.setText("");
				sendMessage(message);
				System.out.println("Sending message : " + message);
			}

			//send a private message, to selected users
			if(e.getSource() == privateMsgButton){
				int[] privateList = list.getSelectedIndices();

				for(int i=0; i<privateList.length; i++){
					System.out.println("selected index :" + privateList[i]);
				}
				message = textField.getText();
				textField.setText("");
				sendPrivate(privateList);
			}

		}
		catch (RemoteException remoteExc) {
			remoteExc.printStackTrace();
		}

	}//end actionPerformed
    private void sendMessage(String chatMessage) throws RemoteException {
        try{
            ChatServerIF chatAccess = (ChatServerIF) Naming.lookup("rmi://" + client.getServerAddress() + ":" + client.getServerPort() + "/ChatService");
            chatAccess.updateChat(name, chatMessage);
        }catch(Exception e){
            e.printStackTrace();
        }
		//client.serverIF.updateChat(name, chatMessage);
	}
    private void sendPrivate(int[] privateList) throws RemoteException {
        try{
            ChatServerIF chatAccess = (ChatServerIF) Naming.lookup("rmi://" + client.getServerAddress() + ":" + client.getServerPort() + "/ChatService");
            String privateMessage = "[PM from " + name + "] :" + message + "\n";
            chatAccess.sendPM(privateList, privateMessage);
        }catch(Exception e){
            e.printStackTrace();
        }
	}

	public void messageFromServer(String message) throws RemoteException {
		textArea.append( message );
		//make the gui display the last appended text, ie scroll to bottom
		//textArea.setCaretPosition(chatGUI.textArea.getDocument().getLength());
	}

	public void updateUserList(String[] currentUsers) throws RemoteException {

		if(currentUsers.length < 2){
			privateMsgButton.setEnabled(false);
		}
		userPanel.remove(clientPanel);
		setClientPanel(currentUsers);
		clientPanel.repaint();
		clientPanel.revalidate();
	}
    // Gets Frame
    public JFrame getFrame() {
        return frame;
    }

}
