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
import org.hibernate.query.Query;

import fr.istic.gla.tp.beans.FilDiscussion;
import fr.istic.gla.tp.beans.Utilisateur;

/**
 * Servlet vide permettant uniquement d'afficher la page d'accueil en passant la main à la JSP accueil.jsp.
 * 
 * @author yves
 *
 */
public class AccueilServlet extends HttpServlet {

	/**
	 * {@inheritDoc}
	 */
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		
		
		
	}

	/**
	 * {@inheritDoc}
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("test2");

		

		Configuration configuration = new Configuration();
		SessionFactory factory = configuration.configure("/ressources/hibernate.cfg.xml").buildSessionFactory();
		Session session  = factory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(FilDiscussion.class);
		//criteria.add(Restrictions.eq("login", username));
		
		List<FilDiscussion> filDs = criteria.list();
		for(FilDiscussion filD : filDs){
		//	System.out.println(filD.getId());
			System.out.println(filD.getNom());
			
		}
		req.setAttribute("filD", filDs);
		req.getRequestDispatcher("/accueil.jsp").forward(req, resp);
		
	}

}
	