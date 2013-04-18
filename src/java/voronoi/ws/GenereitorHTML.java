/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi.ws;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import voronoi.helpers.AnuncioHelper;
import voronoi.helpers.EstadosHelpers;
import voronoi.mappingpojos.Anuncio;
import voronoi.mappingpojos.Estados;

/**
 *
 * @author julio
 */
public class GenereitorHTML {
    
 
    public String getEstadosListLi(){
    
        String estados_lis="";
        Iterator<Estados> iterator = new EstadosHelpers().getEstados().iterator();
                while (iterator.hasNext()) {
                        Estados estado=iterator.next();
                        estados_lis+="<li id=\""+estado.getId() +"\" title=\""+ estado.getEstado() +"\" ><a href=\"#lista_categorias\">"+ estado.getMunicipio() +"</a></li>";
                }
                
                 System.out.println("Lista de Estados Li OK!");
                
                
    return estados_lis;
    }
    
       public String getAnunciosRegExLi(){ 
    
        String anuncios_lis="";
        Iterator<Anuncio> iterator = new AnuncioHelper().getAnunciosByREGEX("Doctores").iterator();
                while (iterator.hasNext()) {
                        Anuncio anuncio=iterator.next();
                        anuncios_lis+="<li id=\""+anuncio.getId() +"\" title=\""+ anuncio.getShortD() +"\" ><a href=\"#cliente\">"+ anuncio.getNombre() +"</a></li>";
                }
                
                 System.out.println("Lista de Estados Li OK!");
                
                
    return anuncios_lis;
    }
       
       
     public String getAnuncioById(String id){
        String anuncios_lis="";
        Iterator<Anuncio> iterator = new AnuncioHelper().getAnuncioByID(id).iterator();
                while (iterator.hasNext()) {
                        Anuncio anuncio=iterator.next();
                        anuncios_lis+="<b>" +anuncio.getNombre()+  "</b>,<b>" +anuncio.getCalle()+  "</b>,<img src=\"http://maps.googleapis.com/maps/api/staticmap?center=22.1514818,-100.9802254&zoom=17&size=500x500&markers=color:blue%7Clabel:S%7C22.1514818,-100.9802254&sensor=false\" width=\"288\" height=\"200\"/>";
                        System.out.println("a devolver" + anuncios_lis);
                }
                
                 System.out.println("Lista de Estados Li OK!");
                
                
    return anuncios_lis;
    }
    
    
}
