package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static final String UNIQUE_BINDING_NAME = "server.quadratic";

    public static void main(String[] args) throws RemoteException {

        //remote server object
        Quadratic server = new QuadraticImpl();
        //заглушка - приёмник удаленных вызовов
        Quadratic stub = (Quadratic) UnicastRemoteObject
                .exportObject(server, 0);

        //local registry
        //1099 -  default port for RMI registry
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind(UNIQUE_BINDING_NAME, stub);
    }
}
