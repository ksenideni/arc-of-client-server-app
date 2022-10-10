package server;

import server.dto.Response;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Quadratic extends Remote {
    Response solveEquation(double a, double b, double c) throws RemoteException;
}
