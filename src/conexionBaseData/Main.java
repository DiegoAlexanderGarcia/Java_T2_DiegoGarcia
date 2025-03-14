package conexionBaseData;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static StudentDAO dao;
    private static Scanner scanner;

    public static void main(String[] args) {
        dao = new StudentDAO();
        scanner = new Scanner(System.in);

        try {
            boolean continuar = true;
            while (continuar) {
                mostrarMenu();
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        crearEstudiante();
                        break;
                    case 2:
                        mostrarTodosLosEstudiantes();
                        break;
                    case 3:
                        buscarEstudiantePorId();
                        break;
                    case 4:
                        actualizarEstudiante();
                        break;
                    case 5:
                        eliminarEstudiante();
                        break;
                    case 6:
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, intente de nuevo.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error en la aplicación: " + e.getMessage());
        } finally {
            scanner.close();
            dao.closeConnection();
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n=== MENÚ GESTIÓN DE ESTUDIANTES ===");
        System.out.println("1. Crear nuevo estudiante");
        System.out.println("2. Ver todos los estudiantes");
        System.out.println("3. Buscar estudiante por ID");
        System.out.println("4. Actualizar estudiante");
        System.out.println("5. Eliminar estudiante");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void crearEstudiante() {
        System.out.println("\n=== CREAR NUEVO ESTUDIANTE ===");
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese la edad: ");
        int edad = scanner.nextInt();

        Student newStudent = new Student();
        newStudent.setNombre(nombre);
        newStudent.setApellido(apellido);
        newStudent.setEdad(edad);

        if (dao.create(newStudent)) {
            System.out.println("Estudiante creado exitosamente con ID: " + newStudent.getId());
        } else {
            System.out.println("Error al crear el estudiante");
        }
    }

    private static void mostrarTodosLosEstudiantes() {
        System.out.println("\n=== LISTA DE ESTUDIANTES ===");
        List<Student> students = dao.readAll();
        if (students.isEmpty()) {
            System.out.println("No hay estudiantes registrados");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private static void buscarEstudiantePorId() {
        System.out.println("\n=== BUSCAR ESTUDIANTE POR ID ===");
        System.out.print("Ingrese el ID del estudiante: ");
        int id = scanner.nextInt();

        Student student = dao.readById(id);
        if (student != null) {
            System.out.println("Estudiante encontrado: " + student);
        } else {
            System.out.println("No se encontró ningún estudiante con el ID: " + id);
        }
    }

    private static void actualizarEstudiante() {
        System.out.println("\n=== ACTUALIZAR ESTUDIANTE ===");
        System.out.print("Ingrese el ID del estudiante a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Student student = dao.readById(id);
        if (student != null) {
            System.out.println("Estudiante actual: " + student);
            System.out.print("Nuevo nombre (Enter para mantener actual): ");
            String nombre = scanner.nextLine();
            System.out.print("Nuevo apellido (Enter para mantener actual): ");
            String apellido = scanner.nextLine();
            System.out.print("Nueva edad (0 para mantener actual): ");
            String edadStr = scanner.nextLine();

            if (!nombre.trim().isEmpty()) {
                student.setNombre(nombre);
            }
            if (!apellido.trim().isEmpty()) {
                student.setApellido(apellido);
            }
            if (!edadStr.trim().isEmpty() && !edadStr.equals("0")) {
                student.setEdad(Integer.parseInt(edadStr));
            }

            if (dao.update(student)) {
                System.out.println("Estudiante actualizado exitosamente");
            } else {
                System.out.println("Error al actualizar el estudiante");
            }
        } else {
            System.out.println("No se encontró ningún estudiante con el ID: " + id);
        }
    }

    private static void eliminarEstudiante() {
        System.out.println("\n=== ELIMINAR ESTUDIANTE ===");
        System.out.print("Ingrese el ID del estudiante a eliminar: ");
        int id = scanner.nextInt();

        Student student = dao.readById(id);
        if (student != null) {
            System.out.println("Estudiante a eliminar: " + student);
            System.out.print("¿Está seguro de eliminar este estudiante? (S/N): ");
            scanner.nextLine(); // Limpiar buffer
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("S")) {
                if (dao.delete(id)) {
                    System.out.println("Estudiante eliminado exitosamente");
                } else {
                    System.out.println("Error al eliminar el estudiante");
                }
            } else {
                System.out.println("Operación cancelada");
            }
        } else {
            System.out.println("No se encontró ningún estudiante con el ID: " + id);
        }
    }
}
