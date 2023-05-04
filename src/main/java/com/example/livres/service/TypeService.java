package com.example.livres.service;

import java.util.List;

import com.example.livres.entities.TypeLivre;



public interface TypeService {
	TypeLivre saveType(TypeLivre t);
	TypeLivre updateType(TypeLivre t);
	void deleteType(TypeLivre t);
	void deleteTypeById(Long id);
	TypeLivre getType(Long id);
	List<TypeLivre> getAllTypes();
}
