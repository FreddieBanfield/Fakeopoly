package Server.Services;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.RemoteRef;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

import Client.GameClient;
import Shared.Interfaces.ChatClientIF;
import Shared.Interfaces.ChatServerIF;

public class ChatService extends UnicastRemoteObject implements ChatServerIF {
    private GameClient[] clients;
	private int userCount;
    public ChatService() throws RemoteException {
        super();
        clients = new GameClient[2];
		userCount = 0;
        //TODO Auto-generated constructor stub
    }

    @Override
    public void updateChat(String userName, String chatMessage) throws RemoteException {
        // TODO Auto-generated method stub
        String message =  userName + " : " + chatMessage + "\n";
		sendToAll(message);
    }
	public void sendToAll(String newMessage){
		for(GameClient c : clients){
			try {
				//c.getClient().messageFromServer(newMessage);
                c.gameLobby.messageFromServer(newMessage);
			}
			catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

    public void passIDentity(RemoteRef ref) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'passIDentity'");
    }

    @Override
    public void registerListener(GameClient newClient) throws RemoteException {
		System.out.println(new Date(System.currentTimeMillis()));
		System.out.println(newClient.player.getName() + " has joined the chat session");

		registerChatters(newClient);
    }
	private void registerChatters(GameClient newClient){
		try{
			ChatClientIF nextClient = ( ChatClientIF )Naming.lookup("rmi://" + newClient.getServerAddress() + ":" + newClient.getServerPort() + "/ClientChatService");

			clients[userCount] = newClient;
			userCount++;
			//nextClient.messageFromServer("[Server] : Hello " + newClient.player.getName() + " you are now free to chat.\n");

			//sendToAll("[Server] : " + newClient.player.getName() + " has joined the group.\n");

			updateUserList();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
    private void updateUserList() {
		String[] currentUsers = getUserList();
		for(GameClient c : clients){
			try {
				//c.gameLobby.updateUserList(currentUsers);
				ChatClientIF chatClientIF = (ChatClientIF) Naming.lookup("rmi://" + c.getServerAddress() + ":" + c.getServerPort() + "/ChatClientService");
				chatClientIF.updateUserList(currentUsers, c);
			}
			catch (Exception e) {
				System.out.println(e);
			}
		}
	}
    private String[] getUserList(){
        String[] names = new String[clients.length];
		for(int i = 0; i< names.length; i++){
			if(clients[i]!= null){
				names[i] = clients[i].player.getName();
			}
		}

		return names;
    }
    @Override
    public void leaveChat(String userName) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'leaveChat'");
    }

    @Override
    public void sendPM(int[] privateGroup, String privateMessage) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendPM'");
    }
    
    
}
