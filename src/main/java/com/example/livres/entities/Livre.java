package com.example.livres.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Livre {
   
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLivre;
	
	@NotNull
	@Size (min = 2,max = 20)
	private String titre;
	@NotNull
	@Size (min = 4,max = 20)
	private String auteur;
	@NotNull
	@Min(value = 10)
	@Max(value = 10000)
	private int nbPages;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent
	private Date dateParution;
	@ManyToOne()
	private TypeLivre type;
	

	public Livre(String titre, String auteur, int nbPages, Date dateParution) {
		super();
		this.titre = titre;
		this.auteur = auteur;
		this.nbPages = nbPages;
		this.dateParution = dateParution;
	}
	public Livre(String titre, String auteur, int nbPages, Date dateParution, TypeLivre type) {
		super();
		this.titre = titre;
		this.auteur = auteur;
		this.nbPages = nbPages;
	
		this.dateParution = dateParution;
		this.type = type;
	}

	public Long getIdLivre() {
		return idLivre;
	}

	public void setIdLivre(Long idLivre) {
		this.idLivre = idLivre;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public int getNbPages() {
		return nbPages;
	}

	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}

	public Date getDateParution() {
		return dateParution;
	}

	public void setDateParution(Date dateParution) {
		this.dateParution = dateParution;
	}
	
	@Override
	public String toString() {
		return "Livre [idLivre=" + idLivre + ", titreLivre=" + titre + ", nbPages=" + nbPages
				+ ", dateParution=" + dateParution + "]";
	}
}
