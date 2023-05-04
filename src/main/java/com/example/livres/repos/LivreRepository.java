package com.example.livres.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.livres.entities.Livre;
import com.example.livres.entities.TypeLivre;

public interface LivreRepository extends JpaRepository<Livre,Long> {
	
	
	List<Livre> findByTitre(String t);
	List<Livre> findByTypeIdType(long id);
	@Query("select l from Livre l where l.nbPages between :min and :max")
	List<Livre> findBynbPagesBetween(@Param("min") int min,@Param("max") int max);
	
	@Query("select l from Livre l where l.type = ?1")
	List<Livre> findByType (TypeLivre type);
	List<Livre> findByTitreContains(String titre);
	
	
}
