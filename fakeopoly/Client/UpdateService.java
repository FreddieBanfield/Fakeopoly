package Client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Shared.Interfaces.UpdateServiceIF;

public class UpdateService extends UnicastRemoteObject implements UpdateServiceIF{
    private GameClient client;
    protected UpdateService(GameClient client) throws RemoteException {
        super();
        this.client = client;
    }
    
    @Override
    public void updatePlayerList() throws RemoteException {
        client.gameLobby.updateUserList();
    }
    
}
