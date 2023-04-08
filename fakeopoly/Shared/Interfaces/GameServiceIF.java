package Shared.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Client.GameClient;
import Client.UpdateService;

public interface GameServiceIF extends Remote{
    public void updateUserList(int port) throws RemoteException;
}
