package server;

import server.dto.Response;

import java.rmi.RemoteException;

public class QuadraticImpl implements Quadratic{
    @Override
    public Response solveEquation(double a, double b, double c) {
        double D = b * b - 4 * a * c;
        if (D > 0) {
            double x1, x2;
            x1 = (-b - Math.sqrt(D)) / (2 * a);
            x2 = (-b + Math.sqrt(D)) / (2 * a);
            return new Response(x1, x2, "Два различных корня уравнения");
        }
        else if (D == 0) {
            double x;
            x = -b / (2 * a);
            return new Response(x, x, "Два одинаковых корня уравнения");
        }
        else {
            return new Response(null, null, "Уравнение не имеет корней");
        }
    }
}
