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
            <h1>Forum de discussion TP GLA Nicolas Le Gall et Philippe Fabian</h1>
        </div>
        <div class="userbox">
            <h1>Connecté en tant que : <c:out value = "${utilisateur.login}"/> <c:out value = "(${utilisateur.profil})"/></h1>
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
        
        	<p>A rendre pour le 5 février minuit</p>
        	
        	<c:forEach items="${filD}" var="fD" >
        	 <a href="http://localhost:8080/ForumGLA/fil?id=${fD.id}"> <c:out value = "${fD.nom}"/> </a></br>
			</c:forEach>
			
			
        	 <form name="loginForm" method="post" action="creerDiscussion">
			  Nom discussion: <input type="text" name="name" maxlength="20">
			   <input type="submit" value="créer discussion">
			</form>
        </div>
    </body>
</html>
