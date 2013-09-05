/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi.tests;

/**
 *
 * @author Julio
 */
public class Almacenamiento {
    
    static int numeroEntero=0;
    static Contador c=new Contador();
    static Impresora i=new Impresora();
  
    public int getNumeroEntero() {
        numeroEntero=c.numeroEntero;  //Contador aumenta el valor de la variable numeroEntero
                                      //cada 1 s y se la asigna a la clase Almacenamiento
        return numeroEntero;
    }

    public void setNumeroEntero(int numeroEntero) {
        this.numeroEntero = numeroEntero;
    }
    
    public Almacenamiento(){
    
        c.start(); //Arranca el proceso de Contador.. aumenta cada segundo
        i.setA(this);  //envamos el objeto almacenamiento
        i.start(); // Arranca el proceso impresion.. imprime cada 100 ms, por lo que cuando no cambia el valor 
                   // en el almacenamiento nos dice que no ha cambiado.
    }
    
    
    
    public static void main(String[] args) {
       new Almacenamiento();
    }
    
    
}
