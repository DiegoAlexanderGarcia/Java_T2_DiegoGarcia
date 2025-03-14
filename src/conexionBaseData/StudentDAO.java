package conexionBaseData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Connection connection;

    public StudentDAO() {
        this.connection = DatabaseConfig.getConnection();
    }

    // Crear estudiante
    public boolean create(Student student) {
        String sql = "INSERT INTO students (nombre, apellido, edad) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, student.getNombre());
            pstmt.setString(2, student.getApellido());
            pstmt.setInt(3, student.getEdad());
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        student.setId(generatedKeys.getInt(1));
                    }
                }
                System.out.println("Estudiante creado exitosamente con ID: " + student.getId());
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al crear estudiante: " + e.getMessage());
        }
        return false;
    }

    // Leer todos los estudiantes
    public List<Student> readAll() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(extractStudentFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer estudiantes: " + e.getMessage());
        }
        return students;
    }

    // Leer estudiante por ID
    public Student readById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractStudentFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al leer estudiante: " + e.getMessage());
        }
        return null;
    }

    // Actualizar estudiante
    public boolean update(Student student) {
        String sql = "UPDATE students SET nombre = ?, apellido = ?, edad = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, student.getNombre());
            pstmt.setString(2, student.getApellido());
            pstmt.setInt(3, student.getEdad());
            pstmt.setInt(4, student.getId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Estudiante actualizado exitosamente");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar estudiante: " + e.getMessage());
        }
        return false;
    }

    // Eliminar estudiante
    public boolean delete(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Estudiante eliminado exitosamente");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar estudiante: " + e.getMessage());
        }
        return false;
    }

    private Student extractStudentFromResultSet(ResultSet rs) throws SQLException {
        return new Student(
            rs.getInt("id"),
            rs.getString("nombre"),
            rs.getString("apellido"),
            rs.getInt("edad")
        );
    }

    public void closeConnection() {
        DatabaseConfig.closeConnection();
    }
}
