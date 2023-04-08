package Shared.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientServiceIF extends Remote {

    // Declaring the method prototype
    public void testMessage() throws RemoteException; // Test message to console output only on that client
}
