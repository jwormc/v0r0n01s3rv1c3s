/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi.tests;

/**
 *
 * @author Julio
 */
public class Contador extends Thread{
    public int numeroEntero=0;
    
    public Contador(){
    
    }
    
   @Override
   public void run(){
       while(numeroEntero<1000){
           try{
           System.out.println(""+numeroEntero);
           sleep(1000);
           numeroEntero++;
           }catch(InterruptedException e){
               System.out.println("hubo pedos!! " + e.toString());
           }
       }
       
   }
    
}
