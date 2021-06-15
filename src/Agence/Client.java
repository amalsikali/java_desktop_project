package Agence;



public class Client {
	private String cin,nom,prenom,localisation;

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public Client(String cin, String nom, String prenom, String localisation) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.localisation = localisation;
	}


}
