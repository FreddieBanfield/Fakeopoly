package Shared.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Shared.Objects.Message;

public interface ClientServiceIF extends Remote {

    // Declaring the method prototype
    public void updatePlayerList(String[] playerNames) throws RemoteException;

    public void updateMessageBoard(ArrayList<Message> messages) throws RemoteException;

    public void updateGameMessageBoard(ArrayList<Message> messages) throws RemoteException;

    public void startGame() throws RemoteException;

    public void nextTurn(int turn) throws RemoteException;

    public void displayDiceRoll(int dice1, int dice2) throws RemoteException;

    public void updatePlayerLocation(int newLocation, int id) throws RemoteException;

    //clear last dice roll
    public void wipe() throws RemoteException;

    public void updatePlayerDetails() throws RemoteException;

    //if they roll doubles
    public void doubles() throws RemoteException;

    //allows user to end their turn
    public void enableTurnEnd() throws RemoteException;
}
