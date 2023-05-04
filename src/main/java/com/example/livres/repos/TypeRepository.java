package com.example.livres.repos;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.livres.entities.TypeLivre;



public interface TypeRepository extends JpaRepository<TypeLivre,Long>{
	
}
