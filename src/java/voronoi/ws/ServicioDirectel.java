/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi.ws;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import voronoi.helpers.AnuncioHelper;
import voronoi.mappingpojos.Anuncio;

/**
 *
 * @author julio
 */
@WebService(serviceName = "ServicioDirectel")
public class ServicioDirectel {
 
    /**
     * This is a sample web service operation
     */ 
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    
    
     /**
     * operacion por id devuelve anuncio
     */ 
    @WebMethod(operationName = "anunciobyid")
    public String getAnuncioById(@WebParam(name = "id") String id) {
           List<Anuncio> anuncio=new AnuncioHelper().getAnuncioByID(1);
           System.out.println("regresando id desde el WS " + anuncio.get(anuncio.size()-1).getId());
        
        return "Hello  id => " + anuncio.get(anuncio.size()-1).getId() + "  !";
    }
    
}
