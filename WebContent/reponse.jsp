<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<!-- Directive chargeant le bean de l'utilisateur en session -->
<jsp:useBean id="utilisateur" class="fr.istic.gla.tp.beans.Utilisateur" scope="session"/>


<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
    </head>
    <body>
        <div class="header">
            <h1>Forum de discussion TP GLA</h1>
        </div>
        <div class="userbox">
            <h1>Connecté en tant que : ${utilisateur.login} (${utilisateur.profil})</h1>
        </div>
        <div class="menu">
        	<p>
        		<a href="http://localhost:8080/ForumGLA/accueil">Accueil</a><br>
	            <a href="http://localhost:8080/ForumGLA/authentification">Connexion</a><br>
	            <a href="http://localhost:8080/ForumGLA/profil?login=${utilisateur.login}">profil</a><br>
	            <a href="http://localhost:8080/ForumGLA/resetDatabase">Reset DataBase(ajout de discussion)</a><br>
            </p>
        </div>
        <div class="content">
        
        	<p>Ecrivez votre reponse </p>
        	
		    <form name="Name_repondre" method="post" action="reponse_traitement">
		    <textarea name="textarea" rows=4 cols=40 maxlength="200"></textarea>
		    <input type="hidden" name="id_fil" value="${id}"><br>
		    <input type="submit" value="Envoyer">
		    </form>
			
			
        </div>
    </body>
</html>
