package Server.Services;

import java.awt.Color;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Shared.Interfaces.*;
import Shared.Objects.Player;

public class PlayerService extends UnicastRemoteObject implements PlayerServiceIF {
    private ArrayList<Player> players;

    public PlayerService(ArrayList<Player> players) throws RemoteException {
        super();
        this.players = players;
    }

    @Override
    public int getNumberOfPlayers() throws RemoteException {
        return players.size();
    }

    /**
     * Saves new player object on server
     * 
     * @param name
     * @param color
     * @return Returns the ID for that player
     * @throws RemoteException
     */
    @Override
    public int createPlayer(String name, Color color) throws RemoteException {
        Player player = new Player(name, color);
        players.add(player);
        int id = players.indexOf(player);
        return id;
    }

    @Override
    public String getNameById(int id) throws RemoteException {
        return players.get(id).getName();
    }

    @Override
    public Color getColorById(int id) throws RemoteException {
        return players.get(id).getColor();
    }

    @Override
    public void deletePlayer(int id) throws RemoteException {
        players.set(id, null);
    }
}
