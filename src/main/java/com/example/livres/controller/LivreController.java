package com.example.livres.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.livres.entities.Livre;
import com.example.livres.entities.TypeLivre;
import com.example.livres.service.LivreService;
import com.example.livres.service.TypeService;

import jakarta.validation.Valid;


@Controller
public class LivreController {
@Autowired
LivreService livreService;
@Autowired
TypeService typeService;



@RequestMapping("/showCreate")
public String showCreate(ModelMap modelMap)
{
	
	
	List<TypeLivre> types=typeService.getAllTypes();
	Livre livre = new Livre();
	TypeLivre type = new TypeLivre();
	type = types.get(0);
	livre.setType(type);
	
	modelMap.addAttribute("types",types);
    modelMap.addAttribute("livre", livre);
    modelMap.addAttribute("mode", "new");
   
    return "formLivre";
}


@RequestMapping("/saveLivre")
public String saveLivre(@Valid Livre livre,
 BindingResult bindingResult,ModelMap modelMap,
 @RequestParam (name="page",defaultValue = "0") int page,
 @RequestParam (name="size",defaultValue = "4") int size
		)
{

if (bindingResult.hasErrors()) {

	List<TypeLivre> types=typeService.getAllTypes();
	modelMap.addAttribute("types",types);
	
	int currentPage ;
	if(livre.getIdLivre()==null) {
		 modelMap.addAttribute("mode", "new");
		 
		 currentPage = livreService.getAllLivres().size()/size;
	}else {
		modelMap.addAttribute("mode", "edit");
		currentPage =page;
	}
	
	Page<Livre> livres = livreService.getAllLivresParPage(currentPage, 4);
	modelMap.addAttribute("pages", new int[livres.getTotalPages()]);
	modelMap.addAttribute("currentPage", currentPage);
	modelMap.addAttribute("size", 4);
	modelMap.addAttribute("livres", livres);	
	
	 return "formLivre";
}
int currentPage =page; 
if(livre.getIdLivre()==null) {
	currentPage = livreService.getAllLivres().size()/size;}
livreService.saveLivre(livre);


Page<Livre> livres = livreService.getAllLivresParPage(currentPage, 4);
modelMap.addAttribute("pages", new int[livres.getTotalPages()]);
modelMap.addAttribute("currentPage", currentPage);
modelMap.addAttribute("size", 4);
modelMap.addAttribute("livres", livres);
return "listeLivres";
//return ("redirect:/ListeLivres?page="+currentPage+"&size="+size);
}



@RequestMapping("/ListeLivres")
public String listeLivres(ModelMap modelMap,
@RequestParam (name="page",defaultValue = "0") int page,
@RequestParam (name="size", defaultValue = "4") int size)
{
	
	System.out.println("page = "+page+" size = "+size);
Page<Livre> livres = livreService.getAllLivresParPage(page, size);
modelMap.addAttribute("livres", livres);
 modelMap.addAttribute("pages", new int[livres.getTotalPages()]);
modelMap.addAttribute("currentPage", page);
modelMap.addAttribute("size", size);
return "listeLivres";
}



@RequestMapping("/supprimerLivre")
public String supprimerLivre(@RequestParam("id") Long id,ModelMap modelMap,
		@RequestParam (name="page",defaultValue = "0") int page,
		@RequestParam (name="size", defaultValue = "4") int size)
{
	System.out.println("id = "+id+"page = "+page+" size = "+size);
livreService.deleteLivreById(id);

Page<Livre> livres = livreService.getAllLivresParPage(page,size);

if(livres.getNumberOfElements()==0) {
	page--;
}
modelMap.addAttribute("livres", livres);
modelMap.addAttribute("pages", new int[livres.getTotalPages()]);
modelMap.addAttribute("currentPage",page);
modelMap.addAttribute("size", size);
//return "listeLivres";
return ("redirect:/ListeLivres?page="+page+"&size="+size);

}


@RequestMapping("/modifierLivre")
public String editerProduit(@RequestParam("id") Long id,ModelMap modelMap,
		@RequestParam (name="page",defaultValue = "0") int page,
		@RequestParam (name="size", defaultValue = "4") int size)
{
Livre l= livreService.getLivre(id);

modelMap.addAttribute("livre", l);
modelMap.addAttribute("mode", "edit");
List<TypeLivre> types=typeService.getAllTypes();
modelMap.addAttribute("types",types);
System.out.println("the page "+page);
modelMap.addAttribute("currentPage",page);
modelMap.addAttribute("size", size);

return "formLivre";
}
@RequestMapping("/updateLivre")
public String updateProduit(@ModelAttribute("livre") Livre livre,
		@RequestParam (name="page",defaultValue = "0") int page,
		@RequestParam (name="size", defaultValue = "4") int size,
@RequestParam("dateP") String date,ModelMap modelMap) throws ParseException
{
//conversion de la date
 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
 Date setDateParution = dateformat.parse(String.valueOf(date));
 livre.setDateParution(setDateParution);

 livreService.updateLivre(livre);

 
 Page<Livre> livres = livreService.getAllLivresParPage(page,size);
 modelMap.addAttribute("livres", livres);
 modelMap.addAttribute("pages", new int[livres.getTotalPages()]);
 modelMap.addAttribute("currentPage",page);
 modelMap.addAttribute("size", size);
 
 
 
 
 return "listeLivres";
}


}
