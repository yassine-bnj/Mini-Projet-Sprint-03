package com.example.livres;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.livres.entities.Livre;
import com.example.livres.entities.TypeLivre;
import com.example.livres.repos.LivreRepository;
import com.example.livres.repos.TypeRepository;

@SpringBootTest
class GestionLivresApplicationTests {
@Autowired
LivreRepository livreRepository;
@Autowired
TypeRepository typeRepository;
	@Test
	void contextLoads() {
	}
	
	@Test
	void findByTitreContains() {
		List<Livre> livres = livreRepository.findByTitre(" A quoi tient l'amour?");
		for(Livre l:livres ) {
			System.out.println(l.toString());
		}
	}
	@Test
	void findBynbPagesBetween() {
		List<Livre> livres = livreRepository.findBynbPagesBetween(310,320);
		for(Livre l:livres ) {
			System.out.println(l.toString());
		}
	}
	@Test
	void findByType() {
		TypeLivre t =typeRepository.getById(7L);
		List<Livre> livres = livreRepository.findByType(t);
		for(Livre l:livres ) {
			System.out.println(l.toString());
		}
	}
	@Test
	void findByTypeId() {
		
		List<Livre> livres = livreRepository.findByTypeIdType(7L);
		for(Livre l:livres ) {
			System.out.println(l.toString());
		}
	}

}
