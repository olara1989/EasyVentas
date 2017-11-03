/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package puntoventa001;

import interfaces.Interfaz;
import interfaces.Licencia;
import java.io.*;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Omar
 */
public class Main {

    static interfaces.Login login;
    public static Interfaz interfaz;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new PantallaCargandoMain();
        File f, host;
        f = new File("config");
        host = new File("host.txt");
        //Escritura
        /**
        try {
            
            if (!f.exists()) {
                FileWriter w = new FileWriter(f);
                BufferedWriter bw = new BufferedWriter(w);
                PrintWriter wr = new PrintWriter(bw);
                //escribimos en el archivo 
                wr.write("" + new Date().getTime());
                //concatenamos en el archivo sin borrar lo existente
                //ahora cerramos los flujos de canales de datos, al cerrarlos el archivo quedará guardado con información escrita
                //de no hacerlo no se escribirá nada en el archivo
                wr.close();
                bw.close();
            } else {
                FileReader r = new FileReader(f);
                BufferedReader br = new BufferedReader(r);
                long contenido = Long.parseLong(br.readLine());
                if (new Date().getTime() >= (contenido + 1209600000)) {
                    host.delete();
                    Licencia l = new Licencia(login, true);
                    l.setVisible(true);
                    l.setLocationRelativeTo(null);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Aqui
        */        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "NO SE PUEDE CARGAR LA INTERFAZ" + e.getMessage(), "S.Te.Ve.", JOptionPane.ERROR_MESSAGE);
                }

                try {
                    interfaz = new Interfaz();
                    login = new interfaces.Login();
                    login.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "NO SE PUEDE CARGAR LA INTERFAZ" + e.getMessage(), "S.Te.Ve.", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        Ticket.leerDatos();

    }
}
