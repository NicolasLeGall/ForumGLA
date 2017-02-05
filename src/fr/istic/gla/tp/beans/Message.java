package fr.istic.gla.tp.beans;

public class Message {

	private int id;
	private String auteur;
	private String texte;
	private int idDiscussion;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}
	public int getIdDiscussion() {
		return idDiscussion;
	}
	public void setIdDiscussion(int idDiscussion) {
		this.idDiscussion = idDiscussion;
	}
	
	
}
