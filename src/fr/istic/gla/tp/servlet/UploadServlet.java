package fr.istic.gla.tp.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
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
public class UploadServlet extends HttpServlet {

	/**
	 * {@inheritDoc}
	 */
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//doGet(req, resp);
		File fd;
		String name;
		String fieldname;
		System.out.println("On est dans upload");
		
		Configuration configuration = new Configuration();
		configuration.setProperty(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create");
		SessionFactory factory = configuration.configure("/ressources/hibernate.cfg.xml").buildSessionFactory();
		Session session  = factory.openSession();
		Transaction tx = session.beginTransaction();

		Utilisateur user = (Utilisateur)req.getSession().getAttribute("utilisateur");
		FileItemFactory filefactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(filefactory);
		
		
		try {
			List<FileItem> items = upload.parseRequest(new ServletRequestContext(req));
			for (FileItem items1 : items) {
				//on fait rien si le fichier est plus grand que 8000000 bits
				if(items1.getSize()<8000000){
					//name = items1.getName();
					//on modifie le nom de l'image
					name = "avatar_de_"+user.getLogin()+".jpg";
					fieldname = items1.getFieldName();
					//chemin depuis ou est installer eclipse vers ou on veux l'installer
					fd = new File("../../workspace/ForumGLA/WebContent/fichiers/"+name);
					//on écrit dans le fichier
					items1.write(fd);
					System.out.println("name "+name+" fieldname "+fieldname);
					user.setAvatar(name);
					System.out.println("user.getavatar: "+user.getAvatar());

					//on met a jour l'avatar dans la bdd (il contient le nom de l'avatar)
		            Query query = session.createQuery("Update FROM Utilisateur SET avatar = ? where id = ?  ");

		            query.setParameter(0, name);
		            query.setParameter(1, user.getId());
		            query.executeUpdate();
		            tx.commit();
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception: "+e);
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Exception: "+e);
		}
		
		System.out.println("fin upload");
		req.getRequestDispatcher("/profil.jsp").forward(req, resp);
		
	}

	/**
	 * {@inheritDoc}
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/upload.jsp").forward(req, resp);
	}
}
	