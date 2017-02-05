package fr.istic.gla.tp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import fr.istic.gla.tp.beans.FilDiscussion;
import fr.istic.gla.tp.beans.Message;
import fr.istic.gla.tp.beans.Utilisateur;

/**
 * Servlet d'initialisation de la base de données.
 * 
 * @author yves
 *
 */
public class ResetDatabaseServlet extends HttpServlet {

	/**
	 * Tableau représentant les utilisateurs. Il sera utilisé pour construire la Map.
	 * 
	 */
	// le SEL est sencer etre long et aléatoire. Mais pour me simplifier la vie il ne l'ai pas
	String[][] UTILISATEURS = {
			{ "pierre", "36521945a8db25d1e597592b280e3bba47b05c783a8cbd349bec10006f469a7e", "utilisateur", "salt1" },
			{ "paul", "19de3fff460f0721f2aaf7b808c0d561b748a63446b76f86ec1ee1059e56a2e3", "utilisateur", "salt2"  },
			{ "jacques", "e00c580725bcda159450ac335b8db9591d69e5bc131dbfe55ef8c3c497ef0c19", "moderateur", "salt3"  },
			{ "admin", "037ff4f56ea385d53f203232531d911517e4b75899b47ece7515c2a42b1eb0df", "administrateur", "salt4"  }
	};
	String[][] FILDISCUSSIONS = {
			{"Discussion1"},
			{"Discussion2"},
			{"Discussion3"},
			{"Discussion4"}
	};
	String[][] MESSAGES = {
			{"pierre", "bonjour", "1" },
			{"pierre", "sa va ?", "1" },
			{"pierre", "oui et toi", "1" },
			{"paul", "non","2" }
	};
	

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Configuration configuration = new Configuration();
		configuration.setProperty(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create");
		SessionFactory factory = configuration.configure("/ressources/hibernate.cfg.xml").buildSessionFactory();
		Session session  = factory.openSession();
		Transaction tx = session.beginTransaction();

		//supprime tout les utilisateurs
       /* try {
           
           
            Query query = session.createQuery("DELETE FROM Utilisateur ");

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
		*/
		
		// ajoute dans le bdd les donnée dans les variable du dessus
		try {
			for (String[] UTILISATEUR : UTILISATEURS) {
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setLogin(UTILISATEUR[0]);
				utilisateur.setPassword(UTILISATEUR[1]);
				utilisateur.setProfil(UTILISATEUR[2]);
				utilisateur.setSalt(UTILISATEUR[3]);
				session.save(utilisateur);
			}
			
			for (String[] FILDISCUSSION : FILDISCUSSIONS) {
				FilDiscussion fildiscussion = new FilDiscussion();
				fildiscussion.setNom(FILDISCUSSION[0]);
				session.save(fildiscussion);
			}
			
			for (String[] MESSAGE : MESSAGES) {
				Message msg = new Message();
				msg.setAuteur(MESSAGE[0]);
				msg.setTexte(MESSAGE[1]);
				msg.setIdDiscussion(Integer.parseInt(MESSAGE[2]));
				session.save(msg);
			}

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		req.getRequestDispatcher("/resetDatabase.jsp").forward(req, resp);
	}

}
