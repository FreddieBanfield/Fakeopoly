package Client.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Client.GameClient;
import Shared.Objects.Message;

public class GameView {
    //private String BOARDPATH = "fakeopoly/Client/Resources/Board/";
    //Brady's filepath for whatever reason
    private String BOARDPATH = "Fakeopoly/fakeopoly/Client/Resources/Board/";
    private String DICEPATH = "Fakeopoly/fakeopoly/Client/Resources/Dice/";
    private JFrame frame;
    private int frameWidth;
    private int frameHeight;
    private GameClient client;
    private BufferedImage boardImages[];
    private BufferedImage diceImages[];
    private ImageIcon boardImagesScaled[];
    private JButton boardTiles[];
    private JButton rollDice;
    private JButton endTurn;
    private JTextArea chatArea;
    private JTextField messageTF;
    private JScrollPane messageBoard;
    private JButton sendMessageBtn;
    private JButton manageProperties;
    private JLabel[] playerDetails;
    private JLabel[] diceTile;
    private JPanel controlsPanel = new JPanel();
    private JPanel boardPanel = new JPanel();
    private JPanel mainPanel = new JPanel();

    private int imagesNum = 40;
    private double imageScale = 1.25;

    public GameView(JFrame frame, int width, int height, GameClient client) {
        // Set Variables
        this.frame = frame;
        frameWidth = width + 570;
        frameHeight = height + 170;
        this.client = client;
        boardImages = new BufferedImage[imagesNum];
        boardImagesScaled = new ImageIcon[imagesNum];
        boardTiles = new JButton[imagesNum];
        diceImages = new BufferedImage[6];
        diceTile = new JLabel[2];
        playerDetails=new JLabel[2];
        diceTile[0] = new JLabel();
        diceTile[1] = new JLabel();
        chatArea = new JTextArea();
        sendMessageBtn = new JButton("Send");
        messageBoard = new JScrollPane(chatArea);
        diceTile[0].setBounds(225,300,125,125);
        diceTile[1].setBounds(375,300,125,125);
        // Frame
        frame.setTitle("Fakeopoly - Game View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setResizable(false);

        // Panel

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        boardPanel.setLayout(null);
        boardPanel.setBackground(Color.gray);
        boardPanel.setPreferredSize(new Dimension(width + 100, frameHeight));

        controlsPanel.setLayout(null);
        controlsPanel.setPreferredSize(new Dimension(370, frameHeight));


        // Components
        loadPropertyImages();
        createBoard();
        setControlPanelButtons();
        setControlPanelChat();
        setControlPanelPlayerDetails();

        // Add JButtons to panel
        for (int i = 0; i < imagesNum; i++) {
            boardPanel.add(boardTiles[i]);
        }
        boardPanel.add(diceTile[0]);
        boardPanel.add(diceTile[1]);
        //add compontents to control panel
        controlsPanel.add(messageBoard);
        controlsPanel.add(sendMessageBtn);
        controlsPanel.add(messageTF);
        controlsPanel.add(rollDice);
        controlsPanel.add(endTurn);
        controlsPanel.add(manageProperties);
        for(int i = 0; i < playerDetails.length; i++){
            controlsPanel.add(playerDetails[i]);
        }

        // Add Components to Panel
        mainPanel.add(boardPanel);
        mainPanel.add(controlsPanel);

        // Add Panel to Frame
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
    private void setControlPanelChat(){
        //chatArea.setBounds(25,300,400,250);
        chatArea.setEditable(false);
        chatArea.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        messageBoard.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		messageBoard.setBounds(10,350,400,200);

        messageTF = new JTextField();
		messageTF.setBounds(10, 550, 330,30);
		messageTF.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		messageTF.setBackground(Color.LIGHT_GRAY);

		sendMessageBtn.setBounds(340,550,70,30);
		sendMessageBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessageAction();
			}
		});
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

