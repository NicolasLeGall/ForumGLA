package fr.istic.gla.tp.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import fr.istic.gla.tp.beans.Utilisateur;

public class AuthentificationServlet  extends HttpServlet {
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "unused", "deprecation" })

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//doGet(req, resp);
		
		String username = req.getParameter("name");
		String password = req.getParameter("password");

		System.out.println("username is: " + username);
		System.out.println("password is: " + password);

		Configuration configuration = new Configuration();
		SessionFactory factory = configuration.configure("ressources/hibernate.cfg.xml").buildSessionFactory();
		Session session  = factory.openSession();
	 
        Transaction tx = null;
        Utilisateur user = null;
		//on récupére en BDD le hash pour le login donnée dans le champs de saisie
        try {
            tx = session.getTransaction();
            
            tx.begin();
            Query query = session.createQuery("FROM Utilisateur where login = ? ");

            query.setParameter(0, username);
            
            query.setMaxResults(1);//limite le nb de ligne de la repionse
            user = (Utilisateur)query.uniqueResult();
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

		MessageDigest md = null;
		try {
			// le mieu est d'utiliser autre chose que MD5 ou SHA-1
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Utilisateur userFromDB =authentification(username, hashconvertie);
		//on a le password saisie dans le champ de saisie et on ajoute le salt a la fin
		String tamp = password+user.getSalt();
		System.out.println("chaine qu'on hash 256: "+tamp);
		byte[] hash = md.digest(tamp.getBytes());
		//on hash l'ensemble
		String hashconvertie = DatatypeConverter.printHexBinary(hash);
		hashconvertie = hashconvertie.toLowerCase();
		System.out.println("voici le hash : "+hashconvertie);

		String mdp_bdd = user.getPassword();
		String mdp_recu = hashconvertie;
		System.out.println("voici le hash en BDD: "+mdp_bdd);
		//on compare le hash de la bdd avec celui qu'on a crée a partir du mdp du champ de saisie
		if(user != null && user.getLogin().equals(username) && mdp_bdd.equals(mdp_recu)){
			System.out.println("true");
			//on l'authentifie a une session
			req.getSession().setAttribute("utilisateur", user);
			req.getRequestDispatcher("/accueil").forward(req, resp);
		}else{
			//il doit essayer un autre mdp
			System.out.println("false");
			req.getRequestDispatcher("/authentification.jsp").forward(req, resp);
		} 
	}

	
	/**
	 * {@inheritDoc}
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/authentification.jsp").forward(req, resp);
	}

}
