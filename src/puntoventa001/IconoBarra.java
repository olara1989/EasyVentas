package puntoventa001;

import interfaces.Interfaz;
import interfaces.Salir;
import java.awt.event.*;
import java.awt.*;

public class IconoBarra {

    private PopupMenu popup;
    private TrayIcon Ticono;
    private MenuItem item, item1;
    private SystemTray st;
    static Interfaz interfaz;

    @SuppressWarnings("empty-statement")
    public IconoBarra() {

        //CREANDO EL POPU DE LA IMAGEN
        if (SystemTray.isSupported()) {
            st = SystemTray.getSystemTray();
            Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/puntoventa001/logo.png"));
            //Image img2= new Image ( "yourFile.gif"  ) ;
            popup = new PopupMenu();
            item = new MenuItem("Salir");
            //item.setImage (img2) ;
            item1 = new MenuItem("Maximizar");

            popup.add(item1);
            popup.addSeparator();
            popup.add(item);
            Ticono = new TrayIcon(img, "PUNTO DE VENTA", popup);

//SALIR
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //JOptionPane.showMessageDialog(null,"SALIENDO");
                    if (Main.interfaz.activar == true) {
                       Main.interfaz.salir();
                    } else {
                        System.exit(0);
                    }

                }
            });

//MENSAJE DEL ICONO
            Ticono.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ee) {

                    Main.interfaz.setVisible(true);
                    Main.interfaz.toFront();
                    st.remove(Ticono);
                }
            });

//MAXIMIZAR 
            item1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent eee) {

                    Main.interfaz.setVisible(true);
                    Main.interfaz.toFront();
                    st.remove(Ticono);
                }

            });

            Ticono.setImageAutoSize(true);
            try {
                st.add(Ticono);
            } catch (AWTException nel) {
                //System.err.println("No es posible agregar el icono al System Tray "+nel);
                javax.swing.JOptionPane.showMessageDialog(null, "No es posible agregar el icono al System Tray ", "S.Te.Ve", javax.swing.JOptionPane.ERROR_MESSAGE);
            };

        } else //System.err.println("Tu sistema no soporta el System Tray");
        {
            javax.swing.JOptionPane.showMessageDialog(null, "Tu sistema no soporta el System Tray", "S.Te.Ve", javax.swing.JOptionPane.ERROR_MESSAGE);
        }

    }
}
