package Shared.Interfaces;

import java.awt.Color;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayerServiceIF extends Remote {

    // Declaring the method prototype
    public int getNumberOfPlayers() throws RemoteException;

    public int createPlayer(String name, Color color) throws RemoteException;

    public String getNameById(int id) throws RemoteException;

    public Color getColorById(int id) throws RemoteException;

    public void deletePlayer(int id) throws RemoteException;
}