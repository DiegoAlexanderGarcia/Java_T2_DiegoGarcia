package Concurrencias_Hilos.Hilos.ejercicio1;

public class PingPong extends Thread{
    private String word;
    public PingPong(String s){word=s;}

    public void run(){

        for(int i=0; i<300;i++){
            System.out.println(word);
            System.out.println();
        }

    }

}

