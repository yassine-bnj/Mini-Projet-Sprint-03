package com.example.livres.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.livres.entities.Livre;
import com.example.livres.service.LivreService;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class LivreRESTController {
	@Autowired
	LivreService livreService;
	
	@RequestMapping(value="/all",method = RequestMethod.GET)
	public List<Livre> getAllLivres() {
	return livreService.getAllLivres();
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public Livre getProduitById(@PathVariable("id") Long id) {
	return livreService.getLivre(id);
	 }
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	public Livre createProduit(@RequestBody Livre livre) {
	return livreService.saveLivre(livre);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Livre updateProduit(@RequestBody Livre livre) {
	return livreService.updateLivre(livre);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public void deleteLivre(@PathVariable("id") Long id)
	{
		livreService.deleteLivreById(id);
	}
	
	@RequestMapping(value="getByType/{id}",method = RequestMethod.GET)
	public List<Livre> getLivresByTypeId(@PathVariable("id") Long id) {
	return livreService.getLivresByTypeID(id);
	}
	
	@RequestMapping(value="getByTitreContains/{titre}",method = RequestMethod.GET)
	public List<Livre> getLivresByTitreContains(@PathVariable("titre") String titre) {
	return livreService.getLivresByTitreContains(titre);
	}
	
	
	
	
}
