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
        
        String param2=request.getParameter("param2");  //  id normalmente
       
        try {
        
            
           switch(tipo){
               case 1:     
                   out.println(new GenereitorHTML().getEstadosListLi());
                   System.out.println("peticion de servicio 1 lista de estados OK!");
                   break;
              
              case 2:     
                  out.println(new GenereitorHTML().getAnunciosRegExLi());
                   System.out.println("peticion de servicio 2 lista de Anuncios REGEX OK!");
                   break;
                  
             case 3:     
                   out.println(new GenereitorHTML().getAnuncioById(param2));
                   System.out.println("peticion de servicio 3 obten anuncio por ID!");
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