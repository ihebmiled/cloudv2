package com.sip.ams.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sip.ams.entities.Animal;

@Controller
public class FirstController {
	
	@GetMapping("/home")
	//@ResponseBody
	public String home() {
		//return "<h1 align=center>Hello Spring Boot</h1>";
		return "/first/home";
	}

	
	@GetMapping("/acceuil")
	public String acceuil(@RequestParam(required = false, defaultValue = "Spring Boot") String framework,
						  @RequestParam(required = false, defaultValue = "1.0") String version,
							ModelMap modObj) {
		String ecole = "POLYTECH";
		String formation = "Spring boot & angular";
		String ch = "Vous avez choisi: "+framework+" la version: "+version;
		
		ArrayList<String> names = new ArrayList<>() ;
		ArrayList<Animal>animals = new ArrayList<>() ;
		names.add("Mohsen");
		names.add("Chedly");
		names.add("Zohra");
		animals.add(new Animal("chat",2));
		animals.add(new Animal("chien",5));
		animals.add(new Animal("lion",13));
		modObj.put("ec", ecole);
		modObj.put("forma", formation);
		modObj.put("names", names);
		modObj.put("animals", animals);
		modObj.put("msg", ch);
		
		return "/first/acceuil";
	}
}
