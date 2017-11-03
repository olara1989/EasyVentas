/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package puntoventa001;

/**
 *
 * @author Omar
 */
public class Usuario {

    private String id;
    private String nombre;
    private String usuario;
    private String pass;

    public Usuario(String id, String usuario, String pass,String nombre ) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}