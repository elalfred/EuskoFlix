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
public class CatalogoPeliculas{  
    private Map<Integer,Pelicula> listaPeliculas;
    private static CatalogoPeliculas miCPeli = new CatalogoPeliculas();
    
    public void CatalogoPeliculas(){  this.listaPeliculas=new HashMap<>(); }
    
    /**
     * 
     * @return 
     */
    public static CatalogoPeliculas getMiCPeli(){ return miCPeli;}

    public Set<Integer> getListaPeliculas() {return listaPeliculas.keySet(); }
    
    public Pelicula getPelicula(int pIdPeli){ return this.listaPeliculas.get(pIdPeli);}
    
    /**
     * Añade una pelicula al catalogo
     * @param pId
     * @param pNombre 
     */
    public void annadirPelicula(int pId, String pNombre){ this.listaPeliculas.put(pId,new Pelicula(pNombre));}
    
    /**
     * Añade a la pelicula con id pIdPeli la etiqueta pEtiqueta y el numero de apariciones
     * @param pIdPeli
     * @param pNAparicionesE
     * @param pEtiqueta 
     */
    public void annadirEtiqueta(int pIdPeli , float pNAparicionesE ,String pEtiqueta ){ this.listaPeliculas.get(pIdPeli).annadirEtiqueta(pEtiqueta, pNAparicionesE); }
    
    public int getTotalPelis(){ return this.listaPeliculas.size();}

}