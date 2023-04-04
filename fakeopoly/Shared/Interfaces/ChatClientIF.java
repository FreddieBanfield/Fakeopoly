package Shared.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Client.GameClient;

public interface ChatClientIF extends Remote{

	public void messageFromServer(String message) throws RemoteException;

	public void updateUserList(String[] currentUsers, GameClient client) throws RemoteException;

}
