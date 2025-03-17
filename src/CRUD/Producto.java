package CRUD;

public class Producto {
    private int id;
    private String nombre;
    private double precio;

    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public double getPrecio() {return precio;}

    public void setPrecio(double precio) {this.precio = precio;}

    @Override
    public String toString() {
        return id + "," + nombre + "," + precio;
    }
    public static Producto fromString(String string) {
        String[] listaNueva = string.split(",");
        return new Producto(Integer.parseInt(listaNueva[0]), listaNueva[1], Double.parseDouble(listaNueva[2]));
    }
}
