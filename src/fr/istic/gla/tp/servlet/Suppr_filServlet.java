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

public class Suppr_filServlet extends HttpServlet {

	/**
	 * {@inheritDoc}
	 */
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//on a l'id de la fil qu'on doit supprimer
		int id_fil = Integer.parseInt(req.getParameter("id_fil"));
		System.out.println("id_fil "+id_fil);
		Configuration configuration = new Configuration();
		configuration.setProperty(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create");
		SessionFactory factory = configuration.configure("/ressources/hibernate.cfg.xml").buildSessionFactory();
		Session session  = factory.openSession();
		 
        Transaction tx = null;
        Utilisateur user = null;
		//on fait la requete SQL qui supprime la discussion pour l'id données
        try {
            tx = session.getTransaction();
            
            tx.begin();
            Query query = session.createQuery("DELETE FROM FilDiscussion where id = ?  ");

            query.setParameter(0, id_fil);
            query.executeUpdate();
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            System.out.println(e);
        } finally {
            session.close();
        }

		//req.setAttribute("id", id_fil);
		req.getRequestDispatcher("/accueil").forward(req, resp);
		
		
		
	}

	/**
	 * {@inheritDoc}
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		
	}

}
