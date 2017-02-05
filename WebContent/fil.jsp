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
        
        	<h4>On est sur le fil de discussion numero ${id}</h4>
        	
        	<c:forEach items="${message}" var="msg" >
	        	<h5>Auteur : <c:out value = "${msg.auteur}"/>, id message : <c:out value = "${msg.id}"/> </h5>
	        	<p><c:out value = "${msg.texte}"/></p>
	        	<form name="supprimmer_reponse" method="post" action=suppr_reponse>
				 	<input type="hidden" name="id_fil" value="${id}">
				 	<input type="hidden" name="id_message" value="${msg.id}">
				 	<input type="submit" value="suppr reponse">
				</form> 
			</c:forEach>
			
			<form name="Name_repondre" method="post" action="reponse">
				 <input type="hidden" name="id_fil" value="${id}"><br>
				 <input type="submit" value="répondre">
			</form> 
			<form name="supprimer_fil" method="post" action="suppr_fil">
				 <input type="hidden" name="id_fil" value="${id}"><br>
				 <input type="submit" value="supprimer fil">
			</form> 
			
        </div>
    </body>
</html>
