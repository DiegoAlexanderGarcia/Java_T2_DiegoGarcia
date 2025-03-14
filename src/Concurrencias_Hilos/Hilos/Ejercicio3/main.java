package Concurrencias_Hilos.Hilos.Ejercicio3;

public class main {
    public static void main(String[] args) {
        PingPongSincronizado p1 = new PingPongSincronizado("h");
        PingPongSincronizado p2 = new PingPongSincronizado("o");
        PingPongSincronizado p3 = new PingPongSincronizado("l");
        PingPongSincronizado p4 = new PingPongSincronizado("a");
        p1.start();
        p2.start();
        p3.start();
        p4.start();
    }
}
