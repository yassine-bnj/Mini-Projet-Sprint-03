package com.example.livres.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livres.entities.TypeLivre;
import com.example.livres.repos.TypeRepository;

@Service
public class TypeServiceImlp implements TypeService{
@Autowired
TypeRepository typeRepository;

@Override
public TypeLivre saveType(TypeLivre t) {
	return typeRepository.save(t);
}

@Override
public TypeLivre updateType(TypeLivre t) {
	return typeRepository.save(t);
}

@Override
public void deleteType(TypeLivre t) {
	typeRepository.delete(t);
	
}
@Override
public void deleteTypeById(Long id) {
	typeRepository.deleteById(id);
	
}



@Override
public TypeLivre getType(Long id) {
	return typeRepository.findById(id).get();
}

@Override
public List<TypeLivre> getAllTypes() {
	return typeRepository.findAll();
			}
	
	
}
