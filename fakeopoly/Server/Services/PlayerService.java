package Server.Services;

import java.awt.Color;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Shared.Interfaces.*;
import Shared.Objects.Player;

public class PlayerService extends UnicastRemoteObject implements PlayerIF {

    public PlayerService() throws RemoteException {
        super();
    }

    @Override
    public Player createPlayer(String name, Color color) throws RemoteException {
        return new Player(name, color);
    }
}
