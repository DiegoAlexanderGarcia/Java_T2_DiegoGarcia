package Dia6.gestionempleados.Polimorfismo.src.polimorfismo;

public class Vehiculo {
    private String marca;
    private String modelo;

    public Vehiculo(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public void mostrarDetalles(){
        System.out.println("Marca: " + marca + "\nModelo: " + modelo);        
    }
    
}
