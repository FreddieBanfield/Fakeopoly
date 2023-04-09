package Client.Services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Client.GameClient;
import Shared.Interfaces.ClientServiceIF;
import Shared.Objects.Message;

public class ClientService extends UnicastRemoteObject implements ClientServiceIF {
    private GameClient gameClient;

    public ClientService(GameClient gameClient) throws RemoteException {
        super();
        this.gameClient = gameClient;
    }

    public void updatePlayerList(String[] playerNames) throws RemoteException {
        gameClient.getGameLobby().setPlayerList(playerNames);
    }

    public void updateMessageBoard(ArrayList<Message> messages) throws RemoteException {
        gameClient.getGameLobby().setMessageBoard(messages);
    }

    public void startGame() throws RemoteException {
        gameClient.getGameLobby().startGame();
    }

}
