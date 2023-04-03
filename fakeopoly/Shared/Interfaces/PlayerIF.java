package Shared.Interfaces;

import java.awt.Color;
import java.rmi.Remote;
import java.rmi.RemoteException;

import Shared.Objects.Player;

public interface PlayerIF extends Remote {

    // Declaring the method prototype
    public Player createPlayer(String name, Color color) throws RemoteException;
}