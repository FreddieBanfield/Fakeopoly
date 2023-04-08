package Shared.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UpdateServiceIF extends Remote{
    public void updatePlayerList() throws RemoteException;
}
