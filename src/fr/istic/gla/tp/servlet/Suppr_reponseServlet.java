package fr.istic.gla.tp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import fr.istic.gla.tp.beans.FilDiscussion;
import fr.istic.gla.tp.beans.Message;
import fr.istic.gla.tp.beans.Utilisateur;

public class Suppr_reponseServlet extends HttpServlet {

	/**
	 * {@inheritDoc}
	 */
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//on récupére l'id du fil et de la discussion
		int id_fil = Integer.parseInt(req.getParameter("id_fil"));
		int id_message = Integer.parseInt(req.getParameter("id_message"));
		System.out.println("id_fil "+id_fil+" id_message "+id_message);
		
		Configuration configuration = new Configuration();
		configuration.setProperty(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create");
		SessionFactory factory = configuration.configure("/ressources/hibernate.cfg.xml").buildSessionFactory();
		Session session  = factory.openSession();
		 
        Transaction tx = null;
        Utilisateur user = null;
		//on supprime avec une requete SQL la réponse 
        try {
            tx = session.getTransaction();
            
            tx.begin();
            Query query = session.createQuery("DELETE FROM Message where id = ? ");

            query.setParameter(0, id_message);
            query.executeUpdate();
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println("test"+id_fil);
		req.setAttribute("id", id_fil);
		 System.out.println("test2"+id_fil);
		req.getRequestDispatcher("/fil?id="+id_fil).forward(req, resp);	
		 System.out.println("test3"+id_fil);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		
		
	}

}
