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
                        String s=anuncio.getCoordenadas(); 
                        anuncios_lis+="<b>"+anuncio.getNombre()+"</b>*"
                                     +"<b>" +anuncio.getCalle()+"</b>*"
                                     +"<img id=\"img_pos\"  src=\"http://maps.googleapis.com/maps/api/staticmap?center="+s.substring(1,s.length()-1)+"&zoom=17&size=500x500&markers=color:blue%7Clabel:S%7C"+s.substring(1,s.length()-1)+"&sensor=false\" width=\"288\" height=\"200\"/>*"
                                     +"<img src=\"http://directel.mx/content/img/logos/"+anuncio.getLogo()+"\" alt=\"image\" style=\"position: absolute; top: 0%; left: 0%; margin-left: -16px; margin-top: -18px\">";
                        System.out.println("a devolver" + anuncios_lis);
                }
                
                 System.out.println("Lista de Estados Li OK!");
                
                
    return anuncios_lis;
    }
    
    
}
