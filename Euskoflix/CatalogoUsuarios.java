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
public class CatalogoUsuarios{
    private Map<Integer,Usuario> listaUsuarios;
    private static CatalogoUsuarios miCU = new CatalogoUsuarios();

    private CatalogoUsuarios(){
        this.listaUsuarios= new HashMap<>();
    }
    
    public static CatalogoUsuarios getMiCU(){  return miCU;  }
    
    /**
     * Añade la valoracion de pIdPeli a la lista de valoraciones de pIdUsuario y añade pIdUsuario si no existe
     * @param pIdUsuario
     * @param pIdPeli
     * @param pValoracion 
     */
    public void annadirValoracionAUsuario(int pIdUsuario, int pIdPeli, float pValoracion ){
        if(!this.listaUsuarios.containsKey(pIdUsuario)){
            this.listaUsuarios.put(pIdUsuario, new Usuario());
        }
        this.listaUsuarios.get(pIdUsuario).annadirValoracion(pIdPeli, pValoracion);
    }
}
