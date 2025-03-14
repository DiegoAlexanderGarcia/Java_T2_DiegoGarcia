package EjercicioProfe;


    public class Carrera {
        //Objeto estático para el testigo que se pasarán entre ellos
        private static final Object testigo = new Object();

        public static void main(String[] args) {
            System.out.println("Empieza la carrera!");
            long tiempoInicio = System.currentTimeMillis();

            //Creamos los 4 atletas
            Atleta atleta4 = new Atleta("Atleta 4",null);
            Atleta atleta3 = new Atleta("Atleta 3",atleta4);
            Atleta atleta2 = new Atleta("Atleta 2",atleta3);
            Atleta atleta1 = new Atleta("Atleta 1",atleta2);

            //Inicializar los 4 atletas
            atleta1.start();
            atleta2.start();
            atleta3.start();
            atleta4.start();

            // El primer atleta empieza con el testigo
            synchronized (testigo) {
                atleta1.tieneTestigo = true;
                testigo.notify();
            }

            // Esperar a que el último atleta termine
            try{
                //Atleta 4
                atleta4.join();
                //Calcular el tiempo total de la carrera
                long tiempoFin=System.currentTimeMillis();
                double tiempoTotal = (tiempoFin-tiempoInicio)/1000;
                System.out.println("¡Carrera Finalizada! El tiempo tomado fue de: "+ tiempoTotal+ " segundos.");
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        //Clase interna Atleta que tiene Thread
        static class Atleta extends Thread{
            private String nombre;
            private Atleta siguienteAtleta;
            private boolean tieneTestigo= false;

            public Atleta(String nombre, Atleta siguienteAtleta){
                this.nombre=nombre;
                this.siguienteAtleta=siguienteAtleta;
            }

            private void esperarTestigo() throws InterruptedException{
                synchronized (testigo){
                    while(!tieneTestigo){
                        testigo.wait();
                    }
                }
            }

            private void pasarTestigo(){
                synchronized (testigo){
                    if(siguienteAtleta != null){
                        System.out.println(nombre+" le pasa el testigo a "+ siguienteAtleta.nombre);
                        tieneTestigo=false;
                        siguienteAtleta.tieneTestigo=true;
                        testigo.notify();
                    }
                }
            }
            public void run(){
                try{
                    //Esperar a que se reciba el testigo
                    esperarTestigo();
                    //Empieza a correr
                    System.out.println(nombre+" ha recibido el testigo y comienza a correr....");
                    //Calcular la carrera de manera aleatoria
                    int tiempoCarrera = 9000 + (int)(Math.random()*2000);
                    Thread.sleep(tiempoCarrera);
                    System.out.println(nombre+" ha terminado su tramo en "+(tiempoCarrera/1000)+ " segundos.");

                    //Si hay un siguiente atleta, pasa el testigo
                    if(siguienteAtleta != null){
                        pasarTestigo();
                    }else{
                        System.out.println(nombre + " ha cruzado la meta!");
                    }
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

        }
    }
