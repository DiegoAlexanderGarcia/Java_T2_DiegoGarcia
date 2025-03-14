package Concurrencias_Hilos.Hilos.ejercicio1;

public class main {
    public static void main(String[] args) {
        Thread t1 = new PingPong("p");
        Thread t2 = new PingPong("s");

        t1.start();
        t2.start();
    }
}
