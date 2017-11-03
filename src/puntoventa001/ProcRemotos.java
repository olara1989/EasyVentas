/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package puntoventa001;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Hp
 */
public class ProcRemotos {
    
     ConexionRemota c = new ConexionRemota();
    public boolean guardarLic(String nombre) {
        try {
           
            c.stt.executeUpdate("Insert into licencias(licencia)"
                    + "values('" + nombre + "');");
            return true;
        } catch (MySQLIntegrityConstraintViolationException cve) {
            javax.swing.JOptionPane.showMessageDialog(null, "El deparamento ya exixte", "Error", 0);
            return false;
        } catch (SQLException e) {

            return false;
        }
    }
    
    public boolean validarLic(String lic){
         boolean es = false;
        try {
            System.out.println("SELECT * FROM licencias WHERE licencia ='" + lic + "' AND estado  = 0");
            ResultSet rs = c.stt.executeQuery("SELECT * FROM licencias WHERE licencia ='" + lic + "' AND estado  = 0");
            while (rs.next()) {                
                es = true;
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return es;
    }
     public boolean activarLic(String nombre) {
        try {
            String sql = ("UPDATE licencias SET estado = 1, fechaActivacion = NOW()"
                    + " WHERE  licencia = '" + nombre + "';");
            System.out.println(sql);
            c.stt.executeUpdate(sql);
            return true;
        } catch (MySQLIntegrityConstraintViolationException cve) {
            javax.swing.JOptionPane.showMessageDialog(null, "El deparamento ya exixte", "Error", 0);
            return false;
        } catch (SQLException e) {

            return false;
        }
    }
    
}
