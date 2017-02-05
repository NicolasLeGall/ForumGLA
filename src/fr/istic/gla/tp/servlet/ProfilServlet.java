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
public class ProfilServlet extends HttpServlet {

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
		
		
		String paramAuteur = req.getParameter( "login" );
		System.out.println("test2"+paramAuteur);
		req.getRequestDispatcher("/profil.jsp").forward(req, resp);
		
	}

}
	