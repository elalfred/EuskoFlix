package Euskoflix;


import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elalfred
 */
public class Usuario{
    private Map<Integer,Float> listaValoraciones;

    public Usuario(){
        this.listaValoraciones=new HashMap<>();
    }
  
    /**
     * añade la peli pIdPeli y su valoracion sobre 5 a la lista de valoraciones del usuario
     * @param pIdPeli
     * @param pValoracion 
     */
    public void annadirValoracion(int pIdPeli, float pValoracion){
        this.listaValoraciones.put(pIdPeli,pValoracion);
    }
}