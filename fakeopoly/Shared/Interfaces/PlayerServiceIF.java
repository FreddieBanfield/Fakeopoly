package Shared.Interfaces;

import java.awt.Color;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Shared.Objects.Message;
import Shared.Objects.Player;
import Shared.Objects.Property;

public interface PlayerServiceIF extends Remote {

    // Declaring the method prototype
    public Player getPlayerById(int id) throws RemoteException;

    public int getNumberOfPlayers() throws RemoteException;

    public void updatePlayerList() throws RemoteException;

    public int createPlayer(String name, Color color) throws RemoteException;

    public boolean connectClient(String serverAddress, int serverPort, int id) throws RemoteException;

    public String getNameById(int id) throws RemoteException;

    public Color getColorById(int id) throws RemoteException;

    public void deletePlayer(int id) throws RemoteException;

    public void addMessage(Message message) throws RemoteException;

    public void UpdateMessageBoard() throws RemoteException;

    public void setIsReadyById(Boolean isReady, int id) throws RemoteException;

    public Boolean getIsReadyById(int id) throws RemoteException;

    public Property getPropertyById(int id) throws RemoteException;

    public int getTotalPlayers() throws RemoteException;

    public int getMoneyById(int id) throws RemoteException;

    public int getTurn() throws RemoteException;

    public void endTurn() throws RemoteException;

    public void addGameMessage(Message message) throws RemoteException;

    public void displayDiceRoll(int id) throws RemoteException;

    public String getMessages() throws RemoteException;

    public ArrayList<Property> getProperties() throws RemoteException;

    public void setPlayerMoney(int id, int value) throws RemoteException;

    public void setPropertyOwner(int propertyId, int id) throws RemoteException;

    public boolean checkIfPlayerOwns(int propertyId, int id) throws RemoteException;

    public void setOwnedPropertyImage(int id, int propertyId, String propertyColor) throws RemoteException;

    public boolean gameHasStarted() throws RemoteException;

}
