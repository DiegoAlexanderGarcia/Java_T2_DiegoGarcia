package Dia16;
import java.util.Random;

public class Carrera {
        public static final Object testigo = new Object();
        public static long tiempoInicio;

        public static void main(String[] args) {
            Atleta[] atletas = new Atleta[4];
            tiempoInicio = System.currentTimeMillis();

            // Crear y lanzar los hilos de los atletas
            for (int i = 0; i < 4; i++) {
                atletas[i] = new Atleta(i + 1);
                atletas[i].start();
            }
        }
    }

    class Atleta extends Thread {
        private int numero;
        private static int turno = 1;
        private static final Random rand = new Random();

        public Atleta(int numero) {
            this.numero = numero;
        }

        @Override
        public void run() {
            synchronized (Carrera.testigo) {
                while (numero != turno) { // Espera su turno
                    try {
                        Carrera.testigo.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Simula el tiempo de carrera entre 9 y 11 segundos
                int tiempoCarrera = 9000 + rand.nextInt(2000);
                System.out.println("Atleta " + numero + " corriendo...");
                try {
                    Thread.sleep(tiempoCarrera);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                long tiempoTotal = System.currentTimeMillis() - Carrera.tiempoInicio;
                System.out.println("Atleta " + numero + " entregó el testigo en " + (tiempoTotal / 1000.0) + " segundos");

                turno++; // Pasa el turno al siguiente atleta
                Carrera.testigo.notifyAll(); // Notifica a los demás hilos
            }
        }
}
