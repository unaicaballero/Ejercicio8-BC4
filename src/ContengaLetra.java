import java.sql.*;
import java.util.Scanner;

public class ContengaLetra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String usuario = "RIBERA";
        String password = "ribera";

        try (Connection conexion = DriverManager.getConnection(url, usuario, password)) {

            //Pido letra
            System.out.println("Que letra quieres que contenga");
            String letra  = sc.nextLine();

            //Saco los nombres de los empleados
            String sql = "SELECT * FROM EJEMPLOCONEXION WHERE nombre LIKE ?";
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, "%" +letra+"%");

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre  = rs.getString("nombre");
                int salario = rs.getInt("salario");

                //muestro el resultado
                System.out.println("ID: "+id + " Nombre: "+nombre + " Salario: "+salario);
            }

        } catch (SQLException e) {
            System.out.println("Error al añadir a la tabla" + e.getMessage());
        }
    }
}