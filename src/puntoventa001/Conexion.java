package puntoventa001;

/**
 *
 * @author Roberto
 */
import interfaces.Interfaz;
import java.io.FileNotFoundException;
import java.sql.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    public static java.sql.Connection con;
    static Statement stt;
    private String errorMensaje = null;
    private final String PATH = "jdbc:mysql://localhost:3306/ventasbasic";

    public Conexion() {
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
           con = DriverManager.getConnection(PATH, "root", "");
            stt = con.createStatement();
        } catch (SQLException sqle) {
            errorMensaje = sqle.getMessage();
        } catch (ClassNotFoundException cnfe) {
            errorMensaje = "Error al cargar el driver de conexion!!\nPongase en contacto con el administrador del sistema...\n" + cnfe.getMessage();
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String leerHost() {
        java.io.FileReader fr;
        java.io.BufferedReader bf;
        String line = "ERROR";
        try {
            fr = new java.io.FileReader("host.txt");
            bf = new java.io.BufferedReader(fr);
            line = bf.readLine();
            bf.close();
        } catch (FileNotFoundException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Se ha terminado tu periodo de prueba\nContactate cal num: 4961135581 o envia un mail a olara@utzac.edu.mx\nPara adquirir el software completo ", "EASY VENTAS", 0);
        } catch (java.io.IOException ioe) {
            javax.swing.JOptionPane.showMessageDialog(null, "No se pudo leer datos desde el archivo de configuracion de ticket.\nIntente volver a configurarlo", "Iniciando aplicacion", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        return line;
    }

    public boolean nueva(String usuario, String password) {
        boolean es = false;
        try {
            
            ResultSet rs = stt.executeQuery("SELECT * FROM usuario WHERE usuario ='" + usuario + "' AND pass ='" + password + "'");
            while (rs.next()) {
                Interfaz.tipoUsuario = Integer.parseInt(rs.getString("tipo"));
                Main.interfaz.user.setText("Le atiende: " + rs.getString("nombre"));
                Interfaz.usuario = rs.getString("id");
                es = true;
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return es;
    }

    public boolean cerrar() {
        try {
            con.close();
            if (con.isClosed()) {
                return true;
            } else {
                errorMensaje = "Error al cerrar sesion!! Intentelo mas tarde...\nSi continuan los problemas pongace en contacto con el administrador del sistema";
                return false;
            }
        } catch (SQLException sqle) {
            errorMensaje = sqle.getMessage();
            return false;
        }
    }

    public String getErrorMensaje() {
        return errorMensaje;
    }

    public Statement getStatement() {
        return stt;
    }
}
