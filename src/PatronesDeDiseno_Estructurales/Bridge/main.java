package PatronesDeDiseno_Estructurales.Bridge;

// bridge

public class main {
    interface Color {
        void ApplyColor();
    }
    static class RedColor implements Color {
        public void ApplyColor() {
            System.out.println("Aplicando color rojo.");
        }
    }
    static class BlueColor implements Color {
        public void ApplyColor() {
            System.out.println("Aplicando color Azul.");
        }
    }
    static abstract class Shape {
        protected Color color;
        public Shape(Color color) {
            this.color = color;
        }
        abstract void draw();
        }

        static class Circle extends Shape {
            public Circle(Color color) {super(color); }

            public void draw() {
                System.out.println("dibujando circulo ");
                color.ApplyColor();
            }
        }

            public static void main(String[] args) {
                Shape redCircle = new Circle(new RedColor());
                Shape blueCircle = new Circle(new BlueColor());

                redCircle.draw();
                blueCircle.draw();
            }
        }


