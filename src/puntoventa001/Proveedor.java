/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package puntoventa001;

/**
 *
 * @author Omar
 */
public class Proveedor {
    
    private String id;
    private String nombre;
    private String apellidos;
    private String compania;

    public Proveedor(String id, String nombre, String apellidos, String compania) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.compania = compania;
    }
    
    public String getApellidos() {
        return apellidos;
    }

    @Override
    public String toString() {
        return nombre ;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
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