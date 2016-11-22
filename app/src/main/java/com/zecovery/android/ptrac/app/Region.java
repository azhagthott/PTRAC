package com.zecovery.android.ptrac.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moe on 16-11-16.
 */

public class Region {

    private int idRegion;
    private String nombreRegion;
    private String numeroRegion;

    public Region() {
    }

    public Region(int idRegion, String nombreRegion, String numeroRegion) {
        this.idRegion = idRegion;
        this.nombreRegion = nombreRegion;
        this.numeroRegion = numeroRegion;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public String getNombreRegion() {
        return nombreRegion;
    }

    public void setNombreRegion(String nombreRegion) {
        this.nombreRegion = nombreRegion;
    }

    public String getNumeroRegion() {
        return numeroRegion;
    }

    public void setNumeroRegion(String numeroRegion) {
        this.numeroRegion = numeroRegion;
    }

    public List<Region> getRegiones(){
        
        ArrayList<Region> list = new ArrayList<>();
        
        list.add(new Region(0,"Arica y Parinacota", "XV"));
        list.add(new Region(1,"Tarapacá", "I"));
        list.add(new Region(2,"Antofagasta", "II"));
        list.add(new Region(3,"Atacama", "III"));
        list.add(new Region(4,"Coquimbo", "IV"));
        list.add(new Region(5,"Valparaiso", "V"));
        list.add(new Region(6,"Metropolitana de Santiago", "RM"));
        list.add(new Region(7,"Libertador General Bernardo O'Higgins", "VI"));
        list.add(new Region(8,"Maule", "VII"));
        list.add(new Region(9,"Biobío", "VIII"));
        list.add(new Region(10,"La Araucania", "IX"));
        list.add(new Region(11,"Los Ríos", "XIV"));
        list.add(new Region(12,"Los Lagos", "X"));
        list.add(new Region(13,"Aisén del General Carlos Ibáñez del Campo", "XII"));
        list.add(new Region(14,"Magallanes y de la Antártica Chilena", "XII"));
        return list;
    }
    
}