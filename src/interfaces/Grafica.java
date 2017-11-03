/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import static interfaces.Inventario.getPrimerDiaDelMes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Omar
 */
public class Grafica extends javax.swing.JDialog {

    puntoventa001.Procedimientos p = new puntoventa001.Procedimientos();

    /**
     * Creates new form Grafica
     */
    public Grafica(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        panelDepto = new javax.swing.JPanel();
        lblGradicando = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        year = new com.toedter.calendar.JYearChooser();
        jButton2 = new javax.swing.JButton();
        panelMensual = new javax.swing.JPanel();
        lblGrafica2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hoy", "Ayer", "Esta semana", "La semana pasada", "Este mes", "Periodo especifico" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Periodo:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Desde:");

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.setEnabled(false);

        jDateChooser2.setDateFormatString("yyyy-MM-dd");
        jDateChooser2.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Hasta:");

        jButton1.setText("Graficar...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDeptoLayout = new javax.swing.GroupLayout(panelDepto);
        panelDepto.setLayout(panelDeptoLayout);
        panelDeptoLayout.setHorizontalGroup(
            panelDeptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDeptoLayout.setVerticalGroup(
            panelDeptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );

        lblGradicando.setBackground(new java.awt.Color(0, 153, 153));
        lblGradicando.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDepto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addComponent(lblGradicando, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 341, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblGradicando, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDepto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Ventas por departamento", jPanel1);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Año:");

        jButton2.setText("Graficar...");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMensualLayout = new javax.swing.GroupLayout(panelMensual);
        panelMensual.setLayout(panelMensualLayout);
        panelMensualLayout.setHorizontalGroup(
            panelMensualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelMensualLayout.setVerticalGroup(
            panelMensualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 541, Short.MAX_VALUE)
        );

        lblGrafica2.setBackground(new java.awt.Color(0, 153, 153));
        lblGrafica2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMensual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGrafica2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 613, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addComponent(lblGrafica2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMensual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Ventas mensuales", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        if (jComboBox5.getSelectedIndex() == 5) {
            jDateChooser1.setEnabled(true);
            jDateChooser2.setEnabled(true);
        } else {
            jDateChooser1.setEnabled(false);
            jDateChooser2.setEnabled(false);
        }
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.out.println("Grafica 1");
        lblGradicando.setText("Graficando...");
        int opc = jComboBox5.getSelectedIndex();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date hoy = new Date();
        int now = new Date().getDay();
        Date inicio = null, fin = null;
        String titulo = "";
        switch (opc) {
            case 0:
                inicio = hoy;
                fin = hoy;
                titulo = "hoy";
                jDateChooser1.setEnabled(false);
                jDateChooser2.setEnabled(false);
                break;
            case 1:
                Calendar c = new GregorianCalendar();
                c.setTimeInMillis(hoy.getTime());
                c.add(Calendar.DATE, -1);
                inicio = new Date(c.getTimeInMillis());
                fin = new Date(c.getTimeInMillis());
                titulo = "ayer";
                jDateChooser1.setEnabled(false);
                jDateChooser2.setEnabled(false);
                break;
            case 2:
                Calendar cDomingo = new GregorianCalendar();
                cDomingo.setTimeInMillis(hoy.getTime());
                cDomingo.add(Calendar.DATE, -now);
                Calendar cSabado = new GregorianCalendar();
                cSabado.setTimeInMillis(hoy.getTime());
                cSabado.add(Calendar.DATE, +(6 - now));
                inicio = new Date(cDomingo.getTimeInMillis());
                fin = new Date(cSabado.getTimeInMillis());
                titulo = "esta semana";
                jDateChooser1.setEnabled(false);
                jDateChooser2.setEnabled(false);
                break;
            case 3:
                Calendar cDomingoPasado = new GregorianCalendar();
                cDomingoPasado.setTimeInMillis(hoy.getTime());
                cDomingoPasado.add(Calendar.DATE, -now - 7);
                Calendar cSabadoPasado = new GregorianCalendar();
                cSabadoPasado.setTimeInMillis(hoy.getTime());
                cSabadoPasado.add(Calendar.DATE, +(6 - now - 7));
                inicio = new Date(cDomingoPasado.getTimeInMillis());
                fin = new Date(cSabadoPasado.getTimeInMillis());
                titulo = "la semana pasada";
                jDateChooser1.setEnabled(false);
                jDateChooser2.setEnabled(false);
                break;
            case 4:
                inicio = getPrimerDiaDelMes();
                fin = hoy;
                titulo = "este mes";
                jDateChooser1.setEnabled(false);
                jDateChooser2.setEnabled(false);
                break;
            case 5:
                jDateChooser1.setEnabled(true);
                jDateChooser2.setEnabled(true);
                break;
        }
        if (jDateChooser1.isEnabled()) {
            inicio = jDateChooser1.getDate();
            fin = jDateChooser2.getDate();
            titulo = inicio + " a " + fin;
        }
        //Datos para la grafica
        String i = sdf.format(inicio);
        String f = sdf.format(fin);

        java.util.ArrayList<String[]> polares = p.getDatosPolares(i, f);

        DefaultPieDataset data = new DefaultPieDataset();
        for (int j = 0; j < polares.size(); j++) {
            //System.out.print(polares.get(j)[0] + "  -   ");
            //System.out.println(polares.get(j)[1]);
            double valor = polares.get(j)[1] == null ? 0 : Double.parseDouble(polares.get(j)[1]);
            if (valor != 0) {
                data.setValue(polares.get(j)[0], valor);
            }
            //System.out.print(polares.get(j)[0] + "  -   ");
            //System.out.println(polares.get(j)[1]);
        }
        /*    data.setValue("C", 40);
        data.setValue("Java", 45);
        data.setValue("Python", 15);
         */
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createPieChart("Ventas de " + titulo, data, true, true, false);

        // Mostrar Grafico
        ChartPanel frame = new ChartPanel(chart);
        frame.setBounds(5, 10, panelDepto.getWidth() - 5, panelDepto.getHeight() - 10);
        panelDepto.removeAll();
        panelDepto.add(frame);
        lblGradicando.setText("");
        this.repaint();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.out.println("Grafica 2");
        lblGrafica2.setText("Graficando...");
        // Fuente de Datos
        int axo = year.getYear();
        Date hoy = new Date();
        String meses[] = {"Enero", "Febrero", "Marzo", "Abríl", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        if (hoy.getYear() == axo) {
            for (int i = 12; i >= hoy.getMonth(); i--) {
                meses[i] = null;
            }
        }

        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
        ArrayList<String[]> datosLineales = null;

        for (int i = 0; i < meses.length; i++) {
            if (meses[i] != null) {
                datosLineales = p.getDatosLineales(axo, (i + 1));
                for (int j = 0; j < datosLineales.size(); j++) {
                    double valor = datosLineales.get(j)[1] == null ? 0 : Double.parseDouble(datosLineales.get(j)[1]);
                    if (valor != 0) {
                        String categoria = datosLineales.get(j)[0];
                        line_chart_dataset.addValue(valor, categoria, meses[i]);
                    }
                }

            } else {
                break;
            }
        }

        // Creando el Grafico
        JFreeChart chart = ChartFactory.createLineChart("Ventas en el " + axo, "Mes", "Ventas ($)", line_chart_dataset, PlotOrientation.VERTICAL,
                true, true, false);

        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(5, 10, panelMensual.getWidth() - 5, panelMensual.getHeight() - 10);
        panelMensual.removeAll();
        panelMensual.add(chartPanel);
        lblGrafica2.setText("");
        this.repaint();

    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Grafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Grafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Grafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Grafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Grafica dialog = new Grafica(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblGradicando;
    private javax.swing.JLabel lblGrafica2;
    private javax.swing.JPanel panelDepto;
    private javax.swing.JPanel panelMensual;
    private com.toedter.calendar.JYearChooser year;
    // End of variables declaration//GEN-END:variables
}