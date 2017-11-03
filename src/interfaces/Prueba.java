/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.*;

/**
 *
 * @author Win
 */
public class Prueba {

    public static void main(String[] args) {
        Date hoy = new Date();
        int now = new Date().getDay();
        Calendar cDomingo = new GregorianCalendar();
        cDomingo.setTimeInMillis(hoy.getTime());
        cDomingo.add(Calendar.DATE, -(now - 7));
        Calendar cSabado = new GregorianCalendar();
        cSabado.setTimeInMillis(hoy.getTime());
        cSabado.add(Calendar.DATE, +(6 - (now - 7)));
        System.out.println(new Date(cDomingo.getTimeInMillis()));
        System.out.println(new Date(cSabado.getTimeInMillis()));
    }
}
