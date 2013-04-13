/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import voronoi.ws_client.ServicioDirectel;

/**
 * REST Web Service
 *
 * @author julio
 */
@Path("serviciodirectelport")
public class ServicioDirectelPort {
    private ServicioDirectel port;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicioDirectelPort
     */
    public ServicioDirectelPort() {
        port = getPort();
    }

    /**
     * Invokes the SOAP method anunciobyid
     * @param id resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("anunciobyid/")
    public String getAnunciobyid(@QueryParam("id") String id) {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.lang.String result = port.anunciobyid(id);
                return result;
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method hello
     * @param name resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("hello/")
    public String getHello(@QueryParam("name") String name) {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.lang.String result = port.hello(name);
                return result;
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     *
     */
    private ServicioDirectel getPort() {
        try {
            // Call Web Service Operation
            voronoi.ws_client.ServicioDirectel_Service service = new voronoi.ws_client.ServicioDirectel_Service();
            voronoi.ws_client.ServicioDirectel p = service.getServicioDirectelPort();
            return p;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }
}
