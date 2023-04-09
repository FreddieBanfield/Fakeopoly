package Client.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Client.GameClient;

public class GameView {
    //private String BOARDPATH = "fakeopoly/Client/Resources/Board/";
    //Brady's filepath for whatever reason
    private String BOARDPATH = "Fakeopoly/fakeopoly/Client/Resources/Board/";
    private JFrame frame;
    private int frameWidth;
    private int frameHeight;
    private GameClient client;
    private BufferedImage boardImages[];
    private ImageIcon boardImagesScaled[];
    private JButton boardTiles[];
    private JButton rollDice;
    private JButton endTurn;
    private JButton manageProperties;
    private JLabel[] playerDetails;

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

        //add compontents to control panel
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
    }
    private void setControlPanelPlayerDetails(){
        try{
            playerDetails = new JLabel[client.getPlayerService().getTotalPlayers()];
            //playerDetails = new JLabel[2];
        }catch(Exception e){
            System.out.println(e);
        }
        int startingX = 370/10;
        int startingY = frameHeight - 750;
        int width = 300;
        int height = 70;
        int yOffset = height + 15;
        for(int i = 0; i < playerDetails.length; i++){
            try{
                int money = client.getPlayerService().getMoneyById(i);
                Color colour = client.getPlayerService().getColorById(i);
                String name = client.getPlayerService().getNameById(i).toString();
                JButton color = new JButton();
                color.setBackground(colour);
                color.setOpaque(true);
                color.setBounds(startingX - 27,startingY + (yOffset * i) + 15,20,20);
                color.setEnabled(false);

                controlsPanel.add(color);
                playerDetails[i] = new JLabel("<html>Player: " + name + " &nbsp &nbsp Money: " + money + " <br/></br>Properties: <html>");
                //playerDetails[i].setForeground(colour);
                playerDetails[i].setBounds(startingX,startingY + (yOffset * i),width,height);
            }catch(Exception e ){
                System.out.println(e);
            }


        }
    }
    private void setControlPanelButtons(){
        rollDice = new JButton("Roll Dice");
        int x = 370/6;
        int offset = 0;
        rollDice.setBounds(x + offset, frameHeight - 135, 300, 40);
        rollDice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//roll dice
                //getDiceRoll();
			}
		});
        endTurn = new JButton("End Turn");
        endTurn.setBounds(x + offset,frameHeight - 90 , 300, 40);
        endTurn.setEnabled(false);

        manageProperties = new JButton("Manage Properties");
        manageProperties.setBounds(x + offset,frameHeight - 180 , 300, 40);
        
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
