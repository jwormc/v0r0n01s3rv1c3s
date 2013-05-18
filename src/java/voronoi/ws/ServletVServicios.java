/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi.ws;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author julio
 */
@WebServlet(name = "ServletVServicios", urlPatterns = {"/ServletVServicios"})
public class ServletVServicios extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
        
        String tipoS=request.getParameter("tipoServicio");
        int tipo=0;
        if(tipoS.length() > 0)
        tipo=Integer.parseInt(request.getParameter("tipoServicio")); 
        
        String anyparam=request.getParameter("anyparam");  //  id normalmente
        String displayString="";
        
        if(request.getParameter("displaysize").length() > 0)
        displayString=request.getParameter("displaysize");
       
        
        try {
        
            
           switch(tipo){
             case 1:     
                   System.out.println("atendiendo peticion Servicio 1...");
                   out.println(new GenereitorHTML().getEstadosListLi());
                   System.out.println("peticion de servicio 1 lista de estados OK!");
                   break;
              
             case 2:     
                   System.out.println("atendiendo peticion Servicio 2...   regex=-->" + anyparam);
                   out.println(new GenereitorHTML().getAnunciosRegExLi(anyparam));
                   System.out.println("peticion de servicio 2 lista de Anuncios REGEX OK!");
                   break;
                  
             case 3:     
                   System.out.println("atendiendo peticion Servicio 3...  param2=-->" + anyparam);
                   out.println(new GenereitorHTML().getAnuncioById(anyparam,displayString));
                   System.out.println("peticion de servicio 3 obten anuncio por ID ok!");
                   break;
                 
             case 4:     
                   System.out.println("atendiendo peticion Servicio 4...  param2=-->" + anyparam);
                   out.println(new GenereitorHTML().getFavoritosByIds(anyparam));
                   System.out.println("peticion de servicio 3 obten Favoritos por IDs ok!");
                   break;
                 
             case 10:
                   System.out.println("atendiendo peticion Servicio 10...  Info_Cliente=-->" + anyparam);
                   out.println(new GenereitorHTML().getClient_info(anyparam,displayString));
                   System.out.println("peticion de servicio 10 ok!");
                   break;
                 
             case 11:
                   System.out.println("atendiendo peticion Servicio 11...  Nombre_Cliente=-->" + anyparam);
                   out.println(new GenereitorHTML().getClient_name(anyparam));
                   System.out.println("peticion de servicio 11 ok!");
                   break;
                 
             case 12:
                   System.out.println("atendiendo peticion Servicio 12...  Direccion_Cliente=-->" + anyparam);
                   out.println(new GenereitorHTML().getClient_address(anyparam));
                   System.out.println("peticion de servicio 12 ok!");
                   break;
                 
             case 13:
                   System.out.println("atendiendo peticion Servicio 13...  Telefonos_Cliente=-->" + anyparam);
                   out.println(new GenereitorHTML().getClient_tels(anyparam));
                   System.out.println("peticion de servicio 13 ok!");
                   break;
                 
             case 14:
                   System.out.println("atendiendo peticion Servicio 14...  Horario_Cliente=-->" + anyparam);
                   out.println(new GenereitorHTML().getClient_hours(anyparam));
                   System.out.println("peticion de servicio 14 ok!");
                   break;
                 
                 
             case 15:
                   System.out.println("atendiendo peticion Servicio 15...  Email_Cliente=-->" + anyparam);
                   out.println(new GenereitorHTML().getClient_email(anyparam));
                   System.out.println("peticion de servicio 15 ok!");
                   break;
                 
                 
             case 16:
                   System.out.println("atendiendo peticion Servicio 16...  Web_Cliente=-->" + anyparam);
                   out.println(new GenereitorHTML().getClient_web(anyparam));
                   System.out.println("peticion de servicio 16 ok!");
                   break;
                 
                              
             case 17:
                   System.out.println("atendiendo peticion Servicio 17...  Logo_Cliente=-->" + anyparam);
                   out.println(new GenereitorHTML().getClient_logo(anyparam));
                   System.out.println("peticion de servicio 17 ok!");
                   break;
                 
             case 18:
                   System.out.println("atendiendo peticion Servicio 18...  Mapa_Cliente=-->" + anyparam);
                   out.println(new GenereitorHTML().getClient_map(anyparam,displayString));
                   System.out.println("peticion de servicio 18 ok!");
                   break;
             
            case 5:     
                   System.out.println("atendiendo peticion Servicio 5...  param2=-->" + anyparam);
                   out.println(new GenereitorHTML().getAnunciosBuscar(anyparam));
                   System.out.println("peticion de servicio 3 obten Favoritos por IDs ok!");
                   break;
                
                
              default:
                  out.println("peticion de servicio desconosido.");
                  System.out.println("peticion de servicio desconosido.");
                  break;
           
           }
        
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
