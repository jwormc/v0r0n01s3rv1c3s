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
    
       public String getAnunciosRegExLi(String s){ 
    
        String anuncios_lis="";                                         
        Iterator<Anuncio> iterator = new AnuncioHelper().getAnunciosByREGEX(s).iterator();
                while (iterator.hasNext()) {
                        Anuncio anuncio=iterator.next();
                        anuncios_lis+="<li id=\""+anuncio.getId() +"\"  class=\"listadeclientes\"  title=\""+ anuncio.getShortD() +"\" ><a href=\"#cliente\"> <h2>"+ anuncio.getNombre() +"</h2>"
                                     + "<p><strong>"+anuncio.getDescripcion()+"</strong></p> "
                                     + "<p class=\"ui-li-aside\"><strong>"+anuncio.getColonia()+"</strong></p></a></li>";
                }
                
                 System.out.println("Lista de Anuncios Li OK!");
                
                
    return anuncios_lis;
    }
       
       
     public String getAnuncioById(String id,String displayString){
        String anuncios_lis="";
        Iterator<Anuncio> iterator = new AnuncioHelper().getAnuncioByID(id).iterator();
                while (iterator.hasNext()) {
                        Anuncio anuncio=iterator.next();
                        String urlCroquis;
                        String s=anuncio.getCoordenadas(); 
                        
                        int xsize=Integer.parseInt(displayString.split("x")[0]);
                        int ysize=Integer.parseInt(displayString.split("x")[1]);
                        xsize=(xsize-(xsize/6)); //lo ancho si le tienes que reducir algo
                        
                        
                           if(anuncio.getCoordenadas().equals(null) || anuncio.getCoordenadas().length()==0)
                                 urlCroquis="<img id=\"img_pos\"  src=\"img/notfound.jpg\" width=\"500\" height=\"500\"/>|";
                           else
                             urlCroquis="<img id=\"img_pos\"  src=\"http://maps.googleapis.com/maps/api/staticmap?center="+s.substring(1,s.length()-1)+"&zoom=18&size="+   xsize+"x"+ysize   +"&markers=color:blue%7Clabel:S%7C"+s.substring(1,s.length()-1)+"&sensor=false\" width=\""+ xsize +"\" height=\""+ ysize +"\"/>|";
                      
                        
                        
                        anuncios_lis+="<b>"+anuncio.getNombre()+"</b>|"
                                     +"<h5>" +anuncio.getCalle()+" No"+anuncio.getNumero()+"<br>"+anuncio.getColonia()+", C.P. "+anuncio.getCp()+"</h5>|"
                                     +urlCroquis
                                     +"<img src=\"http://directel.mx/content/img/logos/"+anuncio.getLogo()+"\"    width=\"400\" height=\"200\"   \">|"
                                     +""+listaTels(anuncio.getTelefono())+"|"
                                     +"<li>"+anuncio.getHorario()+"</li>|"
                                     +"<div><p>"+anuncio.getNombre()+"</p>"
                                     +"<p>"+anuncio.getDescripcion()+"</p>" 
                                     +"<p  class=\"ui-li-aside\">"+anuncio.getEmail()+"</p></div>|"
                                     + getWebYSociales(anuncio.getWww() + "");
                             
   
                        System.out.println("html generado -->  " + anuncios_lis);
                }
                 System.out.println("Lista de Anuncio Data Li OK!");
                
                
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
            tels.add("Local:tel:"+ssC[i].split(",")[2]);
            }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("nodata.. no added to telsLocal.. no problem :)");
            }
        } 
        }
        
        
        List<String> telsCel=new ArrayList<String>();
         for(int i=0;i<ssC.length;i++){
        if(ssC[i].substring(0, 7).equals("Celular")){
            try{
            tels.add("Cel:tel:"+ssC[i].split(",")[1]+ssC[i].split(",")[2]);
            }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("nodata.. no added to telsCel.. no problem :)");
            }
        } 
        }
         
         
         
         
            List<String> telsNexID=new ArrayList<String>();
         for(int i=0;i<ssC.length;i++){
        if(ssC[i].substring(0, 8).equals("NextelID")){
            try{
            tels.add("Next. Id:tel:"+ssC[i].split(",")[2]);
            }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("nodata.. no added to telsCel.. no problem :)");
            }
        } 
        }
         
                List<String> telsNex=new ArrayList<String>();
         for(int i=0;i<ssC.length;i++){
        if(ssC[i].substring(0, 6).equals("Nextel") && !ssC[i].substring(0, 8).equals("NextelID")){
            try{
            tels.add("NexTel:tel:"+ssC[i].split(",")[1]+ssC[i].split(",")[2]);
            }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("nodata.. no added to telsCel.. no problem :)");
            }
        } 
        }
        
  
          List<String> telsFAX=new ArrayList<String>();
         for(int i=0;i<ssC.length;i++){
        if(ssC[i].substring(0, 3).equals("FAX")){
            try{
            tels.add("FAX:tel:"+ssC[i].split(",")[1]+ssC[i].split(",")[2]);
            }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("nodata.. no added to telsCel.. no problem :)");
            }
        } 
        }
         
           List<String> telsCeroUno=new ArrayList<String>();
         for(int i=0;i<ssC.length;i++){
        if(ssC[i].substring(0,5).equals("01800")){
            try{
            tels.add("Tel (01-800):tel:"+ssC[i].split(",")[1]+ssC[i].split(",")[2]);
            }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("nodata.. no added to telsCel.. no problem :)");
            }
        } 
        }
  
        String finalString="";
         
        for(int ii=0;ii<tels.size();ii++){
        finalString+="<li class=\"linkses\" ><a href=\""+tels.get(ii).split(":")[1]+":"+tels.get(ii).split(":")[2]+"\">"+tels.get(ii).split(":")[0]+":"+tels.get(ii).split(":")[2]+"</a></li>";
        }
        
       
        System.out.println(finalString);
        
        return   finalString;
     }
     
     
     public String getWebYSociales(String s){
     
      String[] ss=s.split(";");
      List<String> sociales=new ArrayList<String>();
      
        for(int i=0;i<s.split(";").length;i++){
            try{
           sociales.add((s.split(";")[i]).split(",")[1]);
            }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("nodata.. no added to Sociales array splitting.. no problem :)");
            }
        
        }
        
        String finalString="";
        for(int i=0;i<sociales.size();i++){   
        finalString+="<li class='linkses'><a href=\" " + sociales.get(i) + "\"> <h2>"+sociales.get(i)+"</h2></a></li>";
        }
     
        
        return finalString;
     }
    
    
}
