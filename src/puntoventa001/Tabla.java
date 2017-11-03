/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package puntoventa001;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javax.swing.JPopupMenu;

/**
 *
 * @author Roberto
 */
public class Tabla extends JPanel implements MouseListener{

    private JTextField datosFields[][];
    private JLabel titulosColumnas[];
    private String datos[][];
    private String titulos[];
    private int espacioVertical = 0;
    private int espacioHorizontal = 0;
    private int numeroColumnas = 0;
    private int numeroFilas = 0;
    private KeyListener keyListener;
    private Color foreground;
    private int alignment;
    private Font font;
    private boolean enabled;
    private boolean editable;
    private Color backgroundSelect = Color.YELLOW;
    private JPopupMenu menuEmergente;
    private Color defaultBakcground = Color.WHITE;
    private int selectedIndexFila = -1;


    private void loadDefaults() {
        setLayout(new java.awt.GridLayout(numeroFilas + 1, numeroColumnas, espacioHorizontal, espacioVertical));
        datosFields = new JTextField[numeroFilas][numeroColumnas];
        titulosColumnas = new JLabel[numeroColumnas];
        datos = new String[numeroFilas][numeroColumnas];
        titulos = new String[numeroFilas];
        llenarCampos();
    }

    public Tabla() {
        super();
        loadDefaults();
        this.addMouseListener(this);
    }

    public Tabla(int espacioHorizontal, int espacioVertical) {
        super();
        this.espacioHorizontal = espacioHorizontal;
        this.espacioVertical = espacioVertical;
        loadDefaults();
        this.addMouseListener(this);
    }

    @SuppressWarnings("static-access")
    public Tabla(int espacioHorizontal, int espacioVertical, int columnas, int filas) {
        super();
        this.espacioHorizontal = espacioHorizontal;
        this.espacioVertical = espacioVertical;
        this.numeroColumnas = columnas;
        this.numeroFilas = filas;
        loadDefaults();
        this.addMouseListener(this);
    }

    @SuppressWarnings("static-access")
    public Tabla(int espacioHorizontal, int espacioVertical, String[][] datos, String titulos[]) {
        super();
        this.espacioHorizontal = espacioHorizontal;
        this.espacioVertical = espacioVertical;
        this.datos = datos;
        this.titulos = titulos;
        this.numeroColumnas = titulos.length;
        this.numeroFilas = datos.length;
        setLayout(new java.awt.GridLayout(numeroFilas + 1, numeroColumnas, espacioHorizontal, espacioVertical));
        this.datosFields = new JTextField[numeroFilas][numeroColumnas];
        this.titulosColumnas = new JLabel[numeroColumnas];
        llenarCampos();
        this.addMouseListener(this);
    }

    private void llenarCampos() {
        int i = 0, j = 0;
        for (String titulo : titulos) {
            titulosColumnas[i] = new JLabel(titulo, JLabel.CENTER);
            add(titulosColumnas[i]);
            i++;
        }
        i = 0;
        assert (datosFields.length == numeroFilas);
        for (String[] fila : datos) {
            j = 0;
            for (String dato : fila) {
                datosFields[i][j] = new JTextField(dato);
                datosFields[i][j].addKeyListener(keyListener);
                datosFields[i][j].setForeground(foreground);
                datosFields[i][j].setFont(font);
                datosFields[i][j].setHorizontalAlignment(alignment);
                datosFields[i][j].setEnabled(enabled);
                datosFields[i][j].setEditable(editable);
                if(menuEmergente!=null)datosFields[i][j].setComponentPopupMenu(menuEmergente);
                datosFields[i][j].addMouseListener(this);
                datosFields[i][j].setBackground(this.defaultBakcground);
                add(datosFields[i][j]);
                j++;
            }
            i++;
        }
    }

    public int getNumeroColumnas() {
        return numeroColumnas;
    }

    public int getNumeroFilas() {
        return numeroFilas;
    }

    public String[] getTitulos() {
        return titulos;
    }

    public String getValorEn(int fila, int columna) {
        if (fila >= numeroFilas || columna >= numeroColumnas) {
            throw new ArrayIndexOutOfBoundsException(fila + ">=" + numeroFilas + " o " + columna + ">=" + numeroFilas);
        }
        return datos[fila][columna];
    }

    public void setValorEn(int fila, int columna, String valor) {
        if (fila >= numeroFilas || columna >= numeroColumnas) {
            throw new ArrayIndexOutOfBoundsException(fila + ">=" + numeroFilas + " o " + columna + ">=" + numeroFilas);
        }
        datos[fila][columna] = valor;
        datosFields[fila][columna].setText(valor);
    }

