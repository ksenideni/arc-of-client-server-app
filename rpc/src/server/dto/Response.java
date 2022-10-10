package server.dto;

import java.io.Serializable;

public class Response implements Serializable {
    private String message;
    private Double x1;
    private Double x2;

    public Response(Double x1, Double x2, String message) {
        this.x1 = x1;
        this.x2 = x2;
        this.message = message;
    }

    public Double getX1() {
        return x1;
    }

    public void setX1(Double x1) {
        this.x1 = x1;
    }

    public Double getX2() {
        return x2;
    }

    public void setX2(Double x2) {
        this.x2 = x2;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                ", x1=" + x1 +
                ", x2=" + x2 +
                '}';
    }
}
