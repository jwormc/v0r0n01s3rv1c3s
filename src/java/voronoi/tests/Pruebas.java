/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi.tests;


import java.util.List;
import voronoi.helpers.AnuncioHelper;
import voronoi.mappingpojos.Anuncio;

/**
 *
 * @author julio
 */




public class Pruebas {
    
    public static void main(String args[]){
        List<Anuncio> anuncio=new AnuncioHelper().getAnuncioByID(1);
        
    System.out.println(anuncio.get(anuncio.size()-1).getId());
    
    }
    
}
