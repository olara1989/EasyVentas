/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package puntoventa001;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;

/**
 *
 * @author Roberto
 */
public class Ticket {

    public static String titulo;
    public static String subtitulo;
    private String fecha;
    private String hora;
    private String[] columnas = {"Articulo", "Cnt", "P/U", "Sub.Tot"};
    private String[][] lista;
    private String total = "$00.00";
    private String pago = "$00.00";
    private String cambio = "$00.00";
    public static String msjFinal;
    public static int longitudLinea = 30;
    private int longs[] = {12,6,6,6};
    public static char separadorLine = '=';
    public static String texto = "";

    public static void leerDatos(){
        java.io.FileReader fr;
        java.io.BufferedReader bf;
        try {
            fr = new java.io.FileReader("setupTicket.rach");
            bf = new java.io.BufferedReader(fr);
            String datos[] = new String[40];
            String line = "";
            int i = 0;
            while((line = bf.readLine())!=null){
                datos[i] = line;
                i++;
            }
            bf.close();
            titulo = datos[0];
            subtitulo = datos[1];
            msjFinal = datos[2];
            separadorLine = datos[3].charAt(0);
            longitudLinea = Integer.parseInt(datos[4]);
            //System.out.println(titulo + "," + subtitulo + "," +msjFinal+ "," + separadorLine +"," +longitudLinea);
        } catch (FileNotFoundException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "No se pudo encontrar el archivo de configuracion del ticket.\nIntente volver a configurarlo", "Iniciando aplicacion", javax.swing.JOptionPane.ERROR_MESSAGE);
        }catch(java.io.IOException ioe){
            javax.swing.JOptionPane.showMessageDialog(null, "No se pudo leer datos desde el archivo de configuracion de ticket.\nIntente volver a configurarlo", "Iniciando aplicacion", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void guardarDatos(){
        java.io.FileWriter fw;
        java.io.BufferedWriter bw;
        try {
            fw = new java.io.FileWriter("setupTicket.rach");
            bw = new java.io.BufferedWriter(fw);
            String datos[] = {titulo ,subtitulo,msjFinal,""+ separadorLine ,""+longitudLinea};
            int i = 0;
            while(i<datos.length){
                bw.write(datos[i]);
                bw.newLine();
                i++;
            }
            bw.close();
        } catch (FileNotFoundException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "No se pudo encontrar el archivo de configuracion del ticket.\nIntentelo mas tarde", "Guardadno datos en disco local", javax.swing.JOptionPane.ERROR_MESSAGE);
        }catch(java.io.IOException ioe){
            javax.swing.JOptionPane.showMessageDialog(null, "No se pudo scribir datos en el archivo de configuracion de ticket.\nIntentelo mas tarde", "Guardadndo datos en disco local", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    public Ticket() {
        fecha = getCurrentDate();
        hora = getCurrentTime();
        lista = new String[1][5];
    }
    
    private String getCurrentDate(){
        Calendar c = Calendar.getInstance();
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH) +1;
        int ano = c.get(Calendar.YEAR);
        String date = "";
        if(dia<10)
            date += "0";
        date += dia + "/";
        if(mes<10)
            date += "0";
        date += mes + "/";
        if(ano<10)
            date += "0";
        date += ano;
        return date;
    }

    private String getCurrentTime(){
        Calendar c = Calendar.getInstance();
        int hor = c.get(Calendar.HOUR);
        int min = c.get(Calendar.MINUTE);
        int seg = c.get(Calendar.SECOND);
        int type = c.get(Calendar.AM_PM);
        String time = "";
        time += hor + ":";
        if(min<10)
            time += "0";
        time += min + ":";
        if(seg<10)
            time += "0";
        time += seg + " ";
        if(type == Calendar.AM)
            time += "AM";
        else time += "PM";
        return time;
    }

    public String getCambio() {
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }



    public String[][] getLista() {
        return lista;
    }

    public void setLista(String[][] lista) {
        this.lista = lista;
    }

    public int getLongitudLinea() {
        return longitudLinea;
    }

    public void setLongitudLinea(int longitudLinea) {
        Ticket.longitudLinea = longitudLinea;
        dividirLongitudes();
    }

    public String getMsjFinal() {
        return msjFinal;
    }

    public void setMsjFinal(String msjFinal) {
        Ticket.msjFinal = msjFinal;
    }

    public char getSeparadorLine() {
        return separadorLine;
    }

    public void setSeparadorLine(char separadorLine) {
        Ticket.separadorLine = separadorLine;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        Ticket.subtitulo = subtitulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        Ticket.titulo = titulo;
    }

    private void dividirLongitudes(){
        longs[0] = (int)(longitudLinea * .4);
        longs[1] = (int)(longitudLinea * .2);
        longs[2] = (int)(longitudLinea * .2);
        longs[3] = (int)(longitudLinea * .2);
        /*System.out.println("Longitud : "+longitudLinea);
        for(int i:longs)
        System.out.println("l="+i);*/
    }

    private String getLineaSeparadora(){
        String line = "";
        for(int i=0;i<longitudLinea;i++)
            line+= separadorLine;
        return line + "\n";
    }

    private String getNEspacios(int n){
        String espacio ="";
        for(int i=0;i<n;i++)
            espacio+=" ";
        /*System.out.println("regresnado " + n);*/
        return espacio;
    }

    private String[] separarFrase(String frase){
        return frase.split(" ");
    }

    private String getLineaCentrada(String linea){
        String centrada = "";
        int espacio = (int)(longitudLinea/2 - linea.length()/2);
        //System.out.println(espacio);
        String espace = getNEspacios(espacio);
        centrada = espace + linea + espace;
        return centrada;
    }

    private String getLinea(String tit){
        String lineaTitulo = "";
        if(tit.length()>longitudLinea){
            String lines[] = separarFrase(tit);
            String tmp = "";
            for(int i=0;i<lines.length;i++){
                if( tmp.length() + lines[i].length() <= longitudLinea){
                    tmp+=lines[i] + " ";
                    System.err.println(tmp);
                    if(i==lines.length-1)
                        lineaTitulo += getLineaCentrada(tmp) + "\n";
                }else{
                    lineaTitulo += getLineaCentrada(tmp) + "\n" ;
                    tmp = "";
                    i--;
                }
            }
        }else
            lineaTitulo = getLineaCentrada(tit) + "\n";
        return lineaTitulo;
    }

    private String getLineaTitulo(){
        return getLinea(titulo);
    }

    private String getLineaSubtitulo(){
        return getLinea(subtitulo);
    }

    private String getLineaCabeceraz(){
        String lineaTitulos = "";
        int i=0;
        for(String s:columnas){
            lineaTitulos += s + getNEspacios(longs[i] - s.length());
            System.out.println("Espacios:"+longs[i]);
            i++;
        }
        return lineaTitulos + "\n";
    }

    private String getLineaDatos(){
        String lineaDatos = "";
        for(String[]arr:lista){
            int i = 0;
            for(String s:arr){
                if(s.length()>longs[i] && i==0)s = s.substring(0,longs[i]-1);
                if(i==1)s=" "+s;
                lineaDatos += s + getNEspacios(longs[i] - s.length());
                i++;
            }
            lineaDatos += "\n";
        }
        return lineaDatos + "\n";
    }

    private String getLineaTotal(){
        return getNEspacios(longitudLinea - (total+"TOTAL: ").length()) + "TOTAL: " + total+ "\n";
    }

    private String getLineaPago(){
        return getNEspacios(longitudLinea - (pago+"EFECTIVO: ").length()) + "EFECTIVO: " + pago+ "\n";
    }

    private String getLineaCambio(){
        return getNEspacios(longitudLinea - (cambio+"CAMBIO: ").length()) + "CAMBIO: " + cambio + "\n";
    }

    private String getLineaFecha(){
        return /*getNEspacios(longitudLinea - (fecha + "Expedido: ").length()) +*/ "Expedido: " + fecha + "\n";
    }

    private String getLineaHora(){
        return /*getNEspacios(longitudLinea - (hora + "Expedido: ").length()) + */"          " + hora + "\n";
    }

    public void createTexto(){
        texto = "";
        texto += getLineaTitulo();
        texto += getLineaSeparadora();
        texto += getLineaSubtitulo();
        texto += getLineaSeparadora();
        texto += getLineaFecha();
        texto += getLineaHora();
        texto += getLineaSeparadora();
        texto += getLineaCabeceraz();
        texto += getLineaSeparadora();
        texto += getLineaDatos();
        texto += getLineaSeparadora();
        texto += getLineaTotal();
        texto += getLineaPago();
        texto += getLineaCambio();
        texto += getLineaSeparadora();
        texto += getLinea(msjFinal);
        pintar(System.out);
    }
    
    public void imprimir(){
        FileWriter fw;
        try {
            fw = new FileWriter("USB");
            fw.write(texto);
            for(int i=0;i<7;i++)
                fw.write("\n");
            char [] cut = {0x1B,'m'};
            fw.write(cut);
            fw.close();
        } catch (IOException ex) {
            
            javax.swing.JOptionPane.showMessageDialog(null, "Ocurrio un problema al imprimir el ticket!!\n"+ex.getMessage(), "S.Te.Ve. Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    public void pintar(PrintStream sp){
        sp.print(texto);
    }
}
