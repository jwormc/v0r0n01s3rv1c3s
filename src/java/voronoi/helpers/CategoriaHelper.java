/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi.helpers;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import voronoi.hibernate.HibernateUtil;
import voronoi.mappingpojos.Categoria;

/**
 *
 * @author julio
 */
public class CategoriaHelper {
    
      Session session = null;

    public CategoriaHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public List getCategorias(){
    List<Categoria> actorList = null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("from Categoria");
        actorList = (List<Categoria>) q.list();

        } catch (Exception e) {
          e.printStackTrace();
        }

    return actorList;
}
    
    
}//class