    private void saveCurrentData() {
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                datos[i][j] = datosFields[i][j].getText();
            }
        }
    }

    private void changeLengthData(int newLenghtRows, int newLenghtCoulumn) {
        String[][] newData = new String[newLenghtRows][newLenghtCoulumn];
        String[] newTitulos = new String[newLenghtCoulumn];
        setLayout(new java.awt.GridLayout(newLenghtRows + 1, newLenghtCoulumn, espacioHorizontal, espacioVertical));
        JTextField newDataFields[][] = new JTextField[newLenghtRows][newLenghtCoulumn];
        JLabel newTitulosCoulumns[] = new JLabel[newLenghtCoulumn];
        saveCurrentData();
        for (int i = numeroFilas - 1, y = newLenghtRows - 1; i >= 0; i--, y--) {
            for (int j = numeroColumnas - 1, x = newLenghtCoulumn - 1; j >= 0; j--, x--) {
                newData[y][x] = datos[i][j];
            }
        }
        for (int i = 0; i < numeroColumnas; i++) {
            newTitulos[i] = titulos[i];
        }
        datos = newData;
        titulos = newTitulos;
        datosFields = newDataFields;
        titulosColumnas = newTitulosCoulumns;
        numeroFilas = newLenghtRows;
        numeroColumnas = newLenghtCoulumn;
        System.out.println("aa"+numeroFilas);
    }

    private void changeLengthData2(int newLenghtRows, int newLenghtCoulumn) {
        String[][] newData = new String[newLenghtRows][newLenghtCoulumn];
        String[] newTitulos = new String[newLenghtCoulumn];
        //setLayout(new java.awt.GridLayout(newLenghtRows + 1, newLenghtCoulumn, espacioHorizontal, espacioVertical));
        JTextField newDataFields[][] = new JTextField[newLenghtRows][newLenghtCoulumn];
        JLabel newTitulosCoulumns[] = new JLabel[newLenghtCoulumn];
        saveCurrentData();
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                newData[i][j] = datos[i][j];
            }
        }
        for (int i = 0; i < numeroColumnas; i++) {
            newTitulos[i] = titulos[i];
        }
        datos = newData;
        titulos = newTitulos;
        datosFields = newDataFields;
        titulosColumnas = newTitulosCoulumns;
        numeroFilas = newLenghtRows;
        numeroColumnas = newLenghtCoulumn;
    }

    public void addFila() {
        changeLengthData(numeroFilas + 1, numeroColumnas);
        this.removeAll();
        setLayout(new java.awt.GridLayout(numeroFilas + 1, numeroColumnas, espacioHorizontal, espacioVertical));
        llenarCampos();
        this.updateUI();
    }

    public void addFila(String... datos) {
        addFila();
        for (int i = 0; i < numeroColumnas && i < datos.length; i++) {
            this.setValorEn(0, i, datos[i]);
        }
    }

    public void addFilaAlFinal(){
        changeLengthData2(numeroFilas + 1, numeroColumnas);
        this.removeAll();
        setLayout(new java.awt.GridLayout(numeroFilas + 1, numeroColumnas, espacioHorizontal, espacioVertical));
        llenarCampos();
        this.updateUI();
    }

    public void addColumna(String name) {
        changeLengthData(numeroFilas, numeroColumnas + 1);
        datos[0][numeroColumnas - 1] = name;
        titulos[numeroColumnas - 1] = name;
        this.removeAll();
        llenarCampos();
        this.updateUI();
    }

    public JTextField getCamporEn(int fila, int columna) {
        if (fila >= numeroFilas || columna >= numeroColumnas) {
            throw new ArrayIndexOutOfBoundsException(fila + ">=" + numeroFilas + " o " + columna + ">=" + numeroFilas);
        }
        return datosFields[fila][columna];
    }

    public String[][] getDatos() {
        return datos;
    }

    public int getSelectedIndexFila() {
        return selectedIndexFila;
    }

    public boolean buscarSig(String cad){
        for(int i=0;i<numeroFilas;i++)
            for(int j=0;j<numeroColumnas;j++)
                if(getValorEn(i, j).contains(cad)){
                    selectFila(i);
                    datosFields[i][j].selectAll();
                    return true;
                }
        java.awt.Toolkit.getDefaultToolkit().beep();
        return false;
    }

    public void setDatos(String[][] newDatos,String [] titulos,int filas,int columnas) {
        this.datos = newDatos;
        numeroFilas = filas;
        numeroColumnas = columnas;
        datosFields = new JTextField[numeroFilas][numeroColumnas];
        this.titulos = titulos;
        titulosColumnas = new JLabel[numeroColumnas];
        removeAll();
        setLayout(new java.awt.GridLayout(numeroFilas + 1, numeroColumnas, espacioHorizontal, espacioVertical));
        llenarCampos();
        this.updateUI();
    }

    @Override
    public void addKeyListener(KeyListener teclado) {
        this.keyListener = teclado;
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                datosFields[i][j].addKeyListener(teclado);
            }
        }
    }

    public void selectCampo(int fila,int columna){
        if(fila >= numeroFilas || columna >= numeroColumnas)throw new ArrayIndexOutOfBoundsException("Posicion no encontrada");
        datosFields[fila][columna].setBackground(backgroundSelect);
    }

    public void selectColumna(int indiceColumna){
        if(indiceColumna >= numeroColumnas)throw new ArrayIndexOutOfBoundsException(indiceColumna);
        for(int i = 0; i < numeroFilas;i++)
            for(int j = 0; j < numeroColumnas;j++)
                if(j==indiceColumna)datosFields[i][j].setBackground(backgroundSelect);
                else datosFields[i][j].setBackground(this.defaultBakcground);
    }

    public void selectFila(int indiceFila){
        if(indiceFila >= numeroFilas)throw new ArrayIndexOutOfBoundsException(indiceFila);
        for(int i = 0; i < numeroFilas;i++)
            for(int j = 0; j < numeroColumnas;j++)
                if(i==indiceFila)datosFields[i][j].setBackground(backgroundSelect);
                else datosFields[i][j].setBackground(this.defaultBakcground);
        selectedIndexFila = indiceFila;
    }

    public void allEditable(boolean state) {
        this.editable = state;
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                datosFields[i][j].setEditable(state);
            }
        }
    }

    public void allEnabled(boolean state) {
        this.enabled = state;
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                datosFields[i][j].setEnabled(state);
            }
        }
    }

    public void setFontAll(Font f) {
        this.font = f;
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                datosFields[i][j].setFont(f);
            }
        }
    }

    public void setAlignmentAll(int alignment) {
        this.alignment = alignment;
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                datosFields[i][j].setHorizontalAlignment(alignment);
            }
        }
    }

    public void setForegroundAll(Color c) {
        this.foreground = c;
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                datosFields[i][j].setForeground(c);
            }
        }
    }

    public int getSumaColumnaAsInt(int indiceColumna) throws NumberFormatException {
        if (indiceColumna >= numeroColumnas) {
            throw new IndexOutOfBoundsException("" + (numeroColumnas - 1) + "indice maximo. intenanto acceder a indice " + indiceColumna);
        }
        int sum = 0;
        for (int i = 0; i < numeroColumnas; i++) {
            sum += Integer.parseInt(datos[indiceColumna][i]);
        }
        return sum;
    }

    public double getSumaColumnaAsDouble(int indiceColumna) throws NumberFormatException {
        if (indiceColumna >= numeroColumnas) {
            throw new IndexOutOfBoundsException("" + (numeroColumnas - 1) + "indice maximo. intenanto acceder a indice " + indiceColumna);
        }
        double sum = 0;
        for (int i = 0; i < numeroFilas; i++) {
            sum += Double.parseDouble(datos[i][indiceColumna]);
        }
        return sum;
    }

    public String[][] getSeccionDatos(int filaInic,int colInic){
        String[][] dat = new String[this.numeroFilas - filaInic][numeroColumnas - colInic];
        System.out.println("ddd"+ this.numeroFilas);
        for(int i=filaInic,m=0;i<numeroFilas;i++,m++)
            for(int j=colInic,n=0;j<numeroColumnas;j++,n++){
                dat[m][n] = datos[i][j];
                System.out.println(dat[m][n]);
            }
        return dat;
    }

    public void addMenuEmergernte(JPopupMenu menu){
        this.menuEmergente = menu;
        for(int i=0;i<numeroFilas;i++)
            for(int j=0;j<numeroColumnas;j++){
                //datosFields[i][j].add(menuEmergente);
               datosFields[i][j].setComponentPopupMenu(menu);
            }
    }

    public void actualizar(){
        this.llenarCampos();
        this.updateUI();
    }

    public void mouseClicked(MouseEvent e) {
        
    }

    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        for(int i=0;i<numeroFilas;i++)
            for(int j=0;j<numeroColumnas;j++){
                //datosFields[i][j].add(menuEmergente);
               if(datosFields[i][j].equals(e.getSource()))
                   this.selectFila(i);
            }
    }

    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
