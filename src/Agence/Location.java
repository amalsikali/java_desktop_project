package Agence;
public class Location {
	private String id_maison,prix,ville,dimension,nb_chambre,disponibilit�,cin;

	public String getId_maison() {
		return id_maison;
	}

	public void setId_maison(String id_maison) {
		this.id_maison = id_maison;
	}

	public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getNb_chambre() {
		return nb_chambre;
	}

	public void setNb_chambre(String nb_chambre) {
		this.nb_chambre = nb_chambre;
	}

	public String getDisponibilit�() {
		return disponibilit�;
	}

	public void setDisponibilit�(String disponibilit�) {
		this.disponibilit� = disponibilit�;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public Location(String id_maison, String prix, String ville, String dimension, String nb_chambre,
			String disponibilit�, String cin) {
		super();
		this.id_maison = id_maison;
		this.prix = prix;
		this.ville = ville;
		this.dimension = dimension;
		this.nb_chambre = nb_chambre;
		this.disponibilit� = disponibilit�;
		this.cin = cin;
	}
	

}
