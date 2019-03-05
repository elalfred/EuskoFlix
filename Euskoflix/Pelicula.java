package Euskoflix;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elalfred
 */
public class Pelicula{
    
    private final String nombre;
    private Map<String,Float> pesoEtiquetas;

    public Pelicula(String pNombre){
        this.nombre= pNombre;
        this.pesoEtiquetas= new HashMap<>();
    }

    public String getNombre() {return nombre;}
    
    public void annadirEtiqueta(String pEtiqueta, float pNApariciones){this.pesoEtiquetas.put(pEtiqueta,pNApariciones);}
    
    public float getPesoEtiqueta(String pEtiqueta){ return this.pesoEtiquetas.get(pEtiqueta);}

    public Set<String> getNombresEtiquetas() { return pesoEtiquetas.keySet(); }
    
}
