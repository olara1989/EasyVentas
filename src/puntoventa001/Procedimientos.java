/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package puntoventa001;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import interfaces.InicioCaja;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.*;

/**
 *
 * @author Omar
 */
public class Procedimientos {

    public void GeneraExcel(javax.swing.JTable table) {
        HSSFWorkbook libro = new HSSFWorkbook();
        HSSFSheet hoja = libro.createSheet();
        for (int i = 0; i < table.getRowCount() - 1; i++) {
            HSSFRow fila = hoja.createRow(i);
            if (i == 0) {
                for (int j = 0; j < table.getColumnCount() - 1; j++) {
                    HSSFCell celda = fila.createCell(j);
                    celda.setCellValue(new HSSFRichTextString(table.getColumnModel().getColumn(j).getHeaderValue().toString()));
                }
            } else {
                for (int j = 0; j < table.getColumnCount() - 1; j++) {
                    HSSFCell celda = fila.createCell(j);
                    if (table.getValueAt(i, j) != null) {
                        celda.setCellValue(new HSSFRichTextString(table.getValueAt(i, j).toString()));
                    }
                }
            }
            try {
                FileOutputStream elFichero = new FileOutputStream("holamundo.xls");
                libro.write(elFichero);
                elFichero.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean guardarArticulo(String codigo,
            String nombre, String precio,
            String descripcion, String precioC,
            String minimo, String p, String cantidad, String seVende, String precioM, String usaI, boolean imp, String impuesto) {
        try {
            if (minimo.equals("")) {
                minimo = "0";
            }
            if (cantidad.equals("")) {
                cantidad = "0";
            }
            if (precioM.equals("")) {
                precioM = "0";
            }
            if (precioC.equals("")) {
                precioC = "0";
            }
            if (precio.equals("")) {
                precio = "0";
            }
            String sql = "";
            if (getDetailsProducto3(codigo) == null) {
                sql = "Insert into articulo(codbar,nombre,precio,descrip,precio_Compra,min,proveedor,inventario,activo,sevende,precio_mayoreo,utiliza,impuesto,tipo)"
                        + "values('" + codigo + "','" + nombre + "','" + precio + "','" + descripcion + "','"
                        + precioC + "','" + minimo + "','" + p + "','" + cantidad + "','1','" + seVende + "','" + precioM + "','" + usaI + "','" + imp + "','" + impuesto + "');";
            } else {
                sql = "UPDATE ARTICULO SET codbar='" + codigo + "',nombre='" + nombre + "',precio='" + precio + "',"
                        + "descrip='" + descripcion + "',precio_Compra='" + precioC + "',min='" + minimo + "',"
                        + "proveedor='" + p + "',inventario='" + cantidad + "',"
                        + "activo='1',sevende='" + seVende + "',"
                        + "precio_mayoreo='" + precioM + "',utiliza='" + usaI + "' WHERE codbar = '" + codigo + "';";

            }
            Conexion.stt.executeUpdate(sql);
            System.out.println(sql);
            if (Boolean.parseBoolean(usaI)) {
                if (getDetailsProducto3(codigo) == null) {
                    String[] prod = getDetailsProducto2(codigo);
                    String movSQL = "insertInventa( INTO movimiento (dia,hora,habia,tipo,cantidad,cajero,articulo) "
                            + "values(CURRENT_DATE(),CURRENT_TIME(),'" + prod[5] + "','Entradas'," + prod[5] + ",'" + Main.interfaz.usuario + "','" + prod[14] + "');";
                    System.out.println(movSQL);
                    Conexion.stt.execute(movSQL);
                } else {
                    String[] prod = getDetailsProducto2(codigo);
                    String movSQL = "INSERT INTO movimiento (dia,hora,habia,tipo,cantidad,cajero,articulo) "
                            + "values(CURRENT_DATE(),CURRENT_TIME(),'" + prod[5] + "','Entradas'," + cantidad + ",'" + Main.interfaz.usuario + "','" + prod[14] + "');";
                    System.out.println(movSQL);
                    Conexion.stt.execute(movSQL);
                }
            }

            return true;
        } catch (MySQLIntegrityConstraintViolationException cve) {
            javax.swing.JOptionPane.showMessageDialog(null, "El codigo ya exixte", "Error", 0);
            cve.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public String iniciarTurno(String usuario) {
        String id = "";
        try {
            Conexion.stt.execute("Insert into turno(inicio,usuario)"
                    + "values(NOW(),'" + usuario + "');");
            ResultSet rs = Conexion.stt.executeQuery("SELECT MAX(id) FROM turno");

            while (rs.next()) {
                id = rs.getString(1);
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return id;
        }
    }

    public String[] checarTurno() {
        String[] id = null;
        try {
            String sql = "SELECT * FROM turno WHERE fin = 0 AND id = (SELECT id FROM turno ORDER BY id DESC LIMIT 1)";
            System.out.println(sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            id = new String[4];
            while (rs.next()) {
                id[0] = rs.getString(1);
                id[1] = "";
                id[2] = "";
                id[3] = rs.getString(4);
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return id;
        }
    }

    public boolean cerrarTurno(String usuario, String turno) {

        try {
            Conexion.stt.execute("UPDATE turno SET fin = NOW() WHERE id = '" + turno + "' AND usuario = '" + usuario + "';");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean terminarTurno(String turno) {
        try {

            Conexion.stt.executeUpdate("UPDATE turno SET fin = NOW()"
                    + "WHERE  id = ('" + turno + "');");
            return true;
        } catch (MySQLIntegrityConstraintViolationException cve) {
            javax.swing.JOptionPane.showMessageDialog(null, "El deparamento ya exixte", "Error", 0);
            return false;
        } catch (SQLException e) {

            return false;
        }
    }

    public boolean guardarProveedor(String nombre) {
        try {
            String sql = "Insert into proveedores(nombre) " + "values('" + nombre + "');";
            System.out.println(sql);
            Conexion.stt.executeUpdate(sql);
            return true;
        } catch (MySQLIntegrityConstraintViolationException cve) {
            javax.swing.JOptionPane.showMessageDialog(null, "El deparamento ya exixte", "Error", 0);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean guardarCliente(String nombre, String tel, String dir, String cred) {
        try {

            Conexion.stt.executeUpdate("Insert into cliente(nombre,telefono,direccion,credito)"
                    + "values('" + nombre + "','" + tel + "','" + dir + "','" + cred + "');");
            return true;
        } catch (MySQLIntegrityConstraintViolationException cve) {
            cve.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "El cliente ya exixte", "Error", 0);
            return false;
        } catch (SQLException e) {

            return false;
        }
    }

    public boolean modifArticulo(String codigo, String nombre, String precio, String descripcion, String precioC, String minimo, String p, String c, String seVende, String precioM, String usaI, boolean imp, String impuesto, String id) {
        try {
            if (minimo.equals("")) {
                minimo = "0";
            }
            if (c.equals("")) {
                c = "0";
            }
            if (precioM.equals("")) {
                precioM = "0";
            }
            if (precioC.equals("")) {
                precioC = "0";
            }
            if (precio.equals("")) {
                precio = "0";
            }

            String sql = "UPDATE ARTICULO SET codbar = '" + codigo + "', NOMBRE = \'" + nombre + "\', precio = " + precio + ",descrip = \'" + descripcion + "\',precio_compra = \'"
                    + precioC + "\',min = \'" + minimo + "\',proveedor = \'" + p + "\',inventario = \'" + c + "\',sevende = \'" + seVende + "\',"
                    + "precio_Mayoreo = \'" + precioM + "\',utiliza = \'" + usaI + "\',impuesto = \'" + imp + "\',tipo = \'" + impuesto + "\'"
                    + ",activo = '1' where( id = \'" + id + "\');";
            System.out.println(sql);
            Conexion.stt.executeUpdate(sql);
            String[] prod = getDetailsProducto2(codigo);

            String movSQL = "INSERT INTO movimiento (dia,hora,habia,tipo,cantidad,cajero,articulo) "
                    + "values(CURRENT_DATE(),CURRENT_TIME(),'" + prod[5] + "','Ajustes'," + c + ",'"
                    + Main.interfaz.usuario + "','" + prod[14] + "') ;";
            System.out.println(movSQL);
            Conexion.stt.execute(movSQL);

            return true;
        } catch (MySQLIntegrityConstraintViolationException cve) {
            javax.swing.JOptionPane.showMessageDialog(null, "El codigo ya exixte", "Error", 0);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean guardarTicket(String titulo, String subtitulo, String fin, String rfc, String regimen, String lugar, String img) {
        try {
            String sql = "UPDATE tiket SET titulo = \'" + titulo + "\',eslogan = \'" + subtitulo + "\',"
                    + "final = \'" + fin + "\',lugar = \'" + lugar + "\'"
                    + ",rfc = \'" + rfc + "\',regimen = \'" + regimen + "\',img = \'" + img + "\' where(id =1);";
            System.out.println(sql);
            Conexion.stt.executeUpdate(sql);

            return true;
        } catch (MySQLIntegrityConstraintViolationException cve) {
            javax.swing.JOptionPane.showMessageDialog(null, "El codigo ya exixte", "Error", 0);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modifProveedor(String id, String nombre) {
        try {
            String sql = "UPDATE proveedores SET NOMBRE = \'" + nombre + "\' where( id = \'" + id + "\');";
            System.out.println(sql);
            Conexion.stt.executeUpdate(sql);

            return true;
        } catch (MySQLIntegrityConstraintViolationException cve) {
            javax.swing.JOptionPane.showMessageDialog(null, "El codigo ya exixte", "Error", 0);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modifCliente(String id, String nombre, String tel, String dir, String credito) {
        try {
            String sql = "UPDATE cliente SET nombre = \'" + nombre + "\',telefono = \'" + tel
                    + "\',direccion = \'" + dir + "\',credito = \'" + credito + "\' where( id = \'" + id + "\');";
            System.out.println(sql);
            Conexion.stt.executeUpdate(sql);

            return true;
        } catch (MySQLIntegrityConstraintViolationException cve) {
            javax.swing.JOptionPane.showMessageDialog(null, "El codigo ya exixte", "Error", 0);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean liquidar(String id, double abono) {
        try {
            String sql = "UPDATE cliente SET saldo = saldo - \'" + abono + "' where( id = \'" + id + "\');";
            System.out.println(sql);
            Conexion.stt.executeUpdate(sql);

            return true;
        } catch (MySQLIntegrityConstraintViolationException cve) {
            javax.swing.JOptionPane.showMessageDialog(null, "El codigo ya exixte", "Error", 0);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean agregar(String id, double abono) {
        try {
            String sql = "UPDATE cliente SET saldo = saldo + \'" + abono + "' where( id = \'" + id + "\');";
            System.out.println(sql);
            Conexion.stt.executeUpdate(sql);

            return true;
        } catch (MySQLIntegrityConstraintViolationException cve) {
            javax.swing.JOptionPane.showMessageDialog(null, "El codigo ya exixte", "Error", 0);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean liquidar(String id) {
        try {
            String sql = "UPDATE venta SET credito = '0' where( cliente = \'" + id + "\');";
            System.out.println(sql);
            Conexion.stt.executeUpdate(sql);

            return true;
        } catch (MySQLIntegrityConstraintViolationException cve) {
            javax.swing.JOptionPane.showMessageDialog(null, "El codigo ya exixte", "Error", 0);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modifUsuario(String nombre, String usuario, String pass, String id) {
        try {
            String sql = "UPDATE usuario SET NOMBRE = \'" + nombre + "\',usuario = \'" + usuario + "\',pass = \'" + pass + "\' where( id = \'" + id + "\');";
            System.out.println(sql);
            Conexion.stt.executeUpdate(sql);

            return true;
        } catch (MySQLIntegrityConstraintViolationException cve) {
            javax.swing.JOptionPane.showMessageDialog(null, "El codigo ya exixte", "Error", 0);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean agragrInventario(String id, String nombre, boolean es) {
        try {
            String sub = "";
            if (!es) {
                sub = "inventario +";
                String[] prod = getDetailsProducto2(id);
                String movSQL = "INSERT INTO movimiento (dia,hora,habia,tipo,cantidad,cajero,articulo) "
                        + "values(CURRENT_DATE(),CURRENT_TIME(),'" + prod[5] + "','Entradas'," + prod[5] + "+" + nombre + ",'" + Main.interfaz.usuario + "','" + prod[14] + "') ;";
                Conexion.stt.execute(movSQL);
            } else {
                String[] prod = getDetailsProducto2(id);
                String movSQL = "INSERT INTO movimiento (dia,hora,habia,tipo,cantidad,cajero,articulo) "
                        + "values(CURRENT_DATE(),CURRENT_TIME(),'" + prod[5] + "','Ajustes'," + nombre + ",'" + Main.interfaz.usuario + "','" + prod[14] + "') ;";
                System.out.println(movSQL);
                Conexion.stt.execute(movSQL);
            }

            String sql = "UPDATE articulo SET  inventario = " + sub + " \'" + nombre + "\' where( codbar = \'" + id + "\');";
            System.out.println(sql);
            Conexion.stt.executeUpdate(sql);
            return true;
        } catch (MySQLIntegrityConstraintViolationException cve) {
            javax.swing.JOptionPane.showMessageDialog(null, "El codigo ya exixte", "Error", 0);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean agregarCaja(String movimiento, String detalle, String turno, String tipo) {
        try {
            String sql = "SELECT existente FROM caja order by id desc limit 1";
            String e = "";
            ResultSet rs = Conexion.stt.executeQuery(sql);
            while (rs.next()) {
                e = rs.getString(1);
            }
            System.out.println(movimiento + "  " + e);
            double existente = Double.parseDouble(e) + Double.parseDouble(movimiento);
            sql = "INSERT caja (movimiento,existente,detalle,turno,tipo) VALUES('" + movimiento + "','" + existente + "','" + detalle + "','" + turno + "','" + tipo + "');";
            System.out.println(sql);
            Conexion.stt.executeUpdate(sql);
            return true;
        } catch (MySQLIntegrityConstraintViolationException cve) {
            javax.swing.JOptionPane.showMessageDialog(null, "El codigo ya exixte", "Error", 0);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean iniciarCaja(double movimiento, String turno) {
        try {
            String sql = "INSERT caja (movimiento,existente,detalle,turno,tipo) VALUES('" + movimiento + "','" + movimiento + "','Inicio de turno','" + turno + "','inicio');";
            System.out.println(sql);
            Conexion.stt.executeUpdate(sql);
            return true;
        } catch (MySQLIntegrityConstraintViolationException cve) {
            javax.swing.JOptionPane.showMessageDialog(null, "El codigo ya exixte", "Error", 0);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean agregarUsuario(String nombre, String usuario, String pass, String tipo) {
        try {

            String sql = "INSERT usuario (usuario,pass,nombre,tipo) VALUES('" + usuario + "','" + pass + "','" + nombre + "','" + tipo + "');";
            System.out.println(sql);
            Conexion.stt.executeUpdate(sql);
            return true;
        } catch (MySQLIntegrityConstraintViolationException cve) {
            javax.swing.JOptionPane.showMessageDialog(null, "El codigo ya exixte", "Error", 0);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean agregarCuenta(String monto, String concepto, String cajero, String cliente) {
        try {

            String sql = "INSERT INTO cuenta (monto,concepto,cajero,cliente,turno) "
                    + "VALUES('" + monto + "','" + concepto + "','" + cajero + "','" + cliente + "','" + InicioCaja.turno + "');";
            System.out.println(sql);
            Conexion.stt.executeUpdate(sql);
            if (!concepto.equals("COMPRA A CREDITO")) {
                agregarCaja(monto.substring(1, monto.length()), concepto, InicioCaja.turno, "entrada");
            }
            return true;
        } catch (MySQLIntegrityConstraintViolationException cve) {
            javax.swing.JOptionPane.showMessageDialog(null, "El codigo ya exixte", "Error", 0);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<String> getArticuloC() {
        ArrayList<String> lista = new ArrayList<String>();
        try {

            ResultSet rs = Conexion.stt.executeQuery("SELECT nombre FROM articulo WHERE activo = 1 order by nombre");

            while (rs.next()) {
                lista.add(rs.getObject("nombre").toString());
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public boolean elminarProducto(String name) {
        try {
            String sql = "DELETE FROM articulo WHERE( id = '" + name + "');";
            Conexion.stt.execute(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean elminarProducto2(String name) {
        try {
            String sql = "UPDATE articulo SET activo = 0,precio = 0, descrip = '', precio_compra=0,inventario=0,min=0,activo=0,proveedor=0,sevende='pieza',precio_mayoreo=0  WHERE( codbar = '" + name + "');";
            Conexion.stt.execute(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean elminarCliente(String name) {
        try {
            String sql = "DELETE FROM cliente WHERE( id = '" + name + "');";
            Conexion.stt.execute(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public String[] getDetailsProducto(String codBar) {
        String[] array = null;
        try {
            String sql = "SELECT * FROM ARTICULO WHERE ( id = \'" + codBar + "\');";
            System.out.println(sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            if (rs.next()) {
                array = new String[9];
                array[0] = rs.getString(2);
                array[1] = rs.getString(3);
                array[2] = rs.getString(4);
                array[3] = rs.getString(5);
                array[4] = rs.getString(6);
                array[5] = rs.getString(7);
                array[6] = rs.getString(8);
                array[7] = rs.getString(9);
                array[8] = "" + Double.parseDouble(array[4]) * Double.parseDouble(rs.getString(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public String[] getDetailsUsuario(String id) {
        String[] array = null;
        try {
            String sql = "SELECT * FROM usuario WHERE ( id = \'" + id + "\');";
            System.out.println(sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            if (rs.next()) {
                array = new String[5];
                array[0] = rs.getString(1);
                array[1] = rs.getString(2);
                array[2] = rs.getString(3);
                array[3] = rs.getString(4);
                array[4] = rs.getString(5);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public String[] getDetailsCliente(String id) {
        String[] array = null;
        try {
            String sql = "SELECT * FROM cliente WHERE ( id = \'" + id + "\' );";
            System.out.println(sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            if (rs.next()) {
                array = new String[5];
                array[0] = rs.getString(2);
                array[1] = rs.getString(3);
                array[2] = rs.getString(4);
                array[3] = rs.getString(5);
                array[4] = rs.getString(6);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public ArrayList<String[]> buscarMovimientos(String fecha1, String usuraio, String movimientos) {
        ArrayList<String[]> venta = new ArrayList<String[]>();
        String[] ven;
        String subSQL = "", subSQL2 = "";
        try {
            if (!usuraio.equals("-1")) {
                subSQL = " AND movimiento.cajero = '" + usuraio + "' ";
            }
            if (!movimientos.equals("Todos")) {
                subSQL2 = " AND movimiento.tipo = '" + movimientos + "' ";
            }
            String sql = "SELECT *  FROM movimiento JOIN articulo JOIN proveedores JOIN usuario ON "
                    + "movimiento.articulo = articulo.id AND articulo.proveedor = proveedores.id "
                    + "AND movimiento.cajero = usuario.id  "
                    + "WHERE utiliza = 'TRUE' AND dia ='" + fecha1 + "'" + subSQL + subSQL2 + " ORDER BY hora desc;";
            System.out.println(sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            while (rs.next()) {
                ven = new String[9];
                ven[0] = rs.getString("movimiento.hora");
                ven[1] = rs.getString("articulo.nombre");
                ven[2] = rs.getString("movimiento.habia");
                ven[3] = rs.getString("movimiento.tipo");
                ven[4] = rs.getString("movimiento.cantidad");
                ven[5] = rs.getString("usuario.nombre");
                ven[6] = rs.getString("proveedores.nombre");
                ven[7] = rs.getString("articulo.precio");
                ven[8] = rs.getString("articulo.precio_compra");
                venta.add(ven);
            }
            rs.close();
            return venta;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return venta;
    }

    public String[] getDetailsProducto2(String codBar) {
        String[] array = null;
        try {
            String sql = "SELECT * FROM ARTICULO WHERE ( codbar LIKE \'" + codBar + "\' AND activo = 1);";
            System.out.println(sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            if (rs.next()) {
                array = new String[15];
                array[0] = rs.getString(2);
                array[1] = rs.getString(3);
                array[2] = rs.getString(4);
                array[3] = rs.getString(5);
                array[4] = rs.getString(6);
                array[5] = rs.getString(7);
                array[6] = rs.getString(8);
                array[7] = rs.getString(9);
                array[8] = "" + Double.parseDouble(array[4]) * Double.parseDouble(rs.getString(7));
                array[9] = rs.getString(12);
                array[10] = rs.getString(11);
                array[11] = rs.getString(13);
                array[12] = rs.getString("impuesto");
                array[13] = rs.getString("tipo");
                array[14] = rs.getString("id");

            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public String[] getDetailsProducto3(String codBar) {
        String[] array = null;
        try {
            String sql = "SELECT * FROM ARTICULO WHERE ( codbar LIKE \'" + codBar + "\');";
            System.out.println(sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            if (rs.next()) {
                array = new String[1];
                array[0] = rs.getString(3);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public boolean productoExiste(String codBar) {
        boolean array = false;
        try {
            String sql = "SELECT * FROM ARTICULO WHERE ( codbar LIKE \'" + codBar + "\' AND activo = 1);";
            System.out.println(sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            if (rs.next()) {

                array = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(array);
        return array;
    }

    public String[] getDetailsTiket() {
        String[] array = null;
        try {
            String sql = "SELECT * FROM tiket where id = 1;";
            System.out.println(sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            if (rs.next()) {
                array = new String[7];
                array[0] = rs.getString(2);
                array[1] = rs.getString(3);
                array[2] = rs.getString(4);
                array[3] = rs.getString(5);
                array[4] = rs.getString(6);
                array[5] = rs.getString(7);
                array[6] = rs.getString(8);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public String[] getDetailsTurno() {
        String[] array = null;
        try {
            String sql = "SELECT * FROM turno where id = " + InicioCaja.turno + ";";
            System.out.println(sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            if (rs.next()) {
                array = new String[4];
                array[0] = rs.getString(1);
                array[1] = rs.getString(2);
                array[2] = "0000-00-00 00:00:00";
                array[3] = rs.getString(4);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public String[] getResumenVentasGan(String d1, String d2, String user) {
        String[] array = null;
        try {
            String sql = "";
            if (user.equals("-1")) {
                sql = "SELECT SUM( saldo ) , SUM( ganancia ) \n"
                        + "FROM venta\n"
                        + "WHERE venta.dia >=  '" + d1 + "'\n"
                        + "AND venta.dia <=  '" + d2 + "'";
            } else {
                sql = "SELECT SUM( saldo ) , SUM( ganancia ) \n"
                        + "FROM venta\n"
                        + "WHERE dia >=  '" + d1 + "'\n"
                        + "AND dia <=  '" + d2 + "' "
                        + "AND usuario = '" + user + "'";
            }
            System.out.println(sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            if (rs.next()) {
                array = new String[2];
                array[0] = rs.getString(1);
                array[1] = rs.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public String[] getResumenVentasGanTurno(String user) {
        String[] array = null;
        try {
            String sql = "";
            if (user.equals("-1")) {
                sql = "SELECT SUM( saldo ) , SUM( ganancia ) \n"
                        + "FROM venta\n"
                        + "WHERE venta.usuario =  '" + user + "'\n"
                        + "AND venta.turno =  '" + InicioCaja.turno + "'";
            } else {
                sql = "SELECT SUM( saldo ) , SUM( ganancia ) \n"
                        + "FROM venta\n"
                        + "WHERE venta.usuario =  '" + user + "'\n"
                        + "AND venta.turno =  '" + InicioCaja.turno + "'";
            }
            System.out.println(sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            if (rs.next()) {
                array = new String[2];
                array[0] = rs.getString(1);
                array[1] = rs.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public String[] getResumenVentasTab(String d1, String d2, String user) {
        String[] array = null;
        try {
            String sql = "";
            array = new String[2];
            if (user.equals("-1")) {
                sql = "SELECT SUM(saldo) AS total \n"
                        + "FROM venta\n"
                        + "WHERE dia >=  '" + d1 + "'\n"
                        + "AND dia <=  '" + d2 + "'\n"
                        + "AND tipo =0";
            } else {
                sql = "SELECT SUM(saldo) AS total \n"
                        + "FROM venta\n"
                        + "WHERE dia >=  '" + d1 + "'\n"
                        + "AND dia <=  '" + d2 + "'\n"
                        + "AND tipo =0"
                        + " AND usuario = '" + user + "'";
            }
            System.out.println("1: " + sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            if (rs.next()) {
                array[0] = rs.getString(1);
            }
            String sql2 = "";
            if (user.equals("-1")) {
                sql2 = "SELECT SUM( saldo ) AS total \n"
                        + "FROM venta\n"
                        + "WHERE dia >=  '" + d1 + "'\n"
                        + "AND dia <=  '" + d2 + "'\n"
                        + " AND tipo =1";
            } else {
                sql2 = "SELECT SUM( saldo ) AS total \n"
                        + "FROM venta\n"
                        + "WHERE dia >=  '" + d1 + "'\n"
                        + "AND dia <=  '" + d2 + "'\n"
                        + " AND tipo =1 "
                        + "AND usuario = '" + user + "'";

            }
            System.out.println("2: " + sql2);
            ResultSet rs2 = Conexion.stt.executeQuery(sql2);
            if (rs2.next()) {

                array[1] = rs2.getString(1);

                if (array[1] == null) {
                    array[1] = "0.00";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public String[] getResumenVentasTabTurno(String user) {
        String[] array = null;
        try {
            String sql = "";
            array = new String[2];
            if (user.equals("-1")) {
                sql = "SELECT SUM(saldo) AS total \n"
                        + "FROM venta\n"
                        + "WHERE usuario  = '" + user + "'\n"
                        + "AND turno =  '" + InicioCaja.turno + "'\n"
                        + "AND tipo =0";
            } else {
                sql = "SELECT SUM(saldo) AS total \n"
                        + "FROM venta\n"
                        + "WHERE usuario  = '" + user + "'\n"
                        + "AND turno =  '" + InicioCaja.turno + "'\n"
                        + "AND tipo =0"
                        + " AND usuario = '" + user + "'";
            }
            System.out.println("1: " + sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            if (rs.next()) {
                array[0] = rs.getString(1);
            }
            String sql2 = "";
            if (user.equals("-1")) {
                sql2 = "SELECT SUM( saldo ) AS total \n"
                        + "FROM venta\n"
                        + "WHERE usuario  = '" + user + "'\n"
                        + "AND turno =  '" + InicioCaja.turno + "'\n"
                        + " AND tipo =1";
            } else {
                sql2 = "SELECT SUM( saldo ) AS total \n"
                        + "FROM venta\n"
                        + "WHERE usuario  = '" + user + "'\n"
                        + "AND turno =  '" + InicioCaja.turno + "'\n"
                        + " AND tipo =1 "
                        + "AND usuario = '" + user + "'";

            }
            System.out.println("2: " + sql2);
            ResultSet rs2 = Conexion.stt.executeQuery(sql2);
            if (rs2.next()) {

                array[1] = rs2.getString(1);

                if (array[1] == null) {
                    array[1] = "0.00";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public ArrayList<String[]> getResumenVentasDep(String d1, String d2, String user) {
        ArrayList<String[]> venta = new ArrayList<String[]>();
        ArrayList<String[]> deps = getAllProveedores(true);
        String[] ven;
        try {
            String sql = "";
            for (String string[] : deps) {
                if (user.equals("-1")) {
                    sql = "SELECT SUM(articulo.precio*cantidad)\n"
                            + "FROM venta JOIN articulo_venta JOIN articulo\n"
                            + "ON venta.id=articulo_venta.venta AND articulo_venta.articulo=articulo.codbar\n"
                            + "WHERE venta.dia >=  '" + d1 + "'\n"
                            + "AND venta.dia <=  '" + d2 + "' \n"
                            + "AND articulo.proveedor=" + string[0] + ";";
                } else {
                    sql = "SELECT SUM(articulo.precio*cantidad)\n"
                            + "FROM venta JOIN articulo_venta JOIN articulo\n"
                            + "ON venta.id=articulo_venta.venta AND articulo_venta.articulo=articulo.codbar\n"
                            + "WHERE venta.dia >=  '" + d1 + "'\n"
                            + "AND venta.dia <=  '" + d2 + "' \n"
                            + "AND articulo.proveedor=" + string[0] + ""
                            + " AND venta.usuario = '" + user + "';";
                }
                System.out.println(sql);

                ResultSet rs = Conexion.stt.executeQuery(sql);
                ven = new String[2];
                ven[0] = string[1];
                if (rs.next()) {
                    if (rs.getString(1) != null) {
                        BigDecimal ar = new BigDecimal(rs.getString(1) + "");
                        ar = ar.setScale(2, RoundingMode.HALF_UP);
                        ven[1] = "$" + ar;
                    } else {
                        ven[1] = "$0.00";
                    }
                }
                venta.add(ven);
                rs.close();
            }
            return venta;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return venta;
    }

    public ArrayList<String[]> getResumenVentasDepTurno(String user) {
        ArrayList<String[]> venta = new ArrayList<String[]>();
        ArrayList<String[]> deps = getAllProveedores(true);
        String[] ven;
        try {
            String sql = "";
            for (String string[] : deps) {
                if (user.equals("-1")) {
                    sql = "SELECT SUM(articulo.precio*cantidad)\n"
                            + "FROM venta JOIN articulo_venta JOIN articulo\n"
                            + "ON venta.id=articulo_venta.venta AND articulo_venta.articulo=articulo.codbar\n"
                            + "WHERE venta.turno =  '" + InicioCaja.turno + "'\n"
                            + "AND articulo.proveedor=" + string[0] + ";";
                } else {
                    sql = "SELECT SUM(articulo.precio*cantidad)\n"
                            + "FROM venta JOIN articulo_venta JOIN articulo\n"
                            + "ON venta.id=articulo_venta.venta AND articulo_venta.articulo=articulo.codbar\n"
                            + "WHERE venta.turno =  '" + InicioCaja.turno + "'\n"
                            + "AND articulo.proveedor=" + string[0] + ""
                            + " AND venta.usuario = '" + user + "';";
                }
                System.out.println(sql);

                ResultSet rs = Conexion.stt.executeQuery(sql);
                ven = new String[2];
                ven[0] = string[1];
                if (rs.next()) {
                    if (rs.getString(1) != null) {
                        BigDecimal ar = new BigDecimal(rs.getString(1) + "");
                        ar = ar.setScale(2, RoundingMode.HALF_UP);
                        ven[1] = "$" + ar;
                    } else {
                        ven[1] = "$0.00";
                    }
                }
                venta.add(ven);
                rs.close();
            }
            return venta;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return venta;
    }

    public ArrayList<String[]> getResumenGananciasDep(String d1, String d2, String user) {
        ArrayList<String[]> venta = new ArrayList<String[]>();
        ArrayList<String[]> deps = getAllProveedores(true);
        String[] ven;
        try {
            for (String string[] : deps) {
                String sql = "";
                if (user.equals("-1")) {
                    sql = "SELECT SUM(articulo.precio*cantidad) - SUM(articulo.precio_compra*cantidad) \n"
                            + "FROM venta JOIN articulo_venta JOIN articulo\n"
                            + "ON venta.id=articulo_venta.venta AND articulo_venta.articulo=articulo.codbar\n"
                            + "WHERE venta.dia >=  '" + d1 + "'\n"
                            + "AND venta.dia <=  '" + d2 + "' \n"
                            + "AND articulo.proveedor=" + string[0] + ";";
                } else {
                    sql = "SELECT SUM(articulo.precio*cantidad) - SUM(articulo.precio_compra*cantidad) \n"
                            + "FROM venta JOIN articulo_venta JOIN articulo\n"
                            + "ON venta.id=articulo_venta.venta AND articulo_venta.articulo=articulo.codbar\n"
                            + "WHERE venta.dia >=  '" + d1 + "'\n"
                            + "AND venta.dia <=  '" + d2 + "' \n"
                            + "AND articulo.proveedor=" + string[0] + ""
                            + " AND venta.usuario = '" + user + "';";
                }
                System.out.println(sql);
                ResultSet rs = Conexion.stt.executeQuery(sql);
                ven = new String[2];
                ven[0] = string[1];
                if (rs.next()) {
                    if (rs.getString(1) != null) {
                        BigDecimal ar = new BigDecimal(rs.getString(1) + "");
                        ar = ar.setScale(2, RoundingMode.HALF_UP);

                        ven[1] = "$" + ar;
                    } else {
                        ven[1] = "$0.00";
                    }
                }
                venta.add(ven);
                rs.close();
            }
            return venta;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return venta;
    }

    public ArrayList<String[]> getResumenPagos(String d1, String d2, String user) {
        ArrayList<String[]> venta = new ArrayList<String[]>();

        String[] ven;
        try {
            String sql = "";

            if (user.equals("-1")) {
                sql = "SELECT cuenta.fecha,cliente.nombre,ABS(cuenta.monto)\n"
                        + "FROM cuenta JOIN cliente\n"
                        + "ON cuenta.cliente = cliente.id\n"
                        + "WHERE cuenta.fecha >= '" + d1 + "' AND cuenta.fecha<='" + d2 + "'\n"
                        + "AND monto<0;";
            } else {
                sql = "SELECT cuenta.fecha,cliente.nombre,ABS(cuenta.monto)\n"
                        + "FROM cuenta JOIN cliente\n"
                        + "ON cuenta.cliente = cliente.id\n"
                        + "WHERE cuenta.fecha>= '" + d1 + "' AND cuenta.fecha<='" + d2 + "'\n"
                        + "AND monto<0"
                        + " AND cuenta.cajero = '" + user + "';";
            }
            System.out.println(sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            ven = new String[2];
            while (rs.next()) {
                ven[0] = rs.getString(1).split(" ")[1].substring(0, 5) + " " + rs.getString(2);
                ven[1] = "$" + rs.getString(3) + ".00";
                venta.add(ven);
                ven = new String[2];
            }

            rs.close();
            return venta;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return venta;
    }

    public ArrayList<String[]> getResumenPagosTurno(String user) {
        ArrayList<String[]> venta = new ArrayList<String[]>();

        String[] ven;
        try {
            String sql = "";

            if (user.equals("-1")) {
                sql = "SELECT cuenta.fecha,cliente.nombre,ABS(cuenta.monto)\n"
                        + "FROM cuenta JOIN cliente\n"
                        + "ON cuenta.cliente = cliente.id\n"
                        + "WHERE cuenta.turno = '" + InicioCaja.turno
                        + "AND monto<0;";
            } else {
                sql = "SELECT cuenta.fecha,cliente.nombre,ABS(cuenta.monto)\n"
                        + "FROM cuenta JOIN cliente\n"
                        + "ON cuenta.cliente = cliente.id\n"
                        + "WHERE cuenta.turno = '" + InicioCaja.turno
                        + "' AND monto<0"
                        + " AND cuenta.cajero = '" + user + "';";
            }
            System.out.println(sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            ven = new String[3];
            while (rs.next()) {
                ven[0] = rs.getString(1).split(" ")[1].substring(0, 5) + " " + rs.getString(2);
                ven[1] = "$" + rs.getString(3) + ".00";
                ven[2] = rs.getString(3);
                venta.add(ven);
                ven = new String[3];
            }

            rs.close();
            return venta;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return venta;
    }

    public ArrayList<String[]> getDetailsCompra(String id) {
        ArrayList<String[]> listaT = new ArrayList<String[]>();
        String[] array = null;
        try {
            String sql = "SELECT * FROM venta "
                    + "JOIN articulo_venta "
                    + "JOIN articulo "
                    + "ON venta.id=articulo_venta.venta "
                    + "AND articulo_venta.articulo=articulo.codbar "
                    + "WHERE venta.id =" + id + " ORDER BY articulo_venta.id";
            System.out.println(sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            while (rs.next()) {
                array = new String[12];
                array[0] = rs.getString("articulo_venta.articulo");
                array[1] = rs.getString("articulo_venta.cantidad");
                array[2] = rs.getString("articulo.nombre");
                array[3] = rs.getString("articulo_venta.subtotal");
                array[4] = rs.getString("venta.id");
                array[5] = rs.getString("venta.usuario");
                array[6] = rs.getString("venta.cliente");
                array[7] = rs.getString("venta.dia") + " " + rs.getString("venta.hora");
                array[8] = rs.getString("venta.saldo");
                array[9] = rs.getString("venta.pago");
                array[10] = rs.getString("venta.impuesto");
                array[11] = rs.getString("articulo_venta.precio");

                System.out.println(array[3]);
                listaT.add(array);
                array = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaT;
    }

    public String[] getDetailsProveedor(String id) {
        String[] array = null;
        try {
            String sql = "SELECT * FROM proveedores WHERE ( id = \'" + id + "\');";
            System.out.println(sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            if (rs.next()) {
                array = new String[4];
                array[0] = rs.getString(1);
                array[1] = rs.getString(2);
                array[2] = rs.getString(3);
                array[3] = rs.getString(4);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public String getCodigoBarras(String nombre) {
        String codigo = null;
        try {
            String sql = "SELECT CODBAR FROM ARTICULO WHERE(NOMBRE LIKE \'" + nombre + "\' AND activo = 1);";
            ResultSet rs = Conexion.stt.executeQuery(sql);
            if (rs.next()) {
                codigo = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codigo;
    }

    public boolean insertInventa(String dia, double venta, String hora, String[][] datos, String user, double pago) {
        ResultSet rs;
        String idVenta = "";
        String query = "SELECT MAX(id) FROM venta";
        double ganancia = 0.0;
        double impuesto = 0.0;
        try {
            String msj = "";
            for (String[] strings : datos) {
                String[] prod = getDetailsProducto2(strings[0]);
                String movSQL = "INSERT INTO movimiento (dia,hora,habia,tipo,cantidad,cajero,articulo) "
                        + "values(CURRENT_DATE(),CURRENT_TIME(),'" + prod[5] + "','Salidas'," + prod[5] + "-" + strings[2] + ",'" + Main.interfaz.usuario + "','" + prod[14] + "') ;";
                System.out.println(movSQL);
                Conexion.stt.execute(movSQL);

                Conexion.stt.execute("UPDATE articulo SET inventario = inventario - " + strings[2]
                        + " WHERE codbar = '" + strings[0] + "' ;");
                String[] producto = getDetailsProducto2(strings[0]);
                boolean imp = Boolean.parseBoolean(producto[12]);
                if (imp) {
                    if (Double.parseDouble(producto[13]) == 1) {
                        impuesto += (Double.parseDouble(producto[2]) - (Double.parseDouble(producto[2]) / 1.16));
                    } else {
                        impuesto += (Double.parseDouble(producto[2]) - (Double.parseDouble(producto[2]) / 1.08));
                    }
                }
                rs = Conexion.stt.executeQuery("SELECT inventario,min,utiliza FROM articulo WHERE codbar = '" + strings[0] + "'");
                while (rs.next()) {
                    if (Double.parseDouble(rs.getString(1)) <= Double.parseDouble(rs.getString(2)) && Boolean.parseBoolean(rs.getString(3))) {
                        msj += "El producto " + strings[1] + " se esta terminando\n";
                    }
                }
                rs = Conexion.stt.executeQuery("SELECT (precio-precio_compra)*" + strings[2] + " FROM articulo WHERE codbar = '" + strings[0] + "'");
                while (rs.next()) {
                    ganancia += Double.parseDouble(rs.getString(1));
                }
            }
            if (msj.length() > 0) {
                javax.swing.JOptionPane.showMessageDialog(null, msj, "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            Conexion.stt.execute("Insert into venta(dia,saldo,hora,ganancia,usuario,impuesto,pago,turno)"
                    + "values('" + dia + "','" + venta + "','" + hora + "','" + ganancia + "','" + user + "','" + impuesto + "','" + pago + "','" + InicioCaja.turno + "');");
            rs = Conexion.stt.executeQuery(query);
            while (rs.next()) {
                idVenta = rs.getString(1);
            }

            for (String[] strings : datos) {
                String sqlDatos = "Insert into articulo_venta(articulo,venta,cantidad,subtotal,precio)"
                        + "values('" + strings[0] + "','" + idVenta + "','" + strings[2] + "','" + strings[4] + "','" + strings[3] + "');";
                Conexion.stt.execute(sqlDatos);
            }

            agregarCaja("" + venta, "Venta de mostrador", InicioCaja.turno, "ventaefectivo");
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }

    }

    public boolean insertApartado(double venta, String[][] datos, double pago, String fecha, int cliente) {
        ResultSet rs;
        String idVenta = "";
        String query = "SELECT MAX(id) FROM venta";
        double ganancia = 0.0;
        double impuesto = 0.0;
        try {
            String msj = "";
            for (String[] strings : datos) {
                String[] prod = getDetailsProducto2(strings[0]);
                String movSQL = "INSERT INTO movimiento (dia,hora,habia,tipo,cantidad,cajero,articulo) "
                        + "values(CURRENT_DATE(),CURRENT_TIME(),'" + prod[5] + "','Apartados'," + prod[5] + ",'" + Main.interfaz.usuario + "','" + prod[14] + "') ;";
                System.out.println(movSQL);
                Conexion.stt.execute(movSQL);

            }
            Conexion.stt.execute("Insert into venta(dia,saldo,hora,ganancia,usuario,impuesto,pago,turno,fechaFin,cliente)"
                    + "values(CURRENT_DATE(),'" + venta + "',CURRENT_TIME(),'" + ganancia + "','" + Main.interfaz.usuario + "','" + impuesto + "','" + pago + "','" + InicioCaja.turno + "','" + fecha + "','" + cliente + "');");
            rs = Conexion.stt.executeQuery(query);
            while (rs.next()) {
                idVenta = rs.getString(1);
            }
            for (String[] strings : datos) {
                String sqlDatos = "Insert into articulo_venta(articulo,venta,cantidad,subtotal,precio)"
                        + "values('" + strings[0] + "','" + idVenta + "','" + strings[2] + "','" + strings[4] + "','" + strings[3] + "');";
                Conexion.stt.execute(sqlDatos);
            }

            agregarCaja("" + pago, "Sistema de apartado", InicioCaja.turno, "entrada");
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }

    }

    public boolean liquidarApartado(int venta, String[][] datos, String pago) {
        ResultSet rs;
        String idVenta = "";
        String query = "SELECT MAX(id) FROM venta";
        double ganancia = 0.0;
        try {
            String msj = "";
            int i = 0;
            for (String[] strings : datos) {
                String[] prod = getDetailsProducto2(strings[0]);
                String movSQL = "INSERT INTO movimiento (dia,hora,habia,tipo,cantidad,cajero,articulo) "
                        + "values(CURRENT_DATE(),CURRENT_TIME(),'" + prod[5] + "','Salida'," + prod[5] + "-" + datos[i][2] + ",'" + Main.interfaz.usuario + "','" + prod[14] + "') ;";
                System.out.println(movSQL);
                Conexion.stt.execute(movSQL);
                rs = Conexion.stt.executeQuery("SELECT (precio-precio_compra)*" + strings[2] + " FROM articulo WHERE codbar = '" + strings[0] + "'");
                while (rs.next()) {
                    ganancia += Double.parseDouble(rs.getString(1));
                }
                i++;
            }
            String sql = "UPDATE venta SET dia = CURRENT_DATE(), hora = CURRENT_TIME(),ganancia = " + ganancia + ",pago = saldo,fechaFin=NULL, "
                    + "usuario = " + Main.interfaz.usuario + ", turno = " + InicioCaja.turno
                    + " WHERE id = " + venta + ";";
            System.out.println(sql);
            Conexion.stt.execute(sql);
            rs = Conexion.stt.executeQuery(query);
            while (rs.next()) {
                idVenta = rs.getString(1);
            }
            agregarCaja("" + pago, "Sistema de apartado(Liquidacion)", InicioCaja.turno, "entrada");
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }

    }

    public double getCantidad(String producto, String venta) {
        String query = "SELECT cantidad FROM  articulo_venta WHERE articulo = '" + producto + "' AND venta = '" + venta + "';";
        double result = 0.0;
        ResultSet rs;
        try {
            System.out.println(query);
            rs = Conexion.stt.executeQuery(query);
            while (rs.next()) {
                result = Double.parseDouble(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean devolverProducto(String producto, String venta, double cantidad, boolean eliminar) {
        ResultSet rs;
        double ganancia = 0, saldo = 0, monto = 0;
        monto = Double.parseDouble(getDetailsProducto2(producto)[2]) * cantidad;
        try {
            if (eliminar) {
                Conexion.stt.execute("DELETE FROM articulo_venta WHERE venta = '" + venta + "' AND articulo = '" + producto + "';");
            } else {
                Conexion.stt.execute("UPDATE  articulo_venta SET cantidad = cantidad -'" + cantidad + "' WHERE venta = '" + venta + "' AND articulo = '" + producto + "';");
            }
            rs = Conexion.stt.executeQuery("SELECT (cantidad*precio)-(cantidad*precio_compra) FROM articulo_venta JOIN articulo ON articulo_venta.articulo =articulo.codbar WHERE venta= " + venta + ";");
            while (rs.next()) {
                ganancia += Double.parseDouble(rs.getString(1));
            }
            rs = Conexion.stt.executeQuery("SELECT cantidad*precio FROM articulo_venta JOIN articulo ON articulo_venta.articulo =articulo.codbar WHERE venta= " + venta + ";");
            while (rs.next()) {
                saldo += Double.parseDouble(rs.getString(1));
            }
            Conexion.stt.execute("UPDATE venta SET ganancia = '" + ganancia + "',saldo = '" + saldo + "' WHERE  id = '" + venta + "';");
            String[] prod = getDetailsProducto2(producto);
            String movSQL = "INSERT INTO movimiento (dia,hora,habia,tipo,cantidad,cajero,articulo) "
                    + "values(CURRENT_DATE(),CURRENT_TIME(),'" + prod[5] + "','Entradas'," + prod[5] + "+" + cantidad + ",'" + Main.interfaz.usuario + "','" + prod[14] + "') ;";
            Conexion.stt.execute(movSQL);
            Conexion.stt.execute("UPDATE  articulo SET inventario = inventario +'" + cantidad + "' WHERE  codbar = '" + producto + "';");

            agregarCaja("-" + monto, "Devolucion de articulo", InicioCaja.turno, "devolucion");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean agregarProducto(String producto, String venta, double cantidad) {
        ResultSet rs;
        double ganancia = 0, saldo = 0;
        try {
            Conexion.stt.execute("INSERT INTO articulo_venta (venta,articulo) VALUES ('" + venta + "','" + producto + "');");

            rs = Conexion.stt.executeQuery("SELECT (cantidad*precio)-(cantidad*precio_compra) "
                    + "FROM articulo_venta JOIN articulo ON articulo_venta.articulo =articulo.codbar WHERE venta= " + venta + ";");
            while (rs.next()) {
                ganancia += Double.parseDouble(rs.getString(1));
            }
            rs = Conexion.stt.executeQuery("SELECT cantidad*precio FROM articulo_venta JOIN articulo ON articulo_venta.articulo =articulo.codbar WHERE venta= " + venta + ";");
            while (rs.next()) {
                saldo += Double.parseDouble(rs.getString(1));
            }
            Conexion.stt.execute("UPDATE venta SET ganancia = '" + ganancia + "',saldo = '" + saldo + "' WHERE  id = '" + venta + "';");
            Conexion.stt.execute("UPDATE  articulo SET inventario = inventario -'" + cantidad + "' WHERE  codbar = '" + producto + "';");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean insertInventaCredito(String dia, double venta, String hora, String[][] datos, String user, String cliente, double pago, double abono) {
        ResultSet rs;
        String idVenta = "";
        String query = "SELECT MAX(id) FROM venta";
        double ganancia = 0.0;
        double impuesto = 0.0;
        try {
            String[] clienteD = getDetailsCliente(cliente);
            if (Double.parseDouble(clienteD[4]) + venta <= Double.parseDouble(clienteD[3]) || Double.parseDouble(clienteD[3]) == 0.0) {
                String msj = "";
                for (String[] strings : datos) {
                    String[] prod = getDetailsProducto2(strings[0]);
                    String movSQL = "INSERT INTO movimiento (dia,hora,habia,tipo,cantidad,cajero,articulo) "
                            + "values(CURRENT_DATE(),CURRENT_TIME(),'" + prod[5] + "','Salidas'," + prod[5] + "-" + strings[2] + ",'" + Main.interfaz.usuario + "','" + prod[14] + "') ;";
                    System.out.println(movSQL);
                    Conexion.stt.execute(movSQL);
                    System.out.println("SELECT (precio-precio_compra)*" + strings[2] + " FROM articulo WHERE codbar = '" + strings[0] + "'");
                    String[] producto = getDetailsProducto2(strings[0]);
                    boolean imp = Boolean.parseBoolean(producto[12]);

                    if (imp) {
                        if (Double.parseDouble(producto[13]) == 1) {
                            impuesto += (Double.parseDouble(producto[2]) - (Double.parseDouble(producto[2]) / 1.16));
                        } else {
                            impuesto += (Double.parseDouble(producto[2]) - (Double.parseDouble(producto[2]) / 1.08));
                        }
                    }
                    Conexion.stt.execute("UPDATE articulo SET inventario = inventario - " + strings[2]
                            + " WHERE codbar = '" + strings[0] + "' ;");
                    rs = Conexion.stt.executeQuery("SELECT inventario,min,utiliza FROM articulo WHERE codbar = '" + strings[0] + "'");
                    while (rs.next()) {
                        if (Double.parseDouble(rs.getString(1)) <= Double.parseDouble(rs.getString(2)) && Boolean.parseBoolean(rs.getString(3))) {
                            msj += "El producto " + strings[1] + " se esta terminando\n";
                        }
                    }
                    rs = Conexion.stt.executeQuery("SELECT (precio-precio_compra)*" + strings[2] + " FROM articulo WHERE codbar = '" + strings[0] + "'");
                    while (rs.next()) {
                        ganancia += Double.parseDouble(rs.getString(1));
                    }
                }
                if (msj.length() > 0) {
                    javax.swing.JOptionPane.showMessageDialog(null, msj, "S.Te.Ve", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
                Conexion.stt.execute("Insert into venta(dia,saldo,hora,ganancia,usuario,credito,cliente,impuesto,pago,tipo,turno)"
                        + "values('" + dia + "','" + venta + "','" + hora + "','" + ganancia + "','" + user + "','1','" + cliente + "','" + impuesto + "','" + pago + "','1','" + InicioCaja.turno + "');");
                rs = Conexion.stt.executeQuery(query);
                while (rs.next()) {
                    idVenta = rs.getString(1);
                }
                for (String[] strings : datos) {
                    String sqlDatos = "Insert into articulo_venta(articulo,venta,cantidad,subtotal,precio)"
                            + "values('" + strings[0] + "','" + idVenta + "','" + strings[2] + "','" + strings[4] + "','" + strings[3] + "');";
                    System.out.println(sqlDatos);
                    Conexion.stt.execute(sqlDatos);
                }
                Conexion.stt.execute("UPDATE cliente SET saldo = saldo + '" + abono + "' WHERE id = '" + cliente + "' ;");
                //agregarCaja("" + venta, "Venta de mostrador", InicioCaja.turno, "ventaefectivo");
                return true;
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "No es posible completar la venta \nEl cliente (" + clienteD[0] + ") superaria su limite de credio ($" + clienteD[3] + "0)", "ERROR", 0);
                return false;

            }
        } catch (Exception ex) {
            Logger.getLogger(Procedimientos.class
                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();

            return false;
        }

    }

    public boolean insertCompra(String dia, double venta, String hora, String[][] datos) {
        ResultSet rs;
        String idVenta = "";
        String query = "SELECT MAX(id) FROM compra";

        try {
            for (String[] strings : datos) {
                Conexion.stt.execute("UPDATE articulo SET inventario = inventario + " + strings[2]
                        + " WHERE codbar = '" + strings[0] + "' ;");
            }
            Conexion.stt.execute("Insert into compra(dia,saldo,hora)"
                    + "values('" + dia + "','" + venta + "','" + hora + "');");
            rs = Conexion.stt.executeQuery(query);
            while (rs.next()) {
                idVenta = rs.getString(1);
            }

            for (String[] strings : datos) {
                String sqlDatos = "Insert into articulo_compra(articulo,compra,cantidad,subtotal)"
                        + "values('" + strings[0] + "','" + idVenta + "','" + strings[2] + "','" + strings[4] + "');";
                Conexion.stt.execute(sqlDatos);
            }
            venta = -venta;
            agregarCaja("" + venta, "Compra de articulos", InicioCaja.turno, "compra");

            return true;

        } catch (Exception ex) {
            Logger.getLogger(Procedimientos.class
                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();

            return false;
        }

    }

    public ArrayList<String[]> getAllProductos(String p) {

        String query = "SELECT * FROM ARTICULO WHERE activo = 1 AND proveedor = '" + p + "' Order by nombre ";
        if (Double.parseDouble(p) == 0) {
            query = "SELECT * FROM ARTICULO WHERE activo = 1 Order by nombre ";
        }
        ResultSet rs;

        ArrayList<String[]> listaT = new ArrayList<String[]>();
        String[] productoDetails = null;
        try {
            rs = Conexion.stt.executeQuery(query);
            while (rs.next()) {
                productoDetails = new String[8];
                productoDetails[0] = rs.getString("codbar");
                productoDetails[1] = rs.getString("nombre");
                productoDetails[2] = rs.getString("precio_compra");
                productoDetails[3] = rs.getString("precio");
                productoDetails[4] = rs.getString("precio_mayoreo");
                productoDetails[5] = rs.getString("inventario");
                productoDetails[6] = rs.getString("min");
                productoDetails[7] = rs.getString("utiliza");

                listaT.add(productoDetails);
                productoDetails = null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class
                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return listaT;
    }

    public ArrayList<String[]> getAllClientes() {
        String query = "SELECT * FROM cliente Where id > 1 Order by nombre";
        ResultSet rs;
        ArrayList<String[]> listaT = new ArrayList<String[]>();
        String[] productoDetails = null;
        try {
            rs = Conexion.stt.executeQuery(query);
            while (rs.next()) {
                productoDetails = new String[3];
                productoDetails[0] = rs.getString(1);
                productoDetails[1] = rs.getString(2);
                productoDetails[2] = rs.getString(3);
                listaT.add(productoDetails);
                productoDetails = null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class
                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return listaT;
    }

    public ArrayList<String[]> getDatosPolares(String i, String f) {
        ArrayList<String[]> categorias = getAllProveedores(true);
        ResultSet rs;
        ArrayList<String[]> listaT = new ArrayList<String[]>();
        String[] productoDetails = null;
        for (int j = 0; j < categorias.size(); j++) {

            String query = "SELECT SUM(articulo_venta.subtotal) as suma FROM venta JOIN articulo_venta JOIN articulo "
                    + "ON venta.id = articulo_venta.venta AND articulo_venta.articulo = articulo.codbar "
                    + "WHERE venta.dia >= '" + i + "' AND venta.dia <= '" + f + "' AND articulo.proveedor=" + categorias.get(j)[0];
            //System.out.println(query);
            try {
                rs = Conexion.stt.executeQuery(query);
                while (rs.next()) {
                    productoDetails = new String[2];

                    productoDetails[0] = categorias.get(j)[1];
                    productoDetails[1] = rs.getString("suma");
                    listaT.add(productoDetails);
                    productoDetails = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Procedimientos.class
                        .getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            rs = null;
        }

        return listaT;
    }

    public ArrayList<String[]> getDatosLineales(int y, int m) {
        ArrayList<String[]> categorias = getAllProveedores(true);
        ResultSet rs;
        ArrayList<String[]> listaT = new ArrayList<String[]>();
        String[] productoDetails = null;
        for (int j = 0; j < categorias.size(); j++) {

            String query = "SELECT SUM(articulo_venta.subtotal) as suma "
                    + "FROM venta JOIN articulo_venta JOIN articulo "
                    + "ON venta.id = articulo_venta.venta AND articulo_venta.articulo = articulo.codbar "
                    + "WHERE MONTH(venta.dia) = " + m + " AND YEAR(venta.dia) =" + y + " AND articulo.proveedor=" + categorias.get(j)[0];
            //        System.out.println(query);
            try {
                rs = Conexion.stt.executeQuery(query);
                while (rs.next()) {
                    productoDetails = new String[2];

                    productoDetails[0] = categorias.get(j)[1];
                    productoDetails[1] = rs.getString("suma");
                    listaT.add(productoDetails);
                    productoDetails = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Procedimientos.class
                        .getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            rs = null;
        }

        return listaT;
    }

    public ArrayList<String[]> getAllProveedores(boolean completo) {
        String query = "";
        if (completo) {
            query = "SELECT * FROM proveedores";
        } else {
            query = "SELECT * FROM proveedores WHERE id > 1";
        }
        ResultSet rs;
        ArrayList<String[]> listaT = new ArrayList<String[]>();
        String[] productoDetails = null;
        try {
            rs = Conexion.stt.executeQuery(query);
            while (rs.next()) {
                productoDetails = new String[4];
                productoDetails[0] = rs.getString(1);
                productoDetails[1] = rs.getString(2);
                productoDetails[2] = rs.getString(3);
                productoDetails[3] = rs.getString(4);
                listaT.add(productoDetails);
                productoDetails = null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class
                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return listaT;
    }

    public ArrayList<String[]> getAllUsuarios() {
        String query = "SELECT * FROM usuario";
        ResultSet rs;
        ArrayList<String[]> listaT = new ArrayList<String[]>();
        String[] productoDetails = null;
        try {
            rs = Conexion.stt.executeQuery(query);
            while (rs.next()) {
                productoDetails = new String[4];
                productoDetails[0] = rs.getString(1);
                productoDetails[1] = rs.getString(2);
                productoDetails[2] = rs.getString(3);
                productoDetails[3] = rs.getString(4);
                listaT.add(productoDetails);
                productoDetails = null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class
                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return listaT;
    }

    public boolean elminarProveedor(String id) {
        try {
            String sql = "DELETE FROM proveedores WHERE( id = '" + id + "');";
            Conexion.stt.execute(sql);
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class
                    .getName()).log(Level.SEVERE, null, ex);

            return false;
        }
    }

    public boolean elminarUsuario(String id) {
        try {
            String sql = "DELETE FROM usuario WHERE( id = '" + id + "');";
            Conexion.stt.execute(sql);
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class
                    .getName()).log(Level.SEVERE, null, ex);

            return false;
        }
    }

    public ArrayList<String[]> buscarProveedores(String filtro) {
        String query = "SELECT * FROM proveedores WHERE id = '" + filtro + "' OR nombre LIKE '%" + filtro + "%' OR apellidos LIKE '%" + filtro + "%' OR compania LIKE '%" + filtro + "%'";
        ResultSet rs;
        ArrayList<String[]> listaT = new ArrayList<String[]>();
        String[] productoDetails = null;
        try {
            System.out.println(query);
            rs = Conexion.stt.executeQuery(query);
            while (rs.next()) {
                productoDetails = new String[4];
                productoDetails[0] = rs.getString(1);
                productoDetails[1] = rs.getString(2);
                productoDetails[2] = rs.getString(3);
                productoDetails[3] = rs.getString(4);
                listaT.add(productoDetails);
                productoDetails = null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class
                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return listaT;
    }

    public ArrayList<String[]> buscarClientes(String filtro) {
        String query = "SELECT * FROM cliente WHERE (id = '" + filtro + "' OR nombre LIKE '" + filtro + "%') AND id > 1";
        ResultSet rs;
        ArrayList<String[]> listaT = new ArrayList<String[]>();
        String[] productoDetails = null;
        try {
            System.out.println(query);
            rs = Conexion.stt.executeQuery(query);
            while (rs.next()) {
                productoDetails = new String[3];
                productoDetails[0] = rs.getString(1);
                productoDetails[1] = rs.getString(2);
                productoDetails[2] = rs.getString(3);
                listaT.add(productoDetails);
                productoDetails = null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class
                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return listaT;
    }

    public void borrarRepetidos() {
        String query = "SELECT * FROM articulo";
        ResultSet rs;
        ArrayList<String[]> listaT = new ArrayList<String[]>();
        String[] productoDetails = null;
        try {
            System.out.println(query);
            rs = Conexion.stt.executeQuery(query);
            while (rs.next()) {
                productoDetails = new String[4];
                productoDetails[0] = rs.getString(1);
                productoDetails[1] = rs.getString(2);
                productoDetails[2] = rs.getString(3);
                productoDetails[3] = rs.getString(4);
                listaT.add(productoDetails);
                productoDetails = null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class
                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        String[] prod = null;
        String[] prod2 = null;
        for (int i = listaT.size() - 1; i >= 0; i--) {
            prod = listaT.get(i);
            for (int j = i - 1; j >= 0; j--) {
                prod2 = listaT.get(j);
                if (prod[1].equals(prod2[1])) {
                    try {
                        System.out.println(prod[1] + "  " + prod2[1]);
                        String sql = "DELETE from articulo where( id = \'" + prod2[0] + "\');";
                        System.out.println(sql);
                        Conexion.stt.executeUpdate(sql);

                    } catch (SQLException ex) {
                        Logger.getLogger(Procedimientos.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

    }

    public void borrarMenores() {
        String query = "SELECT * FROM articulo";
        ResultSet rs;
        ArrayList<String[]> listaT = new ArrayList<String[]>();
        String[] productoDetails = null;
        try {
            System.out.println(query);
            rs = Conexion.stt.executeQuery(query);
            while (rs.next()) {
                productoDetails = new String[4];
                productoDetails[0] = rs.getString(1);
                productoDetails[1] = rs.getString(2);
                productoDetails[2] = rs.getString(3);
                productoDetails[3] = rs.getString(4);
                listaT.add(productoDetails);
                productoDetails = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class
                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        for (String prod[] : listaT) {

            try {
                if (prod[1].length() <= 7) {
                    System.out.println(prod[1]);
                    String sql = "DELETE from articulo where( id = \'" + prod[0] + "\');";
                    System.out.println(sql);
                    Conexion.stt.executeUpdate(sql);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Procedimientos.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public ArrayList<String[]> buscarUsuarios(String filtro) {
        String query = "SELECT * FROM usuario WHERE id = '" + filtro + "' OR nombre LIKE '%" + filtro + "%' OR pass LIKE '%" + filtro + "%' OR usuario LIKE '%" + filtro + "%'";
        ResultSet rs;
        ArrayList<String[]> listaT = new ArrayList<String[]>();
        String[] productoDetails = null;
        try {
            System.out.println(query);
            rs = Conexion.stt.executeQuery(query);
            while (rs.next()) {
                productoDetails = new String[4];
                productoDetails[0] = rs.getString(1);
                productoDetails[1] = rs.getString(2);
                productoDetails[2] = rs.getString(3);
                productoDetails[3] = rs.getString(4);
                listaT.add(productoDetails);
                productoDetails = null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class
                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return listaT;
    }

    public ArrayList<String[]> buscarProductos(String filtro) {
        String query = "SELECT * FROM ARTICULO WHERE ( codbar = '" + filtro + "' OR articulo.nombre LIKE '" + filtro + "%') AND activo = 1 Order by articulo.nombre";
        ResultSet rs;
        ArrayList<String[]> listaT = new ArrayList<String[]>();
        String[] productoDetails = null;
        System.out.println(query);
        try {
            rs = Conexion.stt.executeQuery(query);
            while (rs.next()) {
                productoDetails = new String[6];
                productoDetails[0] = rs.getString(2);
                productoDetails[1] = rs.getString(3);
                productoDetails[2] = rs.getString(4);
                productoDetails[3] = rs.getString(7);
                productoDetails[4] = rs.getString(8);

                productoDetails[5] = "" + (Double.parseDouble(rs.getString(6)) * Double.parseDouble(rs.getString(7)));
                listaT.add(productoDetails);
                productoDetails = null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class
                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return listaT;
    }

    public ArrayList<String[]> buscaApartados() {
        String query = "SELECT * FROM venta JOIN cliente ON venta.cliente = cliente.id WHERE fechaFin IS NOT NULL AND NOW() < fechaFin ORDER BY fechaFin ";
        ResultSet rs;
        ArrayList<String[]> listaT = new ArrayList<String[]>();
        String[] productoDetails = null;
        System.out.println(query);
        try {
            rs = Conexion.stt.executeQuery(query);
            while (rs.next()) {
                productoDetails = new String[4];
                productoDetails[0] = rs.getString(1);
                productoDetails[1] = rs.getString(2) + " / " + rs.getString(4);
                productoDetails[2] = rs.getString(14);
                productoDetails[3] = rs.getString(16);

                listaT.add(productoDetails);
                productoDetails = null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class
                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return listaT;
    }

    public ArrayList<String[]> detallesApartado(int id) {
        String query = "SELECT articulo.codbar,articulo.nombre,articulo_venta.cantidad,articulo.precio,articulo_venta.cantidad * articulo.precio,"
                + " venta.saldo, venta.pago FROM venta join articulo_venta join articulo on venta.id = articulo_venta.venta AND articulo_venta.articulo = articulo.codbar WHERE venta.id = " + id;
        ResultSet rs;
        ArrayList<String[]> listaT = new ArrayList<String[]>();
        String[] productoDetails = null;
        System.out.println(query);
        try {
            rs = Conexion.stt.executeQuery(query);
            while (rs.next()) {
                productoDetails = new String[7];
                productoDetails[0] = rs.getString(1);
                productoDetails[1] = rs.getString(2);
                productoDetails[2] = rs.getString(3);
                productoDetails[3] = rs.getString(4);
                productoDetails[4] = rs.getString(5);
                productoDetails[5] = rs.getString(6);
                productoDetails[6] = rs.getString(7);

                listaT.add(productoDetails);
                productoDetails = null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class
                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return listaT;
    }

    public ArrayList<String[]> buscaClientes(String filtro) {
        String query = "SELECT * FROM cliente WHERE ( nombre LIKE '%" + filtro + "%') Order by nombre";
        ResultSet rs;
        ArrayList<String[]> listaT = new ArrayList<String[]>();
        String[] productoDetails = null;
        System.out.println(query);
        try {
            rs = Conexion.stt.executeQuery(query);
            while (rs.next()) {
                productoDetails = new String[3];
                productoDetails[0] = rs.getString(1);
                productoDetails[1] = rs.getString(2);
                productoDetails[2] = rs.getString(3);
                listaT.add(productoDetails);
                productoDetails = null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class
                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return listaT;
    }

    public ArrayList<String[]> bajos() {
        String query = "SELECT * FROM ARTICULO JOIN proveedores ON articulo.proveedor = proveedores.id"
                + " WHERE inventario<=min AND activo = 1 AND utiliza ='true' Order by articulo.nombre";
        ResultSet rs;
        ArrayList<String[]> listaT = new ArrayList<String[]>();
        String[] productoDetails = null;
        System.out.println(query);
        try {
            rs = Conexion.stt.executeQuery(query);
            while (rs.next()) {
                productoDetails = new String[6];
                productoDetails[0] = rs.getString(1);
                productoDetails[1] = rs.getString(2);
                productoDetails[2] = rs.getString(3);
                productoDetails[3] = rs.getString(7);
                productoDetails[4] = rs.getString(8);
                productoDetails[5] = rs.getString(15);

                listaT.add(productoDetails);
                productoDetails = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class
                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return listaT;
    }

    public ArrayList<String[]> bajos(String prov) {
        String subSQL = "";
        if (Double.parseDouble(prov) != 0) {
            subSQL = "AND proveedor = '" + prov + "'";
        }
        String query = "SELECT * FROM ARTICULO JOIN proveedores ON articulo.proveedor = proveedores.id"
                + " WHERE inventario<=min AND activo = 1 " + subSQL + " AND utiliza ='TRUE' Order by articulo.nombre";
        ResultSet rs;
        ArrayList<String[]> listaT = new ArrayList<String[]>();
        String[] productoDetails = null;
        System.out.println(query);
        try {
            rs = Conexion.stt.executeQuery(query);
            while (rs.next()) {
                productoDetails = new String[6];
                productoDetails[0] = rs.getString("articulo.codbar");
                productoDetails[1] = rs.getString("articulo.nombre");
                productoDetails[2] = rs.getString("articulo.precio");
                productoDetails[3] = rs.getString("articulo.inventario");
                productoDetails[4] = rs.getString("articulo.min");
                productoDetails[5] = rs.getString("proveedores.nombre");
                listaT.add(productoDetails);
                productoDetails = null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class
                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return listaT;
    }

    public ArrayList<String[]> getventa() {
        ArrayList<String[]> venta = new ArrayList<String[]>();
        String[] ven;
        try {
            ResultSet rs = Conexion.stt.executeQuery("SELECT * FROM venta;");
            while (rs.next()) {
                ven = new String[3];
                ven[0] = rs.getString(1);
                ven[1] = rs.getString(2);
                ven[2] = rs.getString(3);
                venta.add(ven);
            }
            rs.close();
            return venta;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return venta;
    }

    public ArrayList<String[]> buscarVenta(String fecha1, String fecha2, String proveedor) {
        ArrayList<String[]> venta = new ArrayList<String[]>();
        String[] ven;
        String subSQL = "", subSQL2 = "";
        if (!proveedor.equals("-1")) {
            subSQL = "FROM venta JOIN articulo_venta JOIN articulo JOIN proveedores ON venta.id = articulo_venta.venta AND articulo_venta.articulo = articulo.codbar AND articulo.activo = 1 AND venta.usuario = " + proveedor;
        } else {

            subSQL2 = " FROM venta JOIN articulo_venta  ON venta.id = articulo_venta.venta";
        }
        try {
            String sql = "SELECT DISTINCT venta.id,venta.dia,venta.hora,venta.saldo,venta.ganancia,venta.hora "
                    + subSQL + subSQL2 + " WHERE dia >='" + fecha1 + "' AND dia <= '" + fecha2 + "' AND fechaFin IS NULL  ORDER BY venta.id desc;";
            System.out.println(sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            while (rs.next()) {
                ven = new String[5];
                ven[0] = rs.getString(1);
                ven[1] = rs.getString(2) + " " + rs.getString(3);
                ven[2] = rs.getString(4);
                ven[3] = rs.getString(5);
                ven[4] = rs.getString(6);
                venta.add(ven);
            }
            rs.close();
            return venta;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return venta;
    }

    /*
     public boolean devolver(String prod, Double cantidad,boolean eliminar) {
               
        
     }*/
    public ArrayList<String[]> acomodar(ArrayList<String[]> venta) {
        ArrayList<String[]> resultado = new ArrayList<String[]>();
        String cod = "", aux = "";
        ArrayList<String> lista = new ArrayList<String>();
        double cantidad = 0;
        double precio = 0;
        boolean esta = false, estaLista = false;
        for (int i = 0; i < venta.size(); i++) {
            cod = venta.get(i)[0];
            for (String string : lista) {
                if (string.equals(cod)) {
                    estaLista = true;
                    break;
                }
                estaLista = false;
            }
            for (int j = 0; j < venta.size(); j++) {
                if (cod.equals(venta.get(j)[0]) && !estaLista) {
                    System.out.println("Cod: " + cod + "Compar: " + venta.get(j)[0]);
                    lista.add(cod);
                    esta = true;
                    cantidad += Double.parseDouble(venta.get(j)[2]);
                    precio += Double.parseDouble(venta.get(j)[3]);
                }
            }
            System.out.println("");

            if (esta) {
                resultado.add(new String[]{cod, venta.get(i)[1], "" + cantidad, "" + (precio / cantidad), venta.get(i)[4]});
                cantidad = 0;
                precio = 0;
            }
            esta = false;
        }
        for (String[] strings : resultado) {
            System.out.println(strings[0] + "  " + strings[1] + "  " + strings[2] + "  " + strings[3] + "  " + strings[4]);
        }
        return resultado;
    }

    public ArrayList<String[]> buscarVenta(String fecha1, String fecha2) {
        ArrayList<String[]> venta = new ArrayList<String[]>();
        String[] ven;
        try {
            String sql = "SELECT articulo.codbar,articulo.nombre,articulo_venta.cantidad,articulo.precio*articulo_venta.cantidad,proveedores.nombre"
                    + " FROM proveedores JOIN articulo JOIN articulo_venta JOIN venta\n"
                    + "ON proveedores.id = articulo.proveedor AND articulo.codbar = articulo_venta.articulo\n"
                    + "AND articulo_venta.venta = venta.id\n"
                    + "WHERE venta.dia >='" + fecha1 + "' AND venta.dia<='" + fecha2 + "' AND fechaFin IS NULL";
            System.out.println(sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            while (rs.next()) {
                ven = new String[5];
                ven[0] = rs.getString(1);
                ven[1] = rs.getString(2);
                ven[2] = rs.getString(3);
                ven[3] = rs.getString(4);
                ven[4] = rs.getString(5);
                venta.add(ven);
            }
            rs.close();
            for (String[] strings : venta) {
                System.out.println(strings[0] + "  " + strings[1] + "  " + strings[2] + "  " + strings[3] + "  " + strings[4]);
            }
            System.out.println("------------------------------------------------------------------------------------------");
            return venta;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return venta;
    }

    public ArrayList<String[]> buscarVenta(String id) {
        ArrayList<String[]> venta = new ArrayList<String[]>();
        String[] ven;
        try {
            String sql = "SELECT DISTINCT venta.id,venta.dia,venta.hora,venta.saldo,venta.ganancia,venta.hora"
                    + " FROM venta JOIN articulo_venta  ON venta.id = articulo_venta.venta "
                    + "WHERE venta.id = " + id + ";";
            System.out.println(sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            while (rs.next()) {
                ven = new String[5];
                ven[0] = rs.getString(1);
                ven[1] = rs.getString(2);
                ven[2] = rs.getString(3);
                ven[3] = rs.getString(4);
                ven[4] = rs.getString(5);
                venta.add(ven);
            }
            rs.close();
            return venta;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return venta;
    }

    public ArrayList<String[]> getCuentas() {
        ArrayList<String[]> venta = new ArrayList<String[]>();
        String[] ven;
        try {
            String sql = "SELECT cliente.*,(SELECT fecha FROM cuenta WHERE cliente = cliente.id ORDER BY fecha desc limit 1)"
                    + "FROM cliente WHERE id > 1;";
            System.out.println(sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            while (rs.next()) {
                ven = new String[7];
                ven[0] = rs.getString(1);
                ven[1] = rs.getString(2);
                ven[2] = rs.getString(4);
                ven[3] = rs.getString(3);
                ven[4] = rs.getString(5);
                ven[5] = rs.getString(6);
                ven[6] = rs.getString(7);
                venta.add(ven);
            }
            rs.close();
            return venta;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return venta;
    }

    public ArrayList<String[]> getCuentas(String cliente) {
        String query = "SELECT venta.id,venta.dia FROM venta  WHERE credito=1 AND cliente = '" + cliente + "' ORDER BY id desc ;";
        System.out.println(query);
        ResultSet rs;
        ArrayList<String[]> listaT = new ArrayList<String[]>();
        String[] productoDetails = null;
        try {
            rs = Conexion.stt.executeQuery(query);
            while (rs.next()) {
                productoDetails = new String[2];
                productoDetails[0] = rs.getString(1);
                productoDetails[1] = rs.getString(2);
                listaT.add(productoDetails);
                productoDetails = null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class
                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return listaT;
    }

    public ArrayList<String[]> buscarCompra(String fecha1, String fecha2, String proveedor) {
        ArrayList<String[]> venta = new ArrayList<String[]>();
        String[] ven;
        String subSQL = "";
        if (!proveedor.equals("-1")) {
            subSQL = "JOIN articulo_compra JOIN articulo JOIN proveedores ON compra.id = articulo_compra.compra AND articulo_compra.articulo = articulo.codbar AND articulo.proveedor = " + proveedor;
        }
        try {
            String sql = "SELECT DISTINCT compra.id,compra.dia,compra.saldo,compra.hora FROM compra " + subSQL + " WHERE dia >='" + fecha1 + "' AND dia <= '" + fecha2 + "';";
            System.out.println(sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            while (rs.next()) {
                ven = new String[4];
                ven[0] = rs.getString(1);
                ven[1] = rs.getString(2);
                ven[2] = rs.getString(3);
                ven[3] = rs.getString(4);
                venta.add(ven);
            }
            rs.close();
            return venta;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return venta;
    }

    public ArrayList<String[]> getRowsCaja(String turno, String tipo) {
        ArrayList<String[]> venta = new ArrayList<String[]>();
        String[] ven;
        try {
            String sql = "SELECT * FROM caja WHERE turno ='" + turno + "' AND detalle <> 'ABONO' AND detalle <> 'LIQUIDACIÓN DE COMPRA' AND tipo = '" + tipo + "';";
            System.out.println(sql);
            ResultSet rs = Conexion.stt.executeQuery(sql);
            while (rs.next()) {
                ven = new String[4];

                ven[0] = rs.getString(2);
                ven[1] = rs.getString(5);
                ven[2] = rs.getString(3);
                ven[3] = rs.getString(5);
                venta.add(ven);
            }
            rs.close();
            return venta;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return venta;
    }

    public String sumaCaja() {

        String ven = "";
        try {
            String sql = "SELECT existente FROM caja order by id desc limit 1";
            ResultSet rs = Conexion.stt.executeQuery(sql);
            while (rs.next()) {
                ven = rs.getString(1);
            }
            rs.close();
            return ven;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ven;
    }

    public String totalInventario() {
        double total = 0.0;
        try {
            String sql = "SELECT  precio_compra,inventario FROM articulo";
            ResultSet rs = Conexion.stt.executeQuery(sql);
            while (rs.next()) {
                total += (Double.parseDouble(rs.getString(1)) * Double.parseDouble(rs.getString(2)));
            }
            rs.close();
            return "" + total;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "" + total;
    }

    public String esperadoEnCaja() {
        double total = 0.0;
        try {
            String sql = "SELECT existente FROM ventasbasic.caja WHERE id = (SELECT Max(id) FROM caja)";
            ResultSet rs = Conexion.stt.executeQuery(sql);
            while (rs.next()) {
                total += Double.parseDouble(rs.getString(1));
            }
            rs.close();
            return "" + total;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "" + total;
    }

    public String gananciaReal(String inicio, String fin) {
        double total = 0.0;
        try {
            String sql = "SELECT (SUM(articulo_venta.subtotal)-SUM(articulo.precio_compra*articulo_venta.cantidad)) "
                    + "AS ganancia FROM articulo JOIN articulo_venta JOIN venta ON articulo.codbar = articulo_venta.articulo "
                    + "AND articulo_venta.venta = venta.id WHERE venta.dia >='" + inicio + "' AND venta.dia <='" + fin + "'";
            ResultSet rs = Conexion.stt.executeQuery(sql);
            while (rs.next()) {
                total += Double.parseDouble(rs.getString(1));
            }
            rs.close();
            return "" + total;
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return "" + total;
    }

    public String insertarCotizacion(String cliente, String producto, String cantidad, String precio, String sub, String fecha) {
        String id = "";
        try {
            Conexion.stt.execute("Insert into cotizaciones "
                                 + "values(NULL,'" + producto + "','" + cantidad + "','" + precio + "'"
                                 + ",'" + sub + "','" + cliente + "','" + fecha + "');");
            ResultSet rs = Conexion.stt.executeQuery("SELECT MAX(id) FROM turno");

            while (rs.next()) {
                id = rs.getString(1);
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return id;
        }
    }
}
