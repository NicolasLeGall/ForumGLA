package fr.istic.gla.tp.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.istic.gla.tp.beans.Utilisateur;

public class RestrictionFilterAdminOper implements Filter {


    public void init( FilterConfig config ) throws ServletException {
    }

    public void doFilter( ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException,
            ServletException {
        /* Cast des objets request et response */
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /**
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connecté.
         */
        Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
        //on applique le filtre au moderateur et administrateur
        if (user.getProfil().equals("moderateur") || user.getProfil().equals("administrateur")) {
            /* Affichage de la page restreinte */
            chain.doFilter( request, response );
     
        } else {
            /* Redirection vers la page publique */
            response.sendRedirect( request.getContextPath() + "/accesrefuser.jsp" );
        }
    }

    public void destroy() {
    }
}