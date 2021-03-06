/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import puntoventa001.Procedimientos;

/**
 *
 * @author usuario1
 */
public class Usuarios extends javax.swing.JDialog {

    /**
     * Creates new form Usuarios
     */
    Procedimientos p = new Procedimientos();

    public Usuarios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(this);
        ArrayList<String[]> listaT = p.getAllUsuarios();
        actualizarTabla(usuarios, listaT);

    }

    private void actualizarTabla(javax.swing.JTable t, ArrayList<String[]> listaT) {
        this.LimpiarJTable(t);
        ArrayList<String> row = new ArrayList<String>();
        for (String[] prod : listaT) {
            for (String string : prod) {
                row.add(string);
            }
            ((DefaultTableModel) t.getModel()).addRow(row.toArray());
            row = new ArrayList<String>();
        }
    }

    public void LimpiarJTable(javax.swing.JTable t) {
        DefaultTableModel mode = (DefaultTableModel) t.getModel();
        int rango = mode.getRowCount() - 1;
        for (int i = rango; i >= 0; i--) {

            mode.removeRow(i);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        usuarios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        usuario = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();

        jMenuItem1.setText("Modificar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("Eliminar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        jMenuItem3.setText("Buscar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem3);

        jMenuItem4.setText("Agregar nuevo");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem4);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "NUMERO", "Nombre del cajero"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        usuarios.setComponentPopupMenu(jPopupMenu1);
        usuarios.getTableHeader().setReorderingAllowed(false);
        usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usuariosMouseClicked(evt);
            }
        });
        usuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usuariosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(usuarios);
        usuarios.getColumnModel().getColumn(0).setResizable(false);
        usuarios.getColumnModel().getColumn(0).setPreferredWidth(2);
        usuarios.getColumnModel().getColumn(1).setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Nombre completo:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Usuario:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Contraseña:");

        nombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });

        usuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/enabled.gif"))); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/disabled.gif"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/add.gif"))); // NOI18N
        jButton3.setText("Nuevo cajero");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/delete.gif"))); // NOI18N
        jButton4.setText("Eliminar cajero");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Buscar:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Administracion de cajeros");

        pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(pass, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                .addComponent(usuario, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))))
                .addContainerGap(90, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!actualizar) {
            guardar();
        } else {
            actualizar();
        }
        actualizar = false;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        id = usuarios.getValueAt(usuarios.getSelectedRow(), 0).toString();
        String[] usuario = p.getDetailsUsuario(id);
        nombre.setText(usuario[3]);
        this.usuario.setText(usuario[1]);
        jButton1.setText("Actualizar");
        actualizar = true;
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        nombre.setText("");
        usuario.setText("");
        actualizar = false;
        jButton1.setText("Guardar");
        nombre.requestFocus();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        String s = javax.swing.JOptionPane.showInputDialog(null, "Ingresa la busqueda");
        ArrayList<String[]> listaT = p.buscarUsuarios(s);
        actualizarTabla(usuarios, listaT);

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        if (!actualizar) {
            guardar();
        } else {
            actualizar();
        }
    }//GEN-LAST:event_nombreActionPerformed

    private void usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioActionPerformed
        if (!actualizar) {
            guardar();
        } else {
            actualizar();
        }
    }//GEN-LAST:event_usuarioActionPerformed
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        id = usuarios.getValueAt(usuarios.getSelectedRow(), 0).toString();
        if (javax.swing.JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar el usuario") == javax.swing.JOptionPane.YES_OPTION) {
            p.elminarUsuario(id);
            ArrayList<String[]> listaT = p.getAllUsuarios();
            actualizarTabla(usuarios, listaT);
        }


    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        usuario.setText("");
        pass.setText("");
        nombre.setText("");
        usuario.requestFocus();
        jButton1.setText("Guardar");
        this.actualizar = false;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        int i = usuarios.getSelectedRow();
        if (i == -1) {
            javax.swing.JOptionPane.showMessageDialog(null, "Selecciona un usuario a eliminar", "ERROR", 0);
        } else {
            int opc = javax.swing.JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar este usuario");
            if (opc == javax.swing.JOptionPane.YES_OPTION) {
                if (p.elminarUsuario(id)) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Usuario eliminado");
                    ArrayList<String[]> listaT = p.getAllUsuarios();
                    actualizarTabla(usuarios, listaT);
                }
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void usuariosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuariosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuariosKeyPressed

    private void usuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuariosMouseClicked
        String id = usuarios.getValueAt(usuarios.getSelectedRow(), 0).toString();
        String[] proveedor = p.getDetailsUsuario(id);
        usuario.setText(proveedor[1]);
        pass.setText(proveedor[2]);
        actualizar = true;
        nombre.setText(proveedor[3]);
        jButton1.setText("Modificar usuario");
        this.id = id;

    }//GEN-LAST:event_usuariosMouseClicked

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            
            setVisible(false);
            
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            usuarios.requestFocus();
            usuarios.setRowSelectionInterval(0, 0);
            String id = usuarios.getValueAt(usuarios.getSelectedRow(), 0).toString();
            String[] proveedor = p.getDetailsUsuario(id);
            usuario.setText(proveedor[1]);
            pass.setText(proveedor[2]);
            actualizar = true;
            nombre.setText(proveedor[3]);
            jButton1.setText("Modificar usuario");
            this.id = id;
        } else {
            char ch = evt.getKeyText(evt.getKeyCode()).toLowerCase().charAt(0);
            if ((ch >= 97 && ch <= 122) || (ch >= 48 && ch <= 57)) {
                String filtro = jTextField1.getText() + ch;
                actualizarTabla(usuarios, p.buscarUsuarios(filtro));
            }
        }


    }//GEN-LAST:event_jTextField1KeyPressed

    private void passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passActionPerformed
        if (!actualizar) {
            guardar();
        } else {
            actualizar();
        }
    }//GEN-LAST:event_passActionPerformed
    boolean actualizar = false;
    String id = "";

    public void guardar() {
        if (p.agregarUsuario(nombre.getText(), usuario.getText(), pass.getText(), "1")) {
            javax.swing.JOptionPane.showMessageDialog(null, "Usuario agregado correctamente");
            ArrayList<String[]> listaT = p.getAllUsuarios();
            actualizarTabla(usuarios, listaT);
            nombre.setText("");
            pass.setText("");
            usuario.setText("");
        } else {
            javax.swing.JOptionPane.showConfirmDialog(null, "Ocurrio un error al insertar el usuario", "Error", 0);
        }
    }

    public void actualizar() {
        if (p.modifUsuario(nombre.getText(), usuario.getText(), pass.getText(), id)) {
            javax.swing.JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente");
            ArrayList<String[]> listaT = p.getAllUsuarios();
            actualizarTabla(usuarios, listaT);
            actualizar = false;
            nombre.setText("");
            pass.setText("");
            usuario.setText("");
            jButton1.setText("Guardar");
        } else {
            javax.swing.JOptionPane.showConfirmDialog(null, "Ocurrio un error al insertar el usuario", "Error", 0);
        }
    }
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField nombre;
    private javax.swing.JPasswordField pass;
    private javax.swing.JTextField usuario;
    private javax.swing.JTable usuarios;
    // End of variables declaration//GEN-END:variables
}
