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
    
    public List getAnuncioByID(String id){
    List<Anuncio> anuncios = null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        System.out.println("--->" + id);
        Query q = session.createQuery ("from Anuncio where id="+id);
        anuncios = (List<Anuncio>) q.list();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return anuncios;
}
    
    
public List getAnunciosByREGEX(String cadena){
    List<Anuncio> anuncios = null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        //antes  Query q = session.createQuery ("from Anuncio where id < 100 AND  etiquetas like '%"+cadena+"%'  ");
        Query q = session.createQuery ("from Anuncio where id<1000 AND etiquetas like '%"+cadena+"%'");
        anuncios = (List<Anuncio>) q.list();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return anuncios;
}
        
    /***********************************************************************
      * getAnunciosByKeywords : Regresa la Lista de Anuncios que coincidan con alguna
      *                         de las etiquetas de las secciones y que correspondan a la ciudad
      * 
      * @date    May 31th, 2013
      * @param   keywords   Etiquetas
      * @param   city       Id de la Ciudad
      * @author  Howser
      * @return  Lista con los anuncios
      **********************************************************************/
public List getAnunciosByKeywords(String keywords, String city){
    List<Anuncio> anuncios = null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        
        /*
         SELECT *
         from anuncio
         where REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(UPPER(etiquetas),'Á','A'),'É','E'),'Í','I'),'Ó','O'),'Ú','U') LIKE '%PÚBLICA%'
         */
        String[] tags = keywords.split("\\|");
        
        String q = "FROM Anuncio WHERE ciudad="+city+" AND (";
        
        //Se agrega cada eqtiqueta
        for(int i=0;i<tags.length;i++){
            q+=" REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(UPPER(etiquetas),'Á','A'),'É','E'),'Í','I'),'Ó','O'),'Ú','U') LIKE '%"+tags[i]+"%' OR";
        }//for
        
        q = q.substring(0, q.length()-2); //Elimina ultimo OR
        q+=")";                           //Cierra condicion AND
        
        
        Query q_ = session.createQuery (q);
        anuncios = (List<Anuncio>) q_.list();

    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return anuncios;
}//method getAnunciosByKeywords


    /***********************************************************************
      * getAnunciosSearch : Regresa la Lista de Anuncios que coincidan con alguna
      *                     una palabra clave de busqueda en el nombre
      * 
      * @date    May 31th, 2013
      * @param   keyword   Palabra clave
      * @param   city      Id de la Ciudad
      * @author  Howser
      * @return  Lista con los anuncios
      **********************************************************************/

public List getAnunciosSearch(String keyword, String city){
    List<Anuncio> anuncios = null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        /*
         SELECT *
         from anuncio
         where ciudad=1 AND REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(UPPER(nombre),'Á','A'),'É','E'),'Í','I'),'Ó','O'),'Ú','U') LIKE '%periquin%'
         */
         
         String q = "FROM Anuncio WHERE ciudad="+city+" AND ";
         q+=" REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(UPPER(nombre),'Á','A'),'É','E'),'Í','I'),'Ó','O'),'Ú','U') LIKE '%"+keyword.toUpperCase()+"%'";
         
         Query q_ = session.createQuery (q);
         anuncios = (List<Anuncio>) q_.list();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return anuncios;
}//method getAnunciosSearch

public List getAnunciosBusqueda(String cadena){
    List<Anuncio> anuncios = null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        //antes  Query q = session.createQuery ("from Anuncio where id < 100 AND  etiquetas like '%"+cadena+"%'  ");
        Query q = session.createQuery ("from Anuncio where id<1000 AND nombre like '%"+cadena+"%'");
        anuncios = (List<Anuncio>) q.list();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return anuncios;
}
      
        
        
            
public List getAnunciosFavoritos(String ids){
    List<Anuncio> anuncios = null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        //antes  Query q = session.createQuery ("from Anuncio where id < 100 AND  etiquetas like '%"+cadena+"%'  ");
        Query q = session.createQuery ("from Anuncio where id in ("+ids+")");
        anuncios = (List<Anuncio>) q.list();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return anuncios;
}
    
}
