/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puntoventa001;

/**
 *
 * @author Hp
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyTableModel extends DefaultTableCellRenderer {


    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
String valor = table.getValueAt(row,1).toString();
        if (valor.substring(valor.length()-1,valor.length()).equalsIgnoreCase("*")) {
            this.setBackground(new Color(255,204,0));
        }
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
    }
}
