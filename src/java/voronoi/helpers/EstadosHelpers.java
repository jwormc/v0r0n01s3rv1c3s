/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi.helpers;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import voronoi.hibernate.HibernateUtil;
import voronoi.mappingpojos.Estados;

/**
 *
 * @author julio
 */
public class EstadosHelpers {
    
      Session session = null;

    public EstadosHelpers() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public List getEstados(){
    List<Estados> actorList = null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("from Estados");
        actorList = (List<Estados>) q.list();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return actorList;
}
    
    
}
