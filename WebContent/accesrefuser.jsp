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
	            <a href="">Déconnexion</a><br>
	            <a href="">Créer un fil</a><br>
	            <a href="http://localhost:8080/ForumGLA/resetDatabase">Reset DataBase</a><br>
            </p>
        </div>
        <div class="content">
        
			<h2>Accées refuser</h2>

			
        </div>
    </body>
</html>
