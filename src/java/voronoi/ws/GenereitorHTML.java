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
import voronoi.helpers.CiudadHelper;
import voronoi.helpers.CategoriaHelper;
import voronoi.mappingpojos.Anuncio;
import voronoi.mappingpojos.Estados;
import voronoi.mappingpojos.Ciudad;
import voronoi.mappingpojos.Categoria;

/**
 *
 * @author julio
 */
public class GenereitorHTML {
    //Atributos
    private static String path    = "ws.GenereitorHTML.";
    private static String version = "0.8";
 
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
    
    /***********************************************************************
      * getCiudadesListLi : Regresa la Lista de ciudades disponibles
      * 
      * @date    May 28th, 2013
      * @author  Howser
      * @return  String con las ciudades
      **********************************************************************/
    public String getCiudadesListLi(){
    
        String ciudades_lis="";
        try{
            
            Iterator<Ciudad> iterator = new CiudadHelper().getCiudades().iterator();
            while (iterator.hasNext()) {
                    Ciudad city=iterator.next();
                    ciudades_lis+="<li id='"+city.getId() +"' title='"+ city.getNombre()+"' class='ciudad' ><a href='#lista_categorias' >"+ city.getNombre()+"</a></li>";
            }//while

             //System.out.println("Lista de Ciudades cargada...!!");
            
            //La class='ciudad' es necesaria para que .ajax la ubique
             
        }catch(Exception ex){
                 System.out.println("ERROR : "+this.path+"getCiudadesListLi"+" : "+ex);
        }
                
        return ciudades_lis;
    }//method getCiudadesListLi
    
    
    /***********************************************************************
      * getCategoriasListLi : Regresa la Lista de categorias
      * 
      * @date    May 31th, 2013
      * @author  Howser
      * @return  String con las categorias
      **********************************************************************/
    public String getCategoriasListLi(){
    
        String res="";
        String id    ="";
        String title = "";
        String text_ = "";   //Indicio que se ve en dispositivo móvil
        try{
            Iterator<Categoria> iterator = new CategoriaHelper().getCategorias().iterator();
            while (iterator.hasNext()) {
                try{
                    Categoria cat=iterator.next();
                    id = ""; title = ""; text_ = "";
                    
                    //minusculas, espacios intermedios con _, sin acentos
                    id = cat.getUrl().trim().toLowerCase(); //salud-y-belleza
                    id = id.replaceAll(" ", "_");
                    id = id.replaceAll("-", "_");
                    id = id.replaceAll("á","a");
                    id = id.replaceAll("é","e");
                    id = id.replaceAll("í","i");
                    id = id.replaceAll("ó","o");
                    id = id.replaceAll("ú","u");
                    
                    //mayusculas, sin acentos
                    title = cat.getSecciones().trim().toUpperCase();  //Academias,Ambulancias,Clínicas y Hospitales,Enfermeras,Farmacias y Droguerías,.....
                    title = title.replaceAll("Á","A");
                    title = title.replaceAll("É","E");
                    title = title.replaceAll("Í","I");
                    title = title.replaceAll("Ó","O");
                    title = title.replaceAll("Ú","U");
                    
                    //
                    text_ = cat.getSecciones();
                    text_ = text_.replace('|', ',');
                    if(text_.length()>20)
                        text_ = text_.substring(0,19);
                    text_+="....";
                    
                    res+= "<li id='li_"+id+"'><a href='#categoria' id='"+id+"' title='"+title+"' class='categoria'>";
                    res+= "     <img src='img/cat/logo_"+id+".png'/>";
                    res+= "     <h2>"+cat.getNombre().trim()+"</h2>";
                    res+= "     <p>"+text_+"</p></a>";
                    res+= "</li>";
                    
                }catch(Exception ex_1){
                    System.out.println("ERROR : "+this.path+"getCategoriasListLi"+" : "+ex_1);
                }
                    
            }//while

             //System.out.println("Lista de Categorias cargada...!!");
            
            /*Así debe quedar
             * id    -> Icono y Nombre de la categoría
             * title -> Parámetros de búsqueda (minusculas, sin acentos, separados por |
             * 
             <li id='li_salud'><a href='#categoria' id='doctores' title='salud' class='categoria'>
                <img src='img/cat/logo_salud.png'>
                <h2>Salud y Belleza</h2>
                <p>Farmacias, Médicos, Dentistas .....</p></a>
             </li>
             */
             
        }catch(Exception ex){
                 System.out.println("ERROR : "+this.path+"getCategoriasListLi"+" : "+ex);
        }
                
        return res;
    }//method getCategoriasListLi
    
    
   /***********************************************************************
      * getAnuncios_KeyLi : Regresa la Lista de Anuncios que coincidan con alguna
      *                     de las etiquetas de las secciones y que correspondan a la ciudad
      * 
      * @date    May 31th, 2013
      * @author  Howser
      * @param   keywords   Etiquetas
      * @param   city       Id de la Ciudad
      * @return  String con las categorias
      **********************************************************************/
    public String getAnuncios_KeyLi(String keywords,String city){ 
    
        String anuncios_lis="";                                         
        Iterator<Anuncio> iterator = new AnuncioHelper().getAnunciosByKeywords(keywords,city).iterator();
                while (iterator.hasNext()) {
                        Anuncio anuncio=iterator.next();
                        anuncios_lis+="<li id='"+anuncio.getId() +"'  class='listadeclientes'  title='"+ anuncio.getShortD() +"' ><a href='#cliente'> <h3>"+ anuncio.getNombre() +"</h3>"
                                     + "<p class='desc'><strong>"+anuncio.getDescripcion()+"</strong></p> "
                                     + "<p class='ui-li-aside'><strong class='col-aside'>"+anuncio.getColonia()+"</strong></p></a></li>";
                }
                     
    return anuncios_lis;
    }//method getAnuncios_KeyLi
    
    
       public String getAnunciosRegExLi(String s){ 
    
        String anuncios_lis="";                                         
        Iterator<Anuncio> iterator = new AnuncioHelper().getAnunciosByREGEX(s).iterator();
                while (iterator.hasNext()) {
                        Anuncio anuncio=iterator.next();
                        anuncios_lis+="<li id='"+anuncio.getId() +"'  class='listadeclientes'  title='"+ anuncio.getShortD() +"' ><a href='#cliente'> <h3>"+ anuncio.getNombre() +"</h3>"
                                     + "<p class='desc'><strong>"+anuncio.getDescripcion()+"</strong></p> "
                                     + "<p class='ui-li-aside'><strong class='col-aside'>"+anuncio.getColonia()+"</strong></p></a></li>";
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
                             urlCroquis="<a href=\"#mapa\"><img id=\"img_pos\"  src=\"http://maps.googleapis.com/maps/api/staticmap?center="+s.substring(1,s.length()-1)+"&zoom=18&size="+   xsize+"x"+ysize   +"&markers=color:blue%7Clabel:S%7C"+s.substring(1,s.length()-1)+"&sensor=false\" width=\""+ xsize +"\" height=\""+ ysize +"\"/></a>|";
                      
                        
                        
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
     
     
     /***********************************************************************
      * listaTels_icon : Lista de Telefonos preparada para le formato nuevo
      *                  Basado en listaTels
      * 
      * @date    May 17th, 2013
      * @author  Howser
      * @param   tel Lista de Telefonos
      * @return  Código HTML5 de la lista
      **********************************************************************/
     private String listaTels_icon(String tel){
       
        String[] ss=tel.split(" ");
        String   sinS="";
        for(int i=0;i<ss.length;i++){
            sinS=sinS.concat(ss[i]);
        }
  
  
        String[] ssC=sinS.split(";");
        List<String> tags=new ArrayList<String>(); //Tags (Local, Celular, etc..)
        List<String> tels=new ArrayList<String>(); //Teléfonos
        
        
        
        for(int i=0;i<ssC.length;i++){
        if(ssC[i].substring(0, 5).equals("Local")){
            try{
                tags.add("Local");
                tels.add(""+ssC[i].split(",")[2]);
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("nodata.. no added to telsLocal.. no problem :)");
            }
        } 
        }
        
        
        List<String> telsCel=new ArrayList<String>();
        for(int i=0;i<ssC.length;i++){
        if(ssC[i].substring(0, 7).equals("Celular")){
            try{
                tags.add("Celular");
                tels.add(""+ssC[i].split(",")[1]+ssC[i].split(",")[2]);
            }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("nodata.. no added to telsCel.. no problem :)");
            }
        } 
        }
         
        List<String> telsNexID=new ArrayList<String>();
        for(int i=0;i<ssC.length;i++){
        if(ssC[i].substring(0, 8).equals("NextelID")){
            try{
                tags.add("NextlID");
                tels.add(""+ssC[i].split(",")[2]);
            }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("nodata.. no added to telsNextID.. no problem :)");
            }
        } 
        }
         
        List<String> telsNex=new ArrayList<String>();
        for(int i=0;i<ssC.length;i++){
        if(ssC[i].substring(0, 6).equals("Nextel") && !ssC[i].substring(0, 8).equals("NextelID")){
            try{
                tags.add("Nextel");
                tels.add(""+ssC[i].split(",")[1]+ssC[i].split(",")[2]);
            }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("nodata.. no added to Nextel.. no problem :)");
            }
        } 
        }
        
  
        List<String> telsFAX=new ArrayList<String>();
        for(int i=0;i<ssC.length;i++){
        if(ssC[i].substring(0, 3).equals("FAX")){
            try{
                tags.add("FAX");
                tels.add(""+ssC[i].split(",")[1]+ssC[i].split(",")[2]);
            }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("nodata.. no added to FAX.. no problem :)");
            }
        } 
        }
         
        List<String> telsCeroUno=new ArrayList<String>();
        for(int i=0;i<ssC.length;i++){
        if(ssC[i].substring(0,5).equals("01800")){
            try{
                tags.add("01-800");
                tels.add(""+ssC[i].split(",")[1]+ssC[i].split(",")[2]);
            }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("nodata.. no added to 01-800.. no problem :)");
            }
        } 
        }
  
        String finalString="";
         
        for(int ii=0;ii<tels.size();ii++){
            finalString+="<div class='telefonos_'>";
            finalString+="    <img src='img/phone.png'>";
            finalString+="    <h3 class='contact_tag'>"+ tags.get(ii) +"</h3>";
            finalString+="    <div class='telefonos_list'><a href='tel:"+tels.get(ii)+"'>"+ tels.get(ii) +"</a></div>";
            finalString+="</div>";
            
        }//for
        
        /* Asi debe quedar
         * <div class='telefonos_'>
            <img src='img/phone.png'>
            <h3 class='contact_tag'>Tels</h3>
            <div class='telefonos_list'><a href=''></a></div>
           </div>
         */
       
        //System.out.println(finalString);
        
        return   finalString;
     }//lista_Tels_icon
     
     
     /***********************************************************************
      * listaTels_all : Lista de Telefonos preparada para le formato nuevo
      *                 
      * 
      * @date    May 29th, 2013
      * @author  Howser
      * @param   tel Lista de Telefonos
      * @return  Código HTML5 de la lista
      **********************************************************************/
     private String listaTels_all(String tel){
       
        String res = "";
        try{
        
            //Separa las entradas de cada telefono
            String[] list_ = tel.split(";");
            String[] tel_component = null;

            List<String> tags  = new ArrayList<String>(); //Tags (Local, Celular, etc..)
            List<String> ladas = new ArrayList<String>(); //Ladas
            List<String> tels  = new ArrayList<String>(); //Telefonos
            List<String> desc  = new ArrayList<String>(); //Descripcion

            
            //Recorre cada entrada de telefono (TAG,LADA,TEL,DESC)
            for(int i=0;i<list_.length;i++){
                try{
                    //Los componentes estan separados por comas (',')
                    tel_component = list_[i].split(",");
                    
                    //Etiqueta o nombre del Telefono (Local, Celular, Nextel, etc....)
                    if(tel_component.length>0)
                        tags.add(tel_component[0]);
                    else
                        tags.add("");
                    
                    //Lada del telefono
                    if(tel_component.length>1)
                        ladas.add(tel_component[1]);
                    else
                        ladas.add("");
                    
                    //Telefono
                    if(tel_component.length>2)
                        tels.add(tel_component[2]);
                    else
                        tels.add("");
                    
                    //Descripcion si existe
                    if(tel_component.length>3)
                        desc.add(" ("+ tel_component[3]+")"); //Se le agrgan los parentesis aqui Local (Servicio 24hrs)
                    else
                        desc.add("");
                        
                    
                }catch(Exception ex_t){
                    System.out.println("ERROR : "+this.path+"listaTels_all"+" : "+ex_t);
                }
            }//for
            
            //Ahora se forma la cadena de salida
            String finalString="";
            String lada = "";
            String tel_ = "";

            for(int ii=0;ii<tels.size();ii++){
                                
                //La LADA visible en texto se pone entre () solo si existe
                if(ladas.get(ii).length()>0)
                    lada = "("+ ladas.get(ii)+")";
                else
                    lada = "";
                
                //El telefono del link tel: , no debe contener espacios
                tel_ = tels.get(ii).replaceAll(" ", "");
                
                finalString+="<div class='telefonos_'>";
                finalString+="    <img src='img/phone.png'>";
                finalString+="    <h3 class='contact_tag'>"+ tags.get(ii) + desc.get(ii) + "</h3>";
                finalString+="    <div class='telefonos_list'><a href='tel:"+ ladas.get(ii) + tel_+"'>"+ lada + tels.get(ii) +"</a></div>";
                finalString+="</div>";

            }//for

            res = finalString;
            /* Asi debe quedar
             * <div class='telefonos_'>
                <img src='img/phone.png'>
                <h3 class='contact_tag'>Local (con 5 lineas)</h3>
                <div class='telefonos_list'><a href='444123456'>(444)12 3456</a></div>
               </div>
             */
        }catch(Exception ex){
            System.out.println("ERROR : "+this.path+"listaTels_all"+" : "+ex);
        }
        return  res;
     }//method listaTels_all
     
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
     
     /***********************************************************************
      * listaWebs_icon : Lista de Webs preparada para le formato nuevo
      * 
      * 
      * @date    May 17th, 2013
      * @author  Howser
      * @param   s Lista de Webs
      * @return  Código HTML5 de la lista
      **********************************************************************/
     public String listaWebs_icon(String s){
     
      String[] ss=s.split(";");
      List<String> tags  =new ArrayList<String>(); //website, facebook, twitter
      List<String> links =new ArrayList<String>();
      
      
        for(int i=0;i<s.split(";").length;i++){
            try{
                tags.add((s.split(";")[i]).split(",")[0]);
                links.add((s.split(";")[i]).split(",")[1]);
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("nodata.. no added to Sociales array splitting.. no problem :)");
            }
        
        }
        
        String finalString="";
        /* FORMATO A
         <div class='web_a'>
            <img src='img/website.png'>
            <a class='link'>www.web.com</a>
	 </div>
         */
        /*
        for(int i=0;i<links.size();i++){   
            finalString+="<div class='web_a'>";
            finalString+="   <a href='"+links.get(i)+"'><img src='img/"+tags.get(i)+".png'></a>";
            finalString+="   <a href='"+links.get(i)+"'  class='link'>"+links.get(i)+"</a>";
            finalString+="</div>";
                    
        }//for
        */
        
        
        /* FORMATO B
         <div class="web_b">
            <img src='img/website.png'>
            <h3 class='contact_tag'>Web</h3>
            <div class='web_list_b'><a>www.web.com</a></div>
	 </div>
         */
        for(int i=0;i<links.size();i++){   
            finalString+="<div class='web_b'>";
            finalString+="   <a href='"+links.get(i)+"'><img src='img/"+tags.get(i)+".png'></a>";
            finalString+="   <h3 class='contact_tag'>"+tags.get(i)+"</h3>";
            finalString+="   <div class='web_list_b'><a href='"+links.get(i)+"'>"+links.get(i)+"</a></div>";
            finalString+="</div>";
                    
        }//for
     
        
        
        
        return finalString;
     }//method listaWebs_icon
    

     
        public String getFavoritosByIds(String s){ 
    
        String anuncios_favors="";                                         
        Iterator<Anuncio> iterator = new AnuncioHelper().getAnunciosFavoritos(s).iterator();
                while (iterator.hasNext()) {
                        Anuncio anuncio=iterator.next();
                        anuncios_favors+="<li id=\""+anuncio.getId() +"\"  class=\"listadeclientes\"  title=\""+ anuncio.getShortD() +"\" ><a href=\"#cliente\"> <h3>"+ anuncio.getNombre() +"</h3>"
                                     + "<p class='desc'><strong>"+anuncio.getDescripcion()+"</strong></p> "
                                     + "<p class='ui-li-aside'><strong class='col-aside'>"+anuncio.getColonia()+"</strong></p></a></li>";
                }
                
                 System.out.println("Lista de Favoritos Li OK!");
                
                
    return anuncios_favors;
    }
        
        
        

        
               public String getAnunciosBuscar(String s){ 
    
        String anuncios_lis="";                                         
        Iterator<Anuncio> iterator = new AnuncioHelper().getAnunciosBusqueda(s).iterator();
                while (iterator.hasNext()) {
                        Anuncio anuncio=iterator.next();
                        anuncios_lis+="<li id=\""+anuncio.getId() +"\"  class=\"listadeclientes\"  title=\""+ anuncio.getShortD() +"\" ><a href=\"#cliente\"> <h3>"+ anuncio.getNombre() +"</h3>"
                                     + "<p class='desc'><strong>"+anuncio.getDescripcion()+"</strong></p> "
                                     + "<p class='ui-li-aside'><strong class='col-aside'>"+anuncio.getColonia()+"</strong></p></a></li>";
                }
                
                 System.out.println("Lista de Anuncios Li OK!");
                
                
    return anuncios_lis;
    }
               
        /***********************************************************************
        * getAnunciosSearch : Regresa la Lista de Anuncios que coincidan con alguna
        *                     una palabra clave de busqueda en el nombre
        * 
        * @date    May 31th, 2013
        * @param   keyword   Palabra clave
        * @param   city      Id de la Ciudad
        * @author  Howser
        * @return  Lista con los anuncios
        **********************************************************************/
        public String getAnunciosSearch(String keyword, String city){ 
    
            String anuncios_lis="";                                         
            Iterator<Anuncio> iterator = new AnuncioHelper().getAnunciosSearch(keyword,city).iterator();
                while (iterator.hasNext()) {
                        Anuncio anuncio=iterator.next();
                        anuncios_lis+="<li id='"+anuncio.getId() +"'  class='listadeclientes'  title='"+ anuncio.getShortD() +"' ><a href='#cliente'> <h3>"+ anuncio.getNombre() +"</h3>"
                                     + "<p class='desc'><strong>"+anuncio.getDescripcion()+"</strong></p> "
                                     + "<p class='ui-li-aside'><strong class='col-aside'>"+anuncio.getColonia()+"</strong></p></a></li>";
                }//while
                

            return anuncios_lis;
        }//method getAnunciosSearch

        /***********************************************************************
         * getClient_name : Obtiene el nombre del cliente (negocio) por id
         * 
         * @date    May 15th, 2013
         * @author  Howser
         * @param   id 
         * @return  Cadena con el nombre
         **********************************************************************/
        public String getClient_name(String id){
        
            String res = "";
            try{
                Iterator<Anuncio> iterator = new AnuncioHelper().getAnuncioByID(id).iterator();
                while (iterator.hasNext()) {
                        Anuncio anuncio=iterator.next();
  
                        res = anuncio.getNombre();
                }
                
            }catch(Exception ex){
                 System.out.println("ERROR : "+this.path+"getClient_name"+" (id="+id+")-> : "+ex);
            }
            return res;
        }//method getClient_name
        
        
        /***********************************************************************
         * getClient_desc : Obtiene la descripcion corta del cliente (negocio) por id
         * 
         * @date    May 27th, 2013
         * @author  Howser
         * @param   id 
         * @return  Cadena con el nombre
         **********************************************************************/
        public String getClient_desc(String id){
        
            String res = "";
            try{
                Iterator<Anuncio> iterator = new AnuncioHelper().getAnuncioByID(id).iterator();
                while (iterator.hasNext()) {
                        Anuncio anuncio=iterator.next();
  
                        res = anuncio.getShortD();
                }
                
            }catch(Exception ex){
                 System.out.println("ERROR : "+this.path+"getClient_desc"+" (id="+id+")-> : "+ex);
            }
            return res;
        }//method getClient_desc
        
        /***********************************************************************
         * getClient_address : Obtiene la dirección del cliente (negocio) por id
         * 
         * @date    May 15th, 2013
         * @author  Howser
         * @param   id 
         * @return  Cadena con la dirección
         **********************************************************************/
        public String getClient_address(String id){
        
            String res = "";
            try{
                Iterator<Anuncio> iterator = new AnuncioHelper().getAnuncioByID(id).iterator();
                while (iterator.hasNext()) {
                        Anuncio anuncio=iterator.next();
  
                        res = anuncio.getCalle()+" No."+anuncio.getNumero()+"<br>"+anuncio.getColonia()+", C.P. "+anuncio.getCp();
                }
                
            }catch(Exception ex){
                 System.out.println("ERROR : "+this.path+"getClient_address"+" (id="+id+")-> : "+ex);
            }

            return res;
            
        }//method getClient_address
        
        
        /***********************************************************************
         * getClient_address_map : Obtiene la dirección del cliente (negocio) por id para el mapa
         * 
         * @date    May 15th, 2013
         * @author  Howser
         * @param   id 
         * @return  Cadena con la dirección
         **********************************************************************/
        public String getClient_address_map(String id){
        
            String res = "";
            try{
                Iterator<Anuncio> iterator = new AnuncioHelper().getAnuncioByID(id).iterator();
                while (iterator.hasNext()) {
                        Anuncio anuncio=iterator.next();
  
                        res = anuncio.getCalle()+" No."+anuncio.getNumero()+" "+anuncio.getColonia();
                }
                
            }catch(Exception ex){
                 System.out.println("ERROR : "+this.path+"getClient_address_map"+" (id="+id+")-> : "+ex);
            }

            return res;
            
        }//method getClient_address_map
        
        
        /***********************************************************************
         * getClient_hours : Obtiene el horario del cliente (negocio) por id
         * 
         * @date    May 15th, 2013
         * @author  Howser
         * @param   id 
         * @return  Cadena con el horario
         **********************************************************************/
        public String getClient_hours(String id){
        
            String res = "";
            try{
                Iterator<Anuncio> iterator = new AnuncioHelper().getAnuncioByID(id).iterator();
                while (iterator.hasNext()) {
                        Anuncio anuncio=iterator.next();
  
                        String hr = anuncio.getHorario();
                        
                        //Solo si existe horario lo coloca
                        if(hr!=null && hr.length()>0){
                            res+="<div class='horario_'>";
                            res+="  <img src='img/clock.png'/>";
			    res+="  <h3 class='contact_tag'>Horario</h3>";
			    res+="  <div class='horario_list'>";
                            res+=       anuncio.getHorario();
                            res+="  </div>";
                            res+="</div>";
                            
                        }
 
                         /*Debe quedar así*
                            <div class='horario_'>
                                <img src='img/clock.png'/>
                                <h3 class='contact_tag'>Horario</h3>
                                <div class='horario_list'></div>
                            </div>
                         */
                }
                
            }catch(Exception ex){
                 System.out.println("ERROR : "+this.path+"getClient_hours"+" (id="+id+")-> : "+ex);
            }

            return res;
            
        }//method getClient_hours
        
        
        /***********************************************************************
         * getClient_tels : Obtiene los teléfonos del cliente (negocio) por id
         * 
         * @date    May 16th, 2013
         * @author  Howser
         * @param   id 
         * @return  Cadena con los teléfonos
         **********************************************************************/
        public String getClient_tels(String id){
        
            String res = "";
            try{
                Iterator<Anuncio> iterator = new AnuncioHelper().getAnuncioByID(id).iterator();
                while (iterator.hasNext()) {
                        Anuncio anuncio=iterator.next();
  
                        res = listaTels_all(anuncio.getTelefono());
                }
                
            }catch(Exception ex){
                 System.out.println("ERROR : "+this.path+"getClient_tels"+" (id="+id+")-> : "+ex);
            }

            return res;
            
        }//method getClient_tels
        
        
        /***********************************************************************
         * getClient_email : Obtiene el email del cliente (negocio) por id
         * 
         * @date    May 15th, 2013
         * @author  Howser
         * @param   id 
         * @return  Cadena con el email
         **********************************************************************/
        public String getClient_email(String id){
        
            String res = "";
            String mail= "";
            try{
                Iterator<Anuncio> iterator = new AnuncioHelper().getAnuncioByID(id).iterator();
                while (iterator.hasNext()) {
                        Anuncio anuncio=iterator.next();
  
                        mail = anuncio.getEmail();
                        
                        //Solo si existe mail lo coloca
                        if(mail!=null && mail.length()>0){
                            res+= "<div class='email_'>";
                            res+= "     <img src='img/email.png'/>";
                            res+= "     <h3 class='contact_tag'>Email</h3>";
                            res+= "     <div class='email_list'>";
                            res+= "         <a href='mailto:"+mail+"'>";
                            res+=            mail;
                            res+= "         </a>";
                            res+= "     </div>";
                            res+= "</div>";
                        }
                        
                        /*Debe quedar así*
                         <div class='email_'>
                            <img src='img/email.png'/>
                            <h3 class='contact_tag'>Email</h3>
                            <div class='email_list'>
                                <a href='mailto:a@a.com'>a@a.com</a>
                            </div>
                         </div>
                         */
           
                }
                
            }catch(Exception ex){
                 System.out.println("ERROR : "+this.path+"getClient_email"+" (id="+id+")-> : "+ex);
            }

            return res;
            
        }//method getClient_email
        
        /***********************************************************************
         * getClient_web : Obtiene los enlances del cliente (negocio) por id
         * 
         * @date    May 15th, 2013
         * @author  Howser
         * @param   id 
         * @return  Cadena con los enlaces
         **********************************************************************/
        public String getClient_web(String id){
        
            String res = "";
            try{
                Iterator<Anuncio> iterator = new AnuncioHelper().getAnuncioByID(id).iterator();
                while (iterator.hasNext()) {
                        Anuncio anuncio=iterator.next();
  
                        res = listaWebs_icon(anuncio.getWww() + "");
                             
                }
                
            }catch(Exception ex){
                 System.out.println("ERROR : "+this.path+"getClient_web"+" (id="+id+")-> : "+ex);
            }

            return res;
            
        }//method getClient_web
        
        /***********************************************************************
         * getClient_logo : Obtiene el logo del cliente (negocio) por id
         * 
         * @date    May 16th, 2013
         * @author  Howser
         * @param   id 
         * @return  Cadena con el logo
         **********************************************************************/
        public String getClient_logo(String id){
        
            String res = "";
            try{
                Iterator<Anuncio> iterator = new AnuncioHelper().getAnuncioByID(id).iterator();
                while (iterator.hasNext()) {
                        Anuncio anuncio=iterator.next();
  
                        String imgPath = "http://directel.mx/content/img/logos/";
                        String img = anuncio.getLogo();
                        
                        if(img!=null && img.length()>0)
                            res = imgPath + anuncio.getLogo();
                        
                        //Si no existe imagen se pone el Logo de Directel
                        else
                            res = "css/images/logo_directel.png";
                             
                }
                
            }catch(Exception ex){
                 System.out.println("ERROR : "+this.path+"getClient_logo"+" (id="+id+")-> : "+ex);
            }

            return res;
            
        }//method getClient_logo
        
        
        /***********************************************************************
         * getClient_map : Obtiene el mapa del cliente (negocio) por id
         * 
         * @date    May 16th, 2013
         * @author  Howser
         * @param   id             Id del cliente
         * @param   displayString  Area disponible para desplegar
         * @return  Cadena con el mapa
         **********************************************************************/
        public String getClient_map(String id,String displayString){
        
            String res = "";
            try{
                Iterator<Anuncio> iterator = new AnuncioHelper().getAnuncioByID(id).iterator();
                while (iterator.hasNext()) {
                        Anuncio anuncio=iterator.next();
                        
                        //System.out.println("................." + displayString);
                        String urlCroquis ="";
                        String s=anuncio.getCoordenadas(); 
                        
                        double xsize_d = Double.parseDouble(displayString.split("x")[0]);
                        double ysize_d = Double.parseDouble(displayString.split("x")[1]);
                        
                        xsize_d=(xsize_d)*1.00; //cambia el ancho
                        ysize_d=(ysize_d)*0.30; //cambia el alto
                        
                        int xsize = (int)xsize_d;
                        int ysize = (int)ysize_d;
                        
                           if(anuncio.getCoordenadas().equals(null) || anuncio.getCoordenadas().length()==0)
                                 urlCroquis="<img id='img_pos'  src='img/notfound.jpg' width='500' height='500'/>";
                           else
                                 urlCroquis+="<div class='map_'>";
                                 urlCroquis+="  <a href='index.html#map_ext'>";
                                 urlCroquis+="      <img id='img_pos'  src='http://maps.googleapis.com/maps/api/staticmap?center="+s.substring(1,s.length()-1)+"&zoom=17&size="+   xsize+"x"+ysize   +"&markers=color:blue%7Clabel:S%7C"+s.substring(1,s.length()-1)+"&sensor=false' width='"+ xsize +"' height='"+ ysize +"'/>";
                                 urlCroquis+="  </a>";
                                 urlCroquis+="</div>";
                             
                           res = urlCroquis;
                           
                           /*Debe quedar así
                            <div class='map_'>
                               <a href='index.html#map_ext'>
				<img src='img/mapota.png'/>
                               </a>
			    </div>
                            */
                }
                
            }catch(Exception ex){
                 System.out.println("ERROR : "+this.path+"getClient_map"+" (id="+id+")-> : "+ex);
            }

            return res;
            
        }//method getClient_map
        
        
        /***********************************************************************
         * getClient_mapExt : Obtiene el mapa grande del cliente (negocio) por id
         * 
         * @date    May 16th, 2013
         * @author  Howser
         * @param   id             Id del cliente
         * @param   displayString  Area disponible para desplegar
         * @return  Cadena con el mapa
         **********************************************************************/
        public String getClient_mapExt(String id,String displayString){
        
            String res = ""; 
            try{
                Iterator<Anuncio> iterator = new AnuncioHelper().getAnuncioByID(id).iterator();
                while (iterator.hasNext()) {
                        Anuncio anuncio=iterator.next();
                        
                        //System.out.println("................." + displayString);
                        String urlCroquis ="";
                        String s=anuncio.getCoordenadas(); 
                        
                        double xsize_d = Double.parseDouble(displayString.split("x")[0]);
                        double ysize_d = Double.parseDouble(displayString.split("x")[1]);
                        
                        xsize_d=(xsize_d)*1.00; //cambia el ancho
                        ysize_d=(ysize_d)*1.00; //cambia el alto
                        
                        int xsize = (int)xsize_d;
                        int ysize = (int)ysize_d;
                        
                        
                           if(anuncio.getCoordenadas().equals(null) || anuncio.getCoordenadas().length()==0)
                                 urlCroquis="<img id='img_pos'  src='img/notfound.jpg' width='500' height='500'/>";
                           else
                                 urlCroquis+="<img id='img_pos'  src='http://maps.googleapis.com/maps/api/staticmap?center="+s.substring(1,s.length()-1)+"&zoom=17&size="+   xsize+"x"+ysize   +"&markers=color:blue%7Clabel:"+"S"+"%7C"+s.substring(1,s.length()-1)+"&sensor=false' width='"+ xsize +"' height='"+ ysize +"'/>";
                                 
                           res = urlCroquis;
                }
                
            }catch(Exception ex){
                 System.out.println("ERROR : "+this.path+"getClient_mapExt"+" (id="+id+")-> : "+ex);
            }

            return res;
            
        }//method getClient_mapExt
        
        
        /***********************************************************************
         * getClient_info : Obtiene toda la informacion del cliente (negocio) por id
         * 
         * @date    May 17th, 2013
         * @author  Howser
         * @param   id             Id del cliente
         * @param   displayString  Area disponible para desplegar
         * @return  Cadena con la informacion
         **********************************************************************/
        public String getClient_info(String id,String displayString){
        
            String res   = "";             //Resultados
            String client_logo    = "";       //Logotipo del cliente
            String client_name    = "";       //Nombre del cliente
            String client_desc    = "";       //Descripcion corta del cliente
            String client_addr    = "";       //Direccion del cliente
            String client_tels    = "";       //Telefonos del cliente
            String client_hours   = "";       //Horario del cliente
            String client_email   = "";       //Email del cliente
            String client_web     = "";       //Webs del cliente
            String client_map     = "";       //Mapa del cliente
            String client_map_ext = "";    //Mapa del cliente grande
            
            try{
                Iterator<Anuncio> iterator = new AnuncioHelper().getAnuncioByID(id).iterator();
                while (iterator.hasNext()) {
                        Anuncio anuncio=iterator.next();
  
                        client_logo = getClient_logo(id);    res+= client_logo  + "|";
                        client_name = getClient_name(id);    res+= client_name  + "|";
                        client_desc = getClient_desc(id);    res+= client_desc  + "|";
                        client_addr = getClient_address(id); res+= client_addr  + "|";
                        client_tels = getClient_tels(id);    res+= client_tels  + "|";
                        client_hours= getClient_hours(id);   res+= client_hours + "|";
                        client_email= getClient_email(id);   res+= client_email + "|";
                        client_web  = getClient_web(id);     res+= client_web   + "|";
                        
                        client_map      = getClient_map(id, displayString);    res+= client_map     + "|";
                        client_map_ext  = getClient_mapExt(id, displayString); res+= client_map_ext + "";
                             
                }
                
            }catch(Exception ex){
                 System.out.println("ERROR : "+this.path+"getClient_info"+" (id="+id+")-> : "+ex);
            }

            return res;
            
        }//method getClient_info
        
        
        /***********************************************************************
         * getVersion : Obtiene la version de la app
         * 
         * @date    May 21th, 2013
         * @author  Howser
         * @return  Cadena con la version
         **********************************************************************/
        public String getVersion(){
        
            return version + "|" + "2013";     
        }//method getVersion
}//class
