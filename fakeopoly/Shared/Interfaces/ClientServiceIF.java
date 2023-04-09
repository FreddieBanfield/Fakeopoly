package Shared.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Shared.Objects.Message;

public interface ClientServiceIF extends Remote {

    // Declaring the method prototype
    public void updatePlayerList(String[] playerNames) throws RemoteException;

    public void updateMessageBoard(ArrayList<Message> messages) throws RemoteException;

    public void startGame() throws RemoteException;
}
