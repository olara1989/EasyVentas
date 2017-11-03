/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package puntoventa001;

/**
 *
 * @author Rafa
 */
import javax.swing.ImageIcon;

public class PantallaCargandoMain {

  PantallaCargando screen;

  public PantallaCargandoMain() {
    inicioPantalla();
	screen.velocidadDeCarga();
  }

  private void inicioPantalla() {
    ImageIcon myImage = new ImageIcon(getClass().getResource("/puntoventa001/logo.png"));
    screen = new PantallaCargando(myImage);
    screen.setLocationRelativeTo(null);
    screen.setProgresoMax(100);
    screen.setVisible(true);
  }

  
}