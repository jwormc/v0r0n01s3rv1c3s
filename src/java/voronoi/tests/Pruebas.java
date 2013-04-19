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
        
  String s="Local,444,842 66 85,;Celular,444,113 91 83,;Nextel,444,266 16 41,;Nextel ID,,92*11*31858,;01 800,01800,714 81 40,;FAX,444,822 30 26,";
  
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
         
  
    }
}
