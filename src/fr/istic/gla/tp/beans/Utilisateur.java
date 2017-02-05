package fr.istic.gla.tp.beans;

/**
 * 
 * Classe représentant un utilisateur du système. Elle contient les informations utiles nécessaires (login, password, profil).<br>
 * Lorsqu'un objet est créé, il est initialisé avec un profil d'invité, ce qui signifie que l'utilisateur n'a fourni aucune authentification.
 * 
 * @author yves
 *
 */
public class Utilisateur {
	
	private int id;
	
	private String login = "invité";
	
	private String password;
	
	private String profil = "invité";
	
	private String avatar;
	private String salt;
	
	

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getLogin() {
		return login;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
	}

}
