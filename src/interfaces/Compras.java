/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Compras.java
 *
 * Created on May 5, 2013, 6:51:39 PM
 */
package interfaces;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import puntoventa001.Main;
import puntoventa001.Procedimientos;
import puntoventa001.Ticket;

/**
 *
 * @author Omar
 */
public class Compras extends javax.swing.JInternalFrame {

    /** Creates new form Compras */
    public Compras() {
        initComponents();
        jTextField7.requestFocus();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu3 = new javax.swing.JPopupMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        panelVenta = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVenta = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaNombres = new javax.swing.JList();
        jPanel10 = new javax.swing.JPanel();
        jTextField7 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();

        jPopupMenu3.setFocusable(false);
        jPopupMenu3.setOpaque(false);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, 0));
        jMenuItem7.setText("Agregar");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenuItem7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jMenuItem7KeyPressed(evt);
            }
        });
        jPopupMenu3.add(jMenuItem7);

        setTitle("Compras");
        setFrameIcon(new ImageIcon(this.getClass().getResource("/puntoventa001/logo2.png")));

        panelVenta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Totales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(0, 0, 204));

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(255, 0, 0));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Cambio");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Total:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField6)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        tablaVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Cantidad", "Precio U", "Sub-total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaVenta.setComponentPopupMenu(jPopupMenu3);
        tablaVenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tablaVentaFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(tablaVenta);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 819, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de productos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        listaNombres.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaNombres.setToolTipText("Lista de articulo almacenados");
        listaNombres.setComponentPopupMenu(jPopupMenu3);
        listaNombres.setSelectedIndex(1);
        listaNombres.setValueIsAdjusting(true);
        listaNombres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaNombresMouseClicked(evt);
            }
        });
        listaNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                listaNombresKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(listaNombres);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Insercion de codigos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField7.setFocusAccelerator('j');
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField7KeyPressed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton7.setText("Pagar");
        jButton7.setToolTipText("(Shift+End)");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jButton7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton7KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelVentaLayout = new javax.swing.GroupLayout(panelVenta);
        panelVenta.setLayout(panelVentaLayout);
        panelVentaLayout.setHorizontalGroup(
            panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVentaLayout.createSequentialGroup()
                .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelVentaLayout.setVerticalGroup(
            panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVentaLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVentaLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listaNombresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaNombresMouseClicked
        // TODO add your handling code here:
        if (evt.getButton() == MouseEvent.MOUSE_CLICKED) {
            javax.swing.JOptionPane.showMessageDialog(null, "gdgfd");
        }
}//GEN-LAST:event_listaNombresMouseClicked

    private void listaNombresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listaNombresKeyPressed
        // TODO add your handling code here:
    /*if(evt.getKeyCode() == KeyEvent.VK_F7){
        boolean state = ! jPanel9.isVisible();
        jPanel9.setVisible(state);
        ventanaIventas.updateUI();
        }
        if(evt.getKeyCode() == KeyEvent.VK_INSERT && vendiendo){
        incluir(listaNombres.getSelectedValue(),"No ha seleccionado ningun producto....");
        listaNombres.transferFocus();
        this.jTextField7.requestFocus();
        }
        if(evt.getKeyCode() == KeyEvent.VK_DELETE && !vendiendo)
        borrando();
        if(evt.getKeyCode() == KeyEvent.VK_F2 && !vendiendo)
        modificando();
        if(evt.getKeyCode() == KeyEvent.VK_F3  && !vendiendo)
        insertandoNuevo();
        if(evt.getKeyCode() == KeyEvent.VK_F8)
        buscando();*/
}//GEN-LAST:event_listaNombresKeyPressed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        incluir(jTextField7.getText());
}//GEN-LAST:event_jTextField7ActionPerformed
    private void incluir(String codBar) {
        if (codBar == null) {
            javax.swing.JOptionPane.showMessageDialog(null, "No ha podido leer el valor de la caja de texto", "S.Te.Ve. .>. Inlcuir en venta", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } else {
            this.incluirEnVenta(codBar, 1);
            jTextField7.setText("");
            jTextField7.requestFocus();
        }
    }

    public void iniciarCompra() {
        this.jTextField5.setText("");
        this.jTextField6.setText("");
        this.jLabel2.setText("Cambio:");
        this.jTextField5.setText("");
        this.jTextField6.setText("");
        this.jTextField7.requestFocus();
        vendiendo = true;
    }

    private void buscando() {
        String name = jTextField7.getText();
        listaNombres.removeAll();
        ArrayList<String[]> prod = procedimiento.buscarProductos(name);
        Vector cads = new Vector();
        for (String[] strings : prod) {
            cads.add(strings[1]);
            listaNombres.setSelectedIndex(0);
        }
        listaNombres.setListData(cads);
        listaNombres.requestFocus();

    }

    private boolean estaEnListaVenta(String cod) {
        boolean resp = false;
        for (int i = 0; i < tablaVenta.getRowCount(); i++) {
            if (tablaVenta.getValueAt(i, 0).toString().equals(cod)) {
                resp = true;
                break;
            }
        }
        return resp;
    }

    @SuppressWarnings("static-access")
    private void incluirEnVenta(String codBar, int cantidad) {
        String datos[] = procedimiento.getDetailsProducto(codBar);
        if (datos == null) {
            buscando();
        } else {

            String nombre = datos[1];
            String precio = datos[4];
            if (!estaEnListaVenta(datos[0])) {
                double subtotal = Double.parseDouble(precio) * cantidad;

                ((DefaultTableModel) this.tablaVenta.getModel()).addRow(new Object[]{codBar, nombre, "" + cantidad, precio, "" + subtotal});
                double total = 0.0;
                for (int i = 0; i < tablaVenta.getRowCount(); i++) {
                    total += Double.parseDouble(tablaVenta.getValueAt(i, 4).toString());

                }
                String tot = "" + total;
                this.jTextField5.setText("$ " + tot);
                articulos++;
            } else {
                int indice = this.indiceQueesIgual(datos[0]);
                int c = cantidad + Integer.parseInt(tablaVenta.getValueAt(indice, 2).toString());
                tablaVenta.setValueAt(c, indice, 2);
                tablaVenta.setValueAt(c * Double.parseDouble(tablaVenta.getValueAt(indice, 3).toString()), indice, 4);

                double total = 0.0;
                for (int i = 0; i < tablaVenta.getRowCount(); i++) {
                    total += Double.parseDouble(tablaVenta.getValueAt(i, 4).toString());

                }
                String tot = "" + total;//double total = t.getSumaColumnaAsDouble(4);
                //String tot = df.format(total);
                this.jTextField5.setText("$ " + tot);
            }
        }
    }

    private int indiceQueesIgual(String cod) {
        int indice = 0;
        for (int i = 0; i < tablaVenta.getRowCount(); i++) {
            if (tablaVenta.getValueAt(i, 0).toString().equals(cod)) {
                break;
            }
            indice++;
        }
        return indice;
    }
    private void jTextField7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //   incluir(jTextField7.getText());
        }
}//GEN-LAST:event_jTextField7KeyPressed
    @SuppressWarnings("static-access")
    private void cobrar() {
        if (articulos < 1) {
            JOptionPane.showMessageDialog(null, "No hay articulo agregados a la venta...", "S.Te.Ve. Cobrar!!", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                activar = false;
                double total = Double.parseDouble(this.jTextField5.getText().substring(1));
                double pago = Double.parseDouble(JOptionPane.showInputDialog(null, "Cantidad con que paga el cleinte", "S.Te.Ve .:. Cobrar!!", JOptionPane.QUESTION_MESSAGE));
                if (total > pago) {
                    JOptionPane.showMessageDialog(null, "La cantidad introducida no alcanza a cubrir el total del adeudo...\n Intenten de nuevo o cancele la venta.", "S.Te.Ve. Cobrar!!", JOptionPane.ERROR_MESSAGE);
                } else {

                    for (int i = 0; i < tablaVenta.getRowCount(); i++) {
                        double c = Double.parseDouble(tablaVenta.getValueAt(i, 2).toString());
                        double p = Double.parseDouble(tablaVenta.getValueAt(i, 3).toString());
                        tablaVenta.setValueAt("" + c * p, i, 4);

                    }

                    double cambio = pago - total;
                    DecimalFormat df = new DecimalFormat();
                    df.setMinimumFractionDigits(2);
                    String camb = df.format(cambio);
                    jLabel2.setText("Pagó con $" + (pago) + " Su cambio es:");
                    jTextField6.setText("$ " + camb);
                    Calendar cal = Calendar.getInstance();
                    String aux = "";
                    if (cal.get(Calendar.MONTH) < 10) {
                        aux = "0";
                    }
                    String aux2 = "";
                    if (cal.get(Calendar.DAY_OF_MONTH) < 10) {
                        aux2 = "0";
                    }
                    String fecha = cal.get(Calendar.YEAR) + "-" + aux + (cal.get(Calendar.MONTH) + 1) + "-" + aux2 + cal.get(Calendar.DAY_OF_MONTH);
                    String hora = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
                    String[][] dat = new String[tablaVenta.getRowCount() - 0][tablaVenta.getColumnCount() - 1];
                    for (int i = 0, m = 0; i < tablaVenta.getRowCount(); i++, m++) {
                        for (int j = 1, n = 0; j < tablaVenta.getColumnCount(); j++, n++) {
                            dat[m][n] = tablaVenta.getValueAt(i, j).toString();
                        }

                    }
                    String[][] datosProd = new String[tablaVenta.getRowCount()][tablaVenta.getColumnCount()];
                    for (int i = 0; i < tablaVenta.getRowCount(); i++) {
                        for (int j = 0; j < tablaVenta.getColumnCount(); j++) {
                            datosProd[i][j] = tablaVenta.getValueAt(i, j).toString();

                        }
                    }
                    System.out.println(datosProd.length);
                    if (procedimiento.insertCompra(fecha, total, hora, datosProd)) {
                        JOptionPane.showMessageDialog(null, "Venta registrada con exito!!.\nGRACIAS POR SU COMPRA", "S.Te.Ve. Venta realizada", JOptionPane.INFORMATION_MESSAGE);
                        terminarVenta();
                        String totales = df.format(total);
                        // ((DefaultTableModel) this.model.getModel()).addRow(new Object[]{fecha, hora, totales});
                 
                        String sum = df.format(suma);
                        Ticket ticket = new Ticket();
                        String[][] daT = new String[tablaVenta.getRowCount() - 0][tablaVenta.getColumnCount() - 1];
                        for (int i = 0, m = 0; i < tablaVenta.getRowCount(); i++, m++) {
                            for (int j = 1, n = 0; j < tablaVenta.getColumnCount(); j++, n++) {
                                daT[m][n] = tablaVenta.getValueAt(i, j).toString();
                                System.out.println(daT[m][n]);
                            }

                        }
                        ticket.setLista(dat);
                        ticket.setTotal("" + total);
                        ticket.setCambio(camb);
                        ticket.setPago("" + pago);
                        ticket.createTexto();
                        //ticket.imprimir();
                        iniciarCompra();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha podido registrar la venta...\n Intenten de nuevo o cancele la venta.", "S.Te.Ve. Cobrar!!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Imposible realizar calculo con lod datos recien proporcionados...\n Intenten de nuevo o cancele la venta.", "S.Te.Ve. Cobrar!!", JOptionPane.ERROR_MESSAGE);
                //nfe.printStackTrace();
            }
        }
    }

    public void LimpiarJTable(javax.swing.JTable t) {
        DefaultTableModel mode = (DefaultTableModel) t.getModel();
        int rango = mode.getRowCount() - 1;
        for (int i = rango; i >= 0; i--) {

            mode.removeRow(i);
        }
    }

    private void terminarVenta() {
//        this.jButton2.setEnabled(true);
        this.LimpiarJTable(tablaVenta);
        this.jTextField7.setText("");
        this.jTextField5.setText("");
        this.jTextField6.setText("");
        this.jLabel2.setText("Cambio:");
        vendiendo = false;
        articulos = 0;
    }

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        cobrar();
}//GEN-LAST:event_jButton7ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        try {
            int cant = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("Cantidad de articulos"));
            String codigo = procedimiento.getCodigoBarras(listaNombres.getSelectedValue().toString());
            this.incluirEnVenta(codigo, cant);
        } catch (HeadlessException headlessException) {
        } catch (NumberFormatException numberFormatException) {
            javax.swing.JOptionPane.showMessageDialog(null, "Debes insertar un numero", "S.Te.Ve", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMenuItem7KeyPressed
        // TODO add your handling code here:
}//GEN-LAST:event_jMenuItem7KeyPressed

    private void tablaVentaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaVentaFocusLost

        double suma = 0;
        for (int i = 0; i < tablaVenta.getRowCount(); i++) {
            double c = Double.parseDouble(tablaVenta.getValueAt(i, 2).toString());
            double p = Double.parseDouble(tablaVenta.getValueAt(i, 3).toString());
            tablaVenta.setValueAt("" + c * p, i, 4);
            suma += (c * p);
        }
        jTextField5.setText("$ " + suma + "0");

    }//GEN-LAST:event_tablaVentaFocusLost

    private void jButton7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_END) {
            cobrar();

        }
    }//GEN-LAST:event_jButton7KeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JList listaNombres;
    private javax.swing.JPanel panelVenta;
    private javax.swing.JTable tablaVenta;
    // End of variables declaration//GEN-END:variables
    private Procedimientos procedimiento = new Procedimientos();
    private int articulos = 0;
    private double suma = 0;
    public boolean activar = false;
    private boolean vendiendo = false;
}
