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

    @Override
    public void updatePlayerList(String[] playerNames) throws RemoteException {
        gameClient.getGameLobby().setPlayerList(playerNames);
    }

    @Override
    public void updateMessageBoard(ArrayList<Message> messages) throws RemoteException {
        gameClient.getGameLobby().setMessageBoard(messages);
    }

    @Override
    public void updateGameMessageBoard(ArrayList<Message> messages) throws RemoteException {
        gameClient.getGameView().setMessageBoard(messages);
    }

    @Override
    public void startGame() throws RemoteException {
        gameClient.getGameLobby().startGame();
    }

    @Override
    public void nextTurn(int turn) {
        if (turn == gameClient.getClientId()) {
            gameClient.getGameView().enableTurn();
        } else {
            gameClient.getGameView().disableTurn();
        }
    }

    @Override
    public void displayDiceRoll(int dice1, int dice2) throws RemoteException {
        gameClient.getGameView().displayDiceRoll(dice1, dice2);
    }

}
