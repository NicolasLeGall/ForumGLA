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

/**
 * Servlet vide permettant uniquement d'afficher la page d'accueil en passant la main à la JSP accueil.jsp.
 * 
 * @author yves
 *
 */
public class FilServlet extends HttpServlet {

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
		int id = Integer.parseInt(req.getParameter("id"));
		System.out.println("l'id c'est" +req.getParameter("id"));


		Configuration configuration = new Configuration();
		SessionFactory factory = configuration.configure("/ressources/hibernate.cfg.xml").buildSessionFactory();
		Session session  = factory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Message.class);
		//on récupére toute les infos pour l'id de discussion données
		criteria.add(Restrictions.eq("idDiscussion", id));
		
		List<Message> messages = criteria.list();
		for(Message message : messages){
		//	System.out.println(filD.getId());
			System.out.println(message.getTexte());
			System.out.println(message.getAuteur());
			
		}
		req.setAttribute("message", messages);
		req.setAttribute("id", id);
		req.getRequestDispatcher("/fil.jsp").forward(req, resp);
		
	}

}
	