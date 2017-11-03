/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;


import static interfaces.Inventario.getPrimerDiaDelMes;
import java.text.SimpleDateFormat;
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
public class Graficas extends javax.swing.JDialog {

    /**
     * Creates new form Graficas
     */
    public Graficas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jButton2 = new javax.swing.JButton();
        panelMensual = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Graficas");
        setSize(new java.awt.Dimension(450, 650));

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
            .addGap(0, 393, Short.MAX_VALUE)
        );

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
                        .addGap(0, 383, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
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
            .addGap(0, 413, Short.MAX_VALUE)
        );

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
                        .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(0, 667, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMensual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Ventas mensuales", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 918, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        }
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.out.println("Grafica 1");    
        
        int opc = jComboBox5.getSelectedIndex();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date hoy = new Date();
        int now = new Date().getDay();
        Date inicio = null, fin = null;
        switch (opc) {
            case 0:
                inicio = hoy;
                fin = hoy;
                jDateChooser1.setEnabled(false);
                jDateChooser2.setEnabled(false);
                break;
            case 1:
                Calendar c = new GregorianCalendar();
                c.setTimeInMillis(hoy.getTime());
                c.add(Calendar.DATE, -1);
                inicio = new Date(c.getTimeInMillis());
                fin = new Date(c.getTimeInMillis());
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
                jDateChooser1.setEnabled(false);
                jDateChooser2.setEnabled(false);
                break;
            case 4:
                inicio = getPrimerDiaDelMes();
                fin = hoy;
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
        }
      //Datos para la grafica
        String i = sdf.format(inicio);
        String f = sdf.format(fin);
        puntoventa001.Procedimientos p = new puntoventa001.Procedimientos();
        java.util.ArrayList<String[]> polares = p.getDatosPolares(i,f);
        
        DefaultPieDataset data = new DefaultPieDataset();
        for (int j = 0; j < polares.size(); j++) {
               data.setValue(polares.get(j)[0],Double.parseDouble(polares.get(j)[1]));
               System.out.print(polares.get(j)[0]+"  -   ");
               System.out.println(polares.get(j)[1]);
        }
        /*    data.setValue("C", 40);
            data.setValue("Java", 45);
            data.setValue("Python", 15);
        */
            // Creando el Grafico
            JFreeChart chart = ChartFactory.createPieChart(
                    "Ejemplo Rapido de Grafico en un ChartFrame",
                    data,
                    true,
                    true,
                    false);

            // Mostrar Grafico
            ChartPanel frame = new ChartPanel(chart);
            frame.setBounds(5,10,panelDepto.getWidth()-5,panelDepto.getHeight()-10);
            panelDepto.removeAll();
            panelDepto.add(frame);
            
            this.repaint();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.out.println("Grafica 2");
        // Fuente de Datos
        
        try {
            DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
            line_chart_dataset.addValue(80, "visitas", "Julio");
            line_chart_dataset.addValue(300, "visitas", "Agosto");
            line_chart_dataset.addValue(600, "visitas", "Septiembre");
            line_chart_dataset.addValue(1200, "visitas", "Octubre");            
            line_chart_dataset.addValue(2400, "visitas", "Noviembre");

            // Creando el Grafico
            JFreeChart chart = ChartFactory.createLineChart("Trafico en el Blog", "Mes", "Visitas", line_chart_dataset, PlotOrientation.VERTICAL,
                    true, true, false);

            // Mostrar Grafico
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setBounds(5,10,panelMensual.getWidth()-5,panelMensual.getHeight()-10);
            panelMensual.removeAll();
            panelMensual.add(chartPanel);
            
            this.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
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
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JPanel panelDepto;
    private javax.swing.JPanel panelMensual;
    // End of variables declaration//GEN-END:variables
}