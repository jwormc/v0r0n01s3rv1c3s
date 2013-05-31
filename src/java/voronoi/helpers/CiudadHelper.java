/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi.helpers;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import voronoi.hibernate.HibernateUtil;
import voronoi.mappingpojos.Ciudad;

/**
 *
 * @author julio
 */
public class CiudadHelper {
    
      Session session = null;

    public CiudadHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public List getCiudades(){
    List<Ciudad> actorList = null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("from Ciudad");
        actorList = (List<Ciudad>) q.list();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return actorList;
}
    
    
}
