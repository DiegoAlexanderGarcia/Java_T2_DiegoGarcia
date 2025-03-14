package Persistencia.ArchivoSimple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PersistenciaFile {
    private static final String nombre_archivo = "./src/Persistencia/percistecia.txt";

    //Metodo para guardar
    public static void guardarDatos(String contenido) {
        try(FileWriter guardar = new FileWriter(nombre_archivo)) {
            guardar.write(contenido);
            System.out.println("Datos guardados exitosamente");
        }
        catch (IOException e){
            System.out.println("Error al guardar el archivo"+ e.getMessage());
        }
    }

    public static void leerDatos() {
        try(BufferedReader lector = new BufferedReader(new FileReader(nombre_archivo))){
            String linea;
            System.out.println("leyendo el archivo: ");
            while((linea = lector.readLine()) !=null){
                System.out.println(linea);
            }
        }
        catch (IOException e){
            System.out.println("Error al leer el archivo"+ e.getMessage());
        }
    }
}
