package Client;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Shared.Interfaces.ChatClientIF;

public class ChatClientService extends UnicastRemoteObject implements ChatClientIF {

    protected ChatClientService() throws RemoteException {
        super();
        //TODO Auto-generated constructor stub
    }

    @Override
    public void messageFromServer(String message) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'messageFromServer'");
        
    }

    @Override
    public void updateUserList(String[] currentUsers, GameClient client) throws RemoteException {
		if(currentUsers.length < 2){
			client.gameLobby.privateMsgButton.setEnabled(false);
		}
		//userPanel.remove(clientPanel);
		client.gameLobby.setClientPanel(currentUsers);
		client.gameLobby.clientPanel.repaint();
		client.gameLobby.clientPanel.revalidate();
    }

    
}
