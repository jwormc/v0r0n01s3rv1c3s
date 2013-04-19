/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi.ws;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
                        anuncios_lis+="<li id=\""+anuncio.getId() +"\"  class=\"listadeclientes\"  title=\""+ anuncio.getShortD() +"\" ><a href=\"#cliente\">"+ anuncio.getNombre() +"</a></li>";
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
                                     +"<h5>" +anuncio.getCalle()+" No"+anuncio.getNumero()+"<br>"+anuncio.getColonia()+", C.P. "+anuncio.getCp()+"</h5>*"
                                     +"<img id=\"img_pos\"  src=\"http://maps.googleapis.com/maps/api/staticmap?center="+s.substring(1,s.length()-1)+"&zoom=18&size=500x500&markers=color:blue%7Clabel:S%7C"+s.substring(1,s.length()-1)+"&sensor=false\" width=\"500\" height=\"500\"/>*"
                                     +"<img src=\"http://directel.mx/content/img/logos/"+anuncio.getLogo()+"\" alt=\"image\" style=\"position: absolute; top: 0%; left: 0%; margin-left: -16px; margin-top: -18px\">*"
                                     +"<h5>"+anuncio.getTelefono()+"</h5>";
                        System.out.println("a devolver" + anuncios_lis);
                }
                
                 System.out.println("Lista de Estados Li OK!");
                
                
    return anuncios_lis;
    }
     
     
     private String listaTels(String tel){
     
         
           String[] ss=tel.split(" ");
           String   sinS="";
           for(int i=0;i<ss.length;i++){
           sinS=sinS.concat(ss[i]);
  }
  
  
        String[] ssC=sinS.split(";");
        List<String> tels=new ArrayList<String>();
        
        
        for(int i=0;i<ssC.length;i++){
        if(ssC[i].substring(0, 5).equals("Local")){
            try{
            tels.add("tel:"+ssC[i].split(",")[2]);
            }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("nodata.. no added to telsLocal.. no problem :)");
            }
        } 
        }
        
        
        List<String> telsCel=new ArrayList<String>();
         for(int i=0;i<ssC.length;i++){
        if(ssC[i].substring(0, 7).equals("Celular")){
            try{
            tels.add("tel:"+ssC[i].split(",")[1]+ssC[i].split(",")[2]);
            }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("nodata.. no added to telsCel.. no problem :)");
            }
        } 
        }
         
         
         
         
            List<String> telsNexID=new ArrayList<String>();
         for(int i=0;i<ssC.length;i++){
        if(ssC[i].substring(0, 8).equals("NextelID")){
            try{
            tels.add("tel:"+ssC[i].split(",")[2]);
            }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("nodata.. no added to telsCel.. no problem :)");
            }
        } 
        }
         
                List<String> telsNex=new ArrayList<String>();
         for(int i=0;i<ssC.length;i++){
        if(ssC[i].substring(0, 6).equals("Nextel") && !ssC[i].substring(0, 8).equals("NextelID")){
            try{
            tels.add("tel:"+ssC[i].split(",")[1]+ssC[i].split(",")[2]);
            }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("nodata.. no added to telsCel.. no problem :)");
            }
        } 
        }
        
  
          List<String> telsFAX=new ArrayList<String>();
         for(int i=0;i<ssC.length;i++){
        if(ssC[i].substring(0, 3).equals("FAX")){
            try{
            tels.add("tel:"+ssC[i].split(",")[1]+ssC[i].split(",")[2]);
            }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("nodata.. no added to telsCel.. no problem :)");
            }
        } 
        }
         
           List<String> telsCeroUno=new ArrayList<String>();
         for(int i=0;i<ssC.length;i++){
        if(ssC[i].substring(0,5).equals("01800")){
            try{
            tels.add("tel:"+ssC[i].split(",")[1]+ssC[i].split(",")[2]);
            }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("nodata.. no added to telsCel.. no problem :)");
            }
        } 
        }
  

         
        // for(int ii=0;ii<tels.size();ii++)
          //   System.out.println(tels.get(ii));
         
         return "tmp";
     }
    
    
}
