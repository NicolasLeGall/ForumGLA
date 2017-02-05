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

import fr.istic.gla.tp.beans.FilDiscussion;
import fr.istic.gla.tp.beans.Message;
import fr.istic.gla.tp.beans.Utilisateur;

public class CreerDiscussionServlet extends HttpServlet {

	/**
	 * {@inheritDoc}
	 */
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		
		Configuration configuration = new Configuration();
		configuration.setProperty(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create");
		SessionFactory factory = configuration.configure("/ressources/hibernate.cfg.xml").buildSessionFactory();
		Session session  = factory.openSession();
		Transaction tx = session.beginTransaction();

		try {
			//on met en bdd le nom de la nouvelle discussion
			FilDiscussion fildiscussion = new FilDiscussion();
			fildiscussion.setNom(name);
			session.save(fildiscussion);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		
		
		req.getRequestDispatcher("/accueil").forward(req, resp);
		
		
		
	}

	/**
	 * {@inheritDoc}
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		
	}

}
