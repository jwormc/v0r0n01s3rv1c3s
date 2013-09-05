/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi.tests;

/**
 *
 * @author Julio
 */
public class Impresora extends Thread{
    Almacenamiento a;

 
    public Impresora(){
    
    }
    
    @Override
    public void run(){
        int ant=0;
        
        while(true){
        
            try{
                
                if(ant!=a.getNumeroEntero()){
                System.out.println("almacenamiento-->numeroEntero="+ a.getNumeroEntero());
                ant=a.getNumeroEntero();
                }else
                System.out.println("---no ha cambiado---");
                
                sleep(100);
            }catch(InterruptedException e){
                System.out.println(""+e.toString());
            }
        
        }
    
    
    }
    
    public Almacenamiento getA() {
        return a;
    }

    public void setA(Almacenamiento a) {
        this.a = a;
    }
      
    
}
