package mx.edu.utez.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    //Dirección de acceso
    public static String idAddress = "localhost";
    //Nombre de la base de datos
    public static String database = "proyecto";
    //Nombre de usuario para realizar la conexión
    public static String user = "root";
    //Contraseña
    public static String password = "root";
    //Puerto /**Depende de la configuración de la máquina**/
    public static String port = "3306";
    //Zona horaria "no es necesario en todos los equipos"
    public static String hourZone = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" ;

    public static Connection getConnection() throws SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException exception){
            System.err.println(exception.getMessage());
        }

        String connectionUrl= "jdbc:mysql://"+idAddress+":"+port+"/"+database+ hourZone;

        return DriverManager.getConnection(connectionUrl,user,password);
    }

    public static void main(String[] args) {
        Connection con;

        try{
            con = ConnectionDB.getConnection();
            System.out.println("Connection OK :D " + con);
            con.close();
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
