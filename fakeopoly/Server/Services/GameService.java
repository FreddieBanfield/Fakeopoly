package Server.Services;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Shared.Interfaces.GameServiceIF;
import Shared.Interfaces.UpdateServiceIF;

public class GameService extends UnicastRemoteObject implements GameServiceIF {
    private ArrayList<UpdateServiceIF> clients;

    public GameService() throws RemoteException {
        super();
        clients = new ArrayList<>();
    }

    @Override
    public void updateUserList(int port) throws RemoteException {

        try {
            //Registry reg = LocateRegistry.getRegistry(9001);
            //UpdateServiceIF update = (UpdateServiceIF) reg.lookup("UpdateService" + port);
            UpdateServiceIF update = (UpdateServiceIF) Naming.lookup("rmi://localhost:9001/UpdateService" + port);
            update.updatePlayerList();
            System.out.println("Working so far" + port);
            //update.updatePlayerList();
            clients.add(update);
        } catch (Exception e) {
            System.out.println(e);
        }
        updateAll();
    }

    private void updateAll(){
        try{
            for(UpdateServiceIF client : clients){
                client.updatePlayerList();
            }
        }catch(Exception e){
            System.out.print(e);
        }
    }
}
