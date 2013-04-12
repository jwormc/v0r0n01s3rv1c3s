/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi.helpers;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import voronoi.hibernate.HibernateUtil;
import voronoi.mappingpojos.Anuncio;

/**
 *
 * @author julio
 */
public class AnuncioHelper {
    
      Session session = null;

    public AnuncioHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public List getAnuncioByID(int id){
    List<Anuncio> actorList = null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("from Anuncio where id="+id);
        actorList = (List<Anuncio>) q.list();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return actorList;
}
    
    
}
