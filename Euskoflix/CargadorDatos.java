package Euskoflix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elalfred
 */
public class CargadorDatos {
    private static final String RUTA_F_ETIQUETAS="src\\Datos\\movie-tags.csv";
    private static final String RUTA_F_PELICULAS="src\\Datos\\movie-titles.csv";
    private static final String RUTA_F_VALORACIONES="src\\Datos\\movie-ratings.csv";
    private static final String SEPARADOR1=";";
    private static final String SEPARADOR2=",";
    
    
    public static void cargarDatos(){
        cargarFicheroPeliculas();
        cargarFicheroEtiquetas();
        cargarFicheroValoraciones();
    }
    
    /**
     * Recorre el fichero con ruta pRutaFichero 
     * @param pRutaFichero
     * @return lista con todas las lineas del fichero
     */
    private static ArrayList<String> getLineasFichero(String pRutaFichero){
        BufferedReader br = null;
        ArrayList<String> listaLineas = null;
        try {
            listaLineas= new ArrayList<>();
            br =new BufferedReader(new FileReader(pRutaFichero));
            String line = br.readLine();
            while (null!=line) {
                listaLineas.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Peto durante la lectura");
        } finally {
            try{
                if (null!=br) {
                   br.close();
                }
            }catch(Exception e){
                System.out.println("Peto al cerrarlo");
            }
        }
        return listaLineas;
    } 
    
    /**
     * Añade al catalogo de peliculas todas las peliculas junto con sus ids
     */
    private static void cargarFicheroPeliculas() {
        CatalogoPeliculas catPeliculas = CatalogoPeliculas.getMiCPeli();
        ArrayList<String> listaPeliculas = getLineasFichero(RUTA_F_PELICULAS);
        for(String linea : listaPeliculas){
            String [] fields = linea.split(SEPARADOR1);
            catPeliculas.annadirPelicula((Integer.parseInt(fields[0])),fields[1].substring(1, fields[1].length()));
        }
    }
    
    /**
     * Añade al catalogo de etiquetas todas las que se encuentren en el fichero y el numero de apariciones total de la etiqueta
     * y añade a cada pelicula del catalogo las etiquetas que aparecen en ella junto con el numero de apariciones de la etiqueta en la peli
     */
    private static void cargarFicheroEtiquetas(){
        CatalogoPeliculas catPeliculas = CatalogoPeliculas.getMiCPeli();
        CatalogoEtiquetas catEtiquetas = CatalogoEtiquetas.getMiCEti();
        ArrayList<String> listaEtiquetas = getLineasFichero(RUTA_F_ETIQUETAS);
        String lAct = listaEtiquetas.get(0);
        listaEtiquetas.remove(0);
        int apariciones=1;
        for(String nextL :listaEtiquetas){
            if(lAct.equals(nextL)){
                apariciones++;
                continue;
            }
            String [] fields = lAct.split(SEPARADOR1);
            catPeliculas.annadirEtiqueta(Integer.parseInt(fields[0]), apariciones, fields[1]);
            catEtiquetas.annadirPeliAEtiqueta(fields[1]);
            lAct=nextL;
            apariciones=1;   
        }
        String [] fields = lAct.split(SEPARADOR1);
        catPeliculas.annadirEtiqueta(Integer.parseInt(fields[0]), apariciones, fields[1]);
        catEtiquetas.annadirPeliAEtiqueta(fields[1]);
        calcularPesoEtiquetas();
    }
    
    /**
     * Añade al catalogo de Usuarios todas sus Valoraciones
     */
    private static void cargarFicheroValoraciones(){
        CatalogoUsuarios catUsuarios = CatalogoUsuarios.getMiCU();
        ArrayList<String> listaValoraciones = getLineasFichero(RUTA_F_VALORACIONES);
        for(String linea: listaValoraciones){
            String [] fields = linea.split(SEPARADOR2);
            catUsuarios.annadirValoracionAUsuario(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), Float.parseFloat(fields[2]));
        }
    }
    
    /**
     * Calcula el TF-IDF de cada etiqueta de cada pelicula y lo sustituye en el HashMap por el nº de apariciones
     */
    private static void calcularPesoEtiquetas(){
        CatalogoPeliculas catPeliculas = CatalogoPeliculas.getMiCPeli();
        CatalogoEtiquetas catEtiquetas = CatalogoEtiquetas.getMiCEti();
        for( int idPeli : catPeliculas.getListaPeliculas()){
            for (String etiqueta: catPeliculas.getPelicula(idPeli).getNombresEtiquetas()){
                float TF_IDF= catPeliculas.getPelicula(idPeli).getPesoEtiqueta(etiqueta);
                TF_IDF*=Math.log(catPeliculas.getTotalPelis()/catEtiquetas.getApariciones(etiqueta));
                catPeliculas.annadirEtiqueta(idPeli,TF_IDF, etiqueta);
            }
        }
    }
    
}
