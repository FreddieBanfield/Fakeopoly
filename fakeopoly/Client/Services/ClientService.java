package Client.Services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Shared.Interfaces.ClientServiceIF;

public class ClientService extends UnicastRemoteObject implements ClientServiceIF {

    public ClientService() throws RemoteException {
        super();
    }

    @Override
    public void testMessage() throws RemoteException {
        System.out.println("This should only display on this client.");
    }

}
