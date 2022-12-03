package client;

import server.Quadratic;
import server.Server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        //1099 -  default port for RMI registry
        Registry registry = LocateRegistry.getRegistry();
        Quadratic server = (Quadratic) registry.lookup(Server.UNIQUE_BINDING_NAME);

        //test
        System.out.println(server.solveEquation(1,2,50000));
        System.out.println(server.solveEquation(1,2,1));
        System.out.println(server.solveEquation(1,4,3));
    }
}
