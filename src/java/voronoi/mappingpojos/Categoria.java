package voronoi.mappingpojos;
// Generated 15-abr-2013 14:16:16 by Hibernate Tools 3.2.1.GA

/**
 * Ciudad generated by Howser
 */
public class Categoria implements java.io.Serializable {
    
     private int id;
     private int parent;
     private String nombre;
     private String url;
     private String imagen;
     private String icono;
     private String secciones;
     private int clics;

    public Categoria() {
        
    }//constructor vacio

    public Categoria(int id, int parent,  String nombre, String url, String imagen, String icono, String secciones, int clics) {
       this.id        = id;
       this.parent    = parent;
       this.nombre    = nombre;
       this.url       = url;
       this.imagen    = imagen;
       this.icono     = icono;
       this.secciones = secciones;
       this.clics     = clics;
    }//constrcutor de copia
   
    public int  getId() {return this.id;}
    public void setId(int id) {this.id = id;}
    
    public int  getParent() {return this.parent;}
    public void setParent(int parent) {this.parent = parent;}
    
    public String  getNombre() {return this.nombre;}
    public void    setNombre(String nombre) {this.nombre = nombre;}
     
    public String  getUrl() {return this.url;}
    public void    setUrl(String url) {this.url = url;}
    
    public String  getImagen() {return this.imagen;}
    public void    setImagen(String imagen) {this.imagen = imagen;}
    
    public String  getIcono() {return this.icono;}
    public void    setIcono(String icono) {this.icono = icono;}
    
    public String  getSecciones() {return this.secciones;}
    public void    setSecciones(String secciones) {this.secciones = secciones;}
    
    public int  getClics() {return this.clics;}
    public void setClics(int clics) {this.clics = clics;}
    
    
   

}//class
