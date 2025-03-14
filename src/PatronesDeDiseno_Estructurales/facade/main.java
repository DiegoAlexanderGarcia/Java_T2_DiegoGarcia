package PatronesDeDiseno_Estructurales.facade;

public class main {
    static class Amplifier {
        void on() {
            System.out.println("Amplificador encendido");
        }
        static class DVDPlayer{
            void play() {
                System.out.println("Reproduciendo DVD. ");
            }
            static class HomeTheaterFacade {
                private Amplifier amp;
                private DVDPlayer dvd;

                public HomeTheaterFacade() {
                    this.amp = new Amplifier();
                    this.dvd = new DVDPlayer();
                }
                public void watchMovie() {
                    amp.on();
                    dvd.play();
                }
            }
            public static void main(String[] args) {
                HomeTheaterFacade homeTheater = new HomeTheaterFacade();
                homeTheater.watchMovie();
            }
        }
    }
}
