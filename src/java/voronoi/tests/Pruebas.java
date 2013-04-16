/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi.tests;


import java.util.Iterator;
import voronoi.helpers.AnuncioHelper;
import voronoi.mappingpojos.Anuncio;
import voronoi.mappingpojos.Estados;

/**
 *
 * @author julio
 */




public class Pruebas {
    
    public  static void main(String args[]){
    Iterator<Anuncio> iterator = new AnuncioHelper().getAnunciosByREGEX("Doctores").iterator();
                while (iterator.hasNext()) {
                        Anuncio anuncio=iterator.next();
                       System.out.println("----->>>>        " + anuncio.getNombre());
                      
                }
    
    }
    
}
