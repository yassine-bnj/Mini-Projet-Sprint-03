package com.example.livres.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.livres.entities.Livre;
import com.example.livres.entities.TypeLivre;
import com.example.livres.service.LivreService;
import com.example.livres.service.TypeService;

import aj.org.objectweb.asm.Type;

@Controller
public class TypeController {

	
	
	@Autowired
	TypeService typeService;


	@RequestMapping("/showCreateType")
	public String showCreate(ModelMap modelMap)
	{
		
		
	return "createType";
	}
	@RequestMapping("/saveType")
	public String saveType(@ModelAttribute("type") TypeLivre type,
	 ModelMap modelMap) throws
	ParseException
	{

	
	 

TypeLivre saveType = typeService.saveType(type);

	

	 
	return "redirect:/ListeTypes";
	}

	@RequestMapping("/ListeTypes")
	public String ListeTypes(ModelMap modelMap)
	{
	List<TypeLivre> types = typeService.getAllTypes();
	modelMap.addAttribute("types", types);
	return "listeTypes";
	}



	@RequestMapping("/supprimerType")
	public String supprimerType(@RequestParam("id") Long id,
	ModelMap modelMap)
	{
	typeService.deleteTypeById(id);
	List<TypeLivre> types = typeService.getAllTypes();
	modelMap.addAttribute("types", types);

	return "listeTypes";
	}


	@RequestMapping("/modifierType")
	public String modifierType(@RequestParam("id") Long id,ModelMap modelMap)
	{
	TypeLivre type = typeService.getType(id);
	modelMap.addAttribute("type", type);
	return "editerType";
	}
	@RequestMapping("/updateType")
	public String updateType(@ModelAttribute("type") TypeLivre type,
			ModelMap modelMap) throws ParseException
	{
	

	 typeService.updateType(type);
	 List<TypeLivre> types = typeService.getAllTypes();
	 modelMap.addAttribute("types", types);
	 return "redirect:/ListeTypes";
	}
	
}
