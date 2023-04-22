package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blo.VilleBLO;
import com.dto.Ville;

@RestController
public class VilleController {

	@Autowired
	VilleBLO villeBLOService;

	@RequestMapping(value = "/ville", method = RequestMethod.GET)
	public String get(@RequestParam(required = false, value = "codePostal") String param) {
		System.out.println("get : " + param);
		System.out.println(villeBLOService.getInfoVille(param));
		return villeBLOService.getInfoVille(param);
	}
	
	@GetMapping("/villes")
	public List<Ville> getVilles() {
		return villeBLOService.getVilles();
	}	

	@GetMapping("/villes/{nom}")
		public Ville getVille(@PathVariable("nom") String nom) {
			return villeBLOService.getVilleByNom(nom);
		}


	@PostMapping("ville")
	public void addVille(@RequestBody Ville ville) {
		villeBLOService.addVille(ville);
		System.out.println("ville ajoutée");
	}
	
	@DeleteMapping("/ville/{nomVille}")
	public void deleteVille(@PathVariable String nomVille) {
		villeBLOService.deleteVille(nomVille);
	}
	
	@PutMapping("/ville")
	public void updateVille(@RequestBody Ville ville) {
		villeBLOService.updateVille(ville);
		System.out.println("la ville a été modifiée");
	}
}
