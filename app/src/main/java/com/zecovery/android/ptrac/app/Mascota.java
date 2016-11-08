package com.zecovery.android.ptrac.app;

/**
 * Created by moe on 24-10-16.
 */

public class Mascota {

    private int numeroFolio;
    private int numeroIngreso;
    private String numeroChip;
    private String tipoTenencia;
    private String responsable;
    private String rut;
    private String direccion;
    private String numeroDepartamento;
    private String region;
    private String comuna;
    private String telefonoFijo;
    private String telefonoCelular;
    private String correoElectronico;
    private String nombre;
    private String especie;
    private String sexo;
    private String fechaNacimiento;
    private String color;
    private String raza;
    private String comoRecibio;
    private String razonTenencia;

    public Mascota() {
    }

    public Mascota(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroFolio() {
        return numeroFolio;
    }

    public void setNumeroFolio(int numeroFolio) {
        this.numeroFolio = numeroFolio;
    }

    public int getNumeroIngreso() {
        return numeroIngreso;
    }

    public void setNumeroIngreso(int numeroIngreso) {
        this.numeroIngreso = numeroIngreso;
    }

    public String getNumeroChip() {
        return numeroChip;
    }

    public void setNumeroChip(String numeroChip) {
        this.numeroChip = numeroChip;
    }

    public String getTipoTenencia() {
        return tipoTenencia;
    }

    public void setTipoTenencia(String tipoTenencia) {
        this.tipoTenencia = tipoTenencia;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
    public String getRut() {
        return rut;
    }

    public void setRut(String rutPropietario) {
        this.rut = rut;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String calle) {
        this.direccion = direccion;
    }

    public String getNumeroDepartamento() {
        return numeroDepartamento;
    }

    public void setNumeroDepartamento(String numeroDepartamento) {
        this.numeroDepartamento = numeroDepartamento;
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

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String paciente) {
        this.nombre = paciente;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getComoRecibio() {
        return comoRecibio;
    }

    public void setComoRecibio(String comoRecibio) {
        this.comoRecibio = comoRecibio;
    }

    public String getRazonTenencia() {
        return razonTenencia;
    }

    public void setRazonTenencia(String razonTenencia) {
        this.razonTenencia = razonTenencia;
    }
}
