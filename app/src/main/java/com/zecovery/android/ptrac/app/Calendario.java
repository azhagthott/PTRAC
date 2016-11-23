package com.zecovery.android.ptrac.app;

/**
 * Created by moe on 16-11-16.
 */

public class Calendario {

    private String region;
    private String comuna;
    private String direccion;
    private String telefono;
private String estado;

    public Calendario() {
    }

    public Calendario(String region, String comuna, String direccion, String telefono, String estado) {
        this.region = region;
        this.comuna = comuna;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estado = estado;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