			client.getPlayerService().addGameMessage(message);
			messageTF.setText("");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
    public void setMessageBoard(ArrayList<Message> messages) {
		String result = "Message Board\n---------------------\n\n";
		for (Message message : messages) {
			result += message.getPlayerName() + ": " + message.getTime() + "\n";
			result += message.getMessage() + "\n\n";
		}
		chatArea.setText(result);
		//panel.repaint();
		//panel.revalidate();
	}
    public void setControlPanelPlayerDetails(){
        try{
            playerDetails = new JLabel[client.getPlayerService().getTotalPlayers()];
            //playerDetails = new JLabel[2];
        }catch(Exception e){
            System.out.println(e);
        }
        int startingX = 370/10;
        int startingY = frameHeight - 750;
        int width = 400;
        int height = 60;
        int yOffset = height + 10;
        for(int i = 0; i < playerDetails.length; i++){
            try{
                Color colour = client.getPlayerService().getColorById(i);
                JButton color = new JButton();
                color.setBackground(colour);
                color.setOpaque(true);
                color.setBounds(startingX - 27,startingY + (yOffset * i) + 25,20,20);
                color.setVisible(true);

                color.setEnabled(false);
                controlsPanel.add(color);

                playerDetails[i] = new JLabel(setPlayerDetailsString(i));
                //playerDetails[i].setForeground(colour);
                playerDetails[i].setBounds(startingX,startingY + (yOffset * i),width,height);
            }catch(Exception e ){
                System.out.println(e);
            }


        }
    }
    public String setPlayerDetailsString(int id){
        try{
            int money = client.getPlayerService().getMoneyById(id);
            String name = client.getPlayerService().getNameById(id);
            String turnString = "";
            if(client.getPlayerService().getTurn() == id ){
                turnString="  &nbsp;&nbsp;&nbsp;&nbsp; <-------- Your Turn";   
            }   
            return "<html>Player: " + name + " &nbsp &nbsp Money: " + money + turnString +" <br/></br>Properties: <html>";
        }catch(Exception e ){
            System.out.println(e);
        }
        return "";
    }
    public void setControlPanelButtons(){
        rollDice = new JButton("Roll Dice");
        int x = 370/6;
        int offset = 0;
        rollDice.setBounds(x + offset, frameHeight - 135, 300, 40);
        rollDice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//roll dice
                performDiceRoll();
			}
		});
        endTurn = new JButton("End Turn");
        endTurn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//end turn
                endTurn();
			}
		});
        endTurn.setBounds(x + offset,frameHeight - 90 , 300, 40);
        try{
            if(client.getPlayerService().getTurn() == client.getClientId()){
                endTurn.setEnabled(true);
                rollDice.setEnabled(true);
            }else{
                endTurn.setEnabled(false);
                rollDice.setEnabled(false);
            }
        }catch(Exception e){
            System.out.println(e);
        }



        manageProperties = new JButton("Manage Properties");
        manageProperties.setBounds(x + offset,frameHeight - 180 , 300, 40);
        
    }
    public void enableTurn(){
        rollDice.setEnabled(true);
        endTurn.setEnabled(true);
        for(int i = 0; i < playerDetails.length; i++){
            playerDetails[i].setText(setPlayerDetailsString(i));
        }
    }
    public void disableturn(){
        rollDice.setEnabled(false);
        endTurn.setEnabled(false);
        for(int i = 0; i < playerDetails.length; i++){
            playerDetails[i].setText(setPlayerDetailsString(i));
        }
    }
    private void performDiceRoll(){
        int dice1=(int)(Math.random()*6+1);
        int dice2=(int)(Math.random()*6+1);
        try{
            diceImages[0] = ImageIO.read(new File(DICEPATH + "dice1.png"));
            diceImages[1] = ImageIO.read(new File(DICEPATH + "dice2.png"));
            diceImages[2] = ImageIO.read(new File(DICEPATH + "dice3.png"));
            diceImages[3] = ImageIO.read(new File(DICEPATH + "dice4.png"));
            diceImages[4] = ImageIO.read(new File(DICEPATH + "dice5.png"));
            diceImages[5] = ImageIO.read(new File(DICEPATH + "dice6.png"));
        }catch(Exception e){
            System.out.print(e);
        }

        int sum = dice1 + dice2;

        diceTile[0].setIcon(new ImageIcon(diceImages[dice1-1].getScaledInstance((int) (diceImages[dice1-1].getWidth() / imageScale), (int) (diceImages[dice1-1].getHeight() / imageScale), Image.SCALE_SMOOTH)));
        diceTile[1].setIcon(new ImageIcon(diceImages[dice2-1].getScaledInstance((int) (diceImages[dice2-1].getWidth() / imageScale), (int) (diceImages[dice2-1].getHeight() / imageScale), Image.SCALE_SMOOTH)));

    }

    private void endTurn(){
        diceTile[0].setIcon(new ImageIcon());
        diceTile[1].setIcon(new ImageIcon());
        try{
            if(client.getPlayerService().getTurn() == client.getClientId()){
                client.getPlayerService().endTurn();
            }
        }catch(Exception e){
            System.out.print(e);
        }

    }
    private void loadPropertyImages() {
        // Get images from folder and store as buffered image
        try {
            boardImages[0] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Go.jpg"));
            boardImages[1] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Buildings_brown.png"));
            boardImages[2] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Chest_bot.jpg"));
            boardImages[3] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Buildings_brown.png"));
            boardImages[4] = ImageIO.read(new File(BOARDPATH + "monopoly_board_IncomeTax.jpg"));
            boardImages[5] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Train_Bot.jpg"));
            boardImages[6] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Buildings_LightBlue.png"));
            boardImages[7] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Chance_bot.jpg"));
            boardImages[8] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Buildings_LightBlue.png"));
            boardImages[9] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Buildings_LightBlue.png"));
            boardImages[10] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Jail(Whole).jpg"));
            boardImages[11] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Buildings_Pink.png"));
            boardImages[12] = ImageIO.read(new File(BOARDPATH + "monopoly_board_ElectricCompany.jpg"));
            boardImages[13] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Buildings_Pink.png"));
            boardImages[14] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Buildings_Pink.png"));
            boardImages[15] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Train_Left.jpg"));
            boardImages[16] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Buildings_Orange.png"));
            boardImages[17] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Chest_Left.jpg"));
            boardImages[18] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Buildings_Orange.png"));
            boardImages[19] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Buildings_Orange.png"));
            boardImages[20] = ImageIO.read(new File(BOARDPATH + "monopoly_board_FreeParking.jpg"));
            boardImages[21] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Buildings_Red.png"));
            boardImages[22] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Chance_top.jpg"));
            boardImages[23] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Buildings_Red.png"));
            boardImages[24] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Buildings_Red.png"));
            boardImages[25] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Train_Top.jpg"));
            boardImages[26] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Buildings_Yellow.png"));
            boardImages[27] = ImageIO.read(new File(BOARDPATH + "monopoly_board_WaterWorks.jpg"));
            boardImages[28] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Buildings_Yellow.png"));
            boardImages[29] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Buildings_Yellow.png"));
            boardImages[30] = ImageIO.read(new File(BOARDPATH + "monopoly_board_GotoJail_Better.jpg"));
            boardImages[31] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Buildings_Green.png"));
            boardImages[32] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Buildings_Green.png"));
            boardImages[33] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Chest_Right.jpg"));
            boardImages[34] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Buildings_Green.png"));
            boardImages[35] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Train_Right.jpg"));
            boardImages[36] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Chance_Right.jpg"));
            boardImages[37] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Buildings_DarkBlue.png"));
            boardImages[38] = ImageIO.read(new File(BOARDPATH + "monopoly_board_LuxuryTax.jpg"));
            boardImages[39] = ImageIO.read(new File(BOARDPATH + "monopoly_board_Buildings_DarkBlue.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void createBoard() {
        // Scale images and save as ImageIcons
        for (int i = 0; i < imagesNum; i++) {
            boardImagesScaled[i] = new ImageIcon(
                    boardImages[i].getScaledInstance((int) (boardImages[i].getWidth() / imageScale),
                            (int) (boardImages[i].getHeight() / imageScale), Image.SCALE_SMOOTH));
        }

        // Set image to JButton
        for (int i = 0; i < imagesNum; i++) {
            boardTiles[i] = new JButton(boardImagesScaled[i]);
        }
        // Set location and size of JButton
        int starting_x = 597;
        int starting_y = 590;
        int wide_w = (int) (boardImages[0].getWidth() / imageScale);
        int wide_h = (int) (boardImages[0].getHeight() / imageScale);
        int slim_w = (int) (boardImages[1].getWidth() / imageScale);
        int slim_h = (int) (boardImages[0].getHeight() / imageScale);

        for (int i = 0; i < imagesNum; i++) {
            if (i < 10) {
                if (i == 0)
                    boardTiles[i].setBounds(starting_x, starting_y, wide_w, wide_h);
                else
                    boardTiles[i].setBounds(starting_x - i * slim_w, starting_y, slim_w, slim_h);
            } else if (i < 20) {
                if (i == 10)
                    boardTiles[i].setBounds(starting_x - 9 * slim_w - wide_w, starting_y, wide_w, wide_h);
                else
                    boardTiles[i].setBounds(starting_x - 9 * slim_w - wide_w, starting_y - (i - 10) * slim_w,
                            slim_h, slim_w);
            } else if (i < 30) {
                if (i == 20)
                    boardTiles[i].setBounds(starting_x - 9 * slim_w - wide_w, starting_y - 9 * slim_w - wide_h,
                            wide_w, wide_h);
                else
                    boardTiles[i].setBounds(starting_x - 10 * slim_w + (i - 20) * slim_w,
                            starting_y - 9 * slim_w - wide_h,
                            slim_w, slim_h);
            } else if (i < 40) {
                if (i == 30)
                    boardTiles[i].setBounds(starting_x, starting_y - 9 * slim_w - wide_h,
                            wide_w, wide_h);
                else
                    boardTiles[i].setBounds(starting_x,
                            starting_y - 10 * slim_w + (i - 30) * slim_w,
                            slim_h, slim_w);
            }
        }
    }

    // Gets Frame
    public JFrame getFrame() {
        return frame;
    }
}
