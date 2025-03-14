package Concurrencias_Hilos.Hilos.Ejercicio3;

public class PingPongSincronizado extends Thread {

    private String word;
    public PingPongSincronizado(String s) {
        word = s;
    }

    public void run() {
        synchronized (getClass()) {
            for (int i = 0; i < 3000; i++) {
                System.out.println(word);
                System.out.flush();
                getClass().notifyAll();
                try{
                    getClass().wait();
                } catch (InterruptedException e) {
                    getClass().notifyAll();
                }
            }
        }
    }
}
