package com.example.livres.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.livres.entities.Livre;



@Service
public interface LivreService {

	Livre saveLivre(Livre l);
	Livre updateLivre(Livre l);
	void deleteLivre(Livre l);
	void deleteLivreById(Long id);
	Livre getLivre(Long id);
	List<Livre> getAllLivres();
	Page<Livre> getAllLivresParPage(int page, int size);
	
	List<Livre> getLivresByTypeID(Long id);
	List<Livre> getLivresByTitre(String titre);
	List<Livre> getLivresByTitreContains(String titre);
}
