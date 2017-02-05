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
public class Reponse_traitementServlet extends HttpServlet {

	/**
	 * {@inheritDoc}
	 */
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//on récupére le texte du champ de saisie
		String textarea = req.getParameter("textarea");
		int id_fil = Integer.parseInt(req.getParameter("id_fil"));
		Utilisateur user = (Utilisateur)req.getSession().getAttribute("utilisateur");
		
		Configuration configuration = new Configuration();
		configuration.setProperty(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create");
		SessionFactory factory = configuration.configure("/ressources/hibernate.cfg.xml").buildSessionFactory();
		Session session  = factory.openSession();
		Transaction tx = session.beginTransaction();
// on insere les donenr dans la base
		try {
			
			Message msg = new Message();
			msg.setAuteur(user.getLogin());
			msg.setIdDiscussion(id_fil);
			msg.setTexte(textarea);
			session.save(msg);
		
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		// on récupére la discssuion pour l'affciher
		Criteria criteria = session.createCriteria(Message.class);
		criteria.add(Restrictions.eq("idDiscussion", id_fil));
		
		List<Message> messages = criteria.list();
		for(Message message : messages){
		//	System.out.println(filD.getId());
			System.out.println(message.getTexte());
			System.out.println(message.getAuteur());
			
		}
		req.setAttribute("message", messages);
		req.setAttribute("id", id_fil);
		
		
		req.getRequestDispatcher("/fil.jsp").forward(req, resp);
		
	}

	/**
	 * {@inheritDoc}
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	

		
	}

}
	