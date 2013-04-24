/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi.tests;


import java.util.ArrayList;
import java.util.List;
import voronoi.helpers.AnuncioHelper;
import voronoi.mappingpojos.Anuncio;
import voronoi.mappingpojos.Estados;

/**
 *
 * @author julio
 */




public class Pruebas {
    
    public  static void main(String args[]){
      String s="website,http://www.antenaradio.mx;facebook,http://www.facebook.com/antnaradio.mx;twitter,https://twitter.com/antenamx;google,";
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
            
        finalString+="<li><a href=\" " + sociales.get(i) + "\"> <h2>el feis</h2></a></li>";
           System.out.println(finalString); 
        }
        
  
    }


 public  String  getTels(String s){

  String[] ss=s.split(" ");
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
        finalString+="<li><a href=\""+tels.get(ii).split(":")[1]+":"+tels.get(ii).split(":")[2]+"\">"+tels.get(ii).split(":")[0]+":"+tels.get(ii).split(":")[2]+"</a></li>";
        }
       
       
       
        return finalString;
    }



}
