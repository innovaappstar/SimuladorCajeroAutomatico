/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cajeroautomatico.entities;

/**
 *
 * @author Kenny
 */
public class Cliente {
    
    String numeroTarjeta;
    String nombre;
    String claveTarjeta;
    String direccion;
    String telefono;

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClaveTarjeta() {
        return claveTarjeta;
    }

    public void setClaveTarjeta(String claveTarjeta) {
        this.claveTarjeta = claveTarjeta;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    
}
