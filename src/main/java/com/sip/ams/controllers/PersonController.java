package com.sip.ams.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.sip.ams.entities.Person;
import com.sip.ams.forms.PersonForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonController {

	private static List<Person> persons = new ArrayList<Person>();
	private static String fnm;
	private static String lnm;

	static {
		persons.add(new Person("Albert", "Einstein"));
		persons.add(new Person("Frederic", "Gauss"));
	}

	// Inject via application.properties
	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;

	@RequestMapping(value = { "/indexPerson", "/Person" }, method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("message", message);

		return "person/index";
	}

	@RequestMapping(value = { "/personList" }, method = RequestMethod.GET)
	public String personList(Model model) {

		model.addAttribute("persons", persons);

		return "person/personList";
	}

	@RequestMapping(value = { "/addPerson" }, method = RequestMethod.GET)
	public String showAddPersonPage(Model model) {

		PersonForm personForm = new PersonForm();
		model.addAttribute("personForm", personForm);

		return "person/addPerson";
	}

	@RequestMapping(value = { "/addPerson" }, method = RequestMethod.POST)
	public String savePerson(Model model, //
			@ModelAttribute("personForm") PersonForm personForm) {

		String firstName = personForm.getFirstName();
		String lastName = personForm.getLastName();

		if (firstName != null && firstName.length() > 0 //
				&& lastName != null && lastName.length() > 0) {
			Person newPerson = new Person(firstName, lastName);
			persons.add(newPerson);

			return "redirect:/personList";
		}

		model.addAttribute("errorMessage", errorMessage);
		return "person/addPerson";
	}
	
	@RequestMapping(value = { "/updatePerson" }, method = RequestMethod.GET)
	public String showUpdatePersonPage(Model model,
			@RequestParam(required = true) String fn,
			@RequestParam(required = true) String ln) {

		PersonForm personForm = new PersonForm();
		personForm.setFirstName(fn);
		personForm.setLastName(ln);
		this.fnm = fn;
		this.lnm = ln;
		//model.addAttribute("fn", fn);
		//model.addAttribute("ln", ln);
		model.addAttribute("personForm", personForm);
		
		return "person/updatePerson";
	}
	
	@RequestMapping(value = { "/updatePerson" }, method = RequestMethod.POST)
	public String saveUpdatePerson(Model model, //
			@ModelAttribute("personForm") PersonForm personForm) {

		String firstName = personForm.getFirstName();
		String lastName = personForm.getLastName();


		if (firstName != null && firstName.length() > 0 //
				&& lastName != null && lastName.length() > 0) {
			Iterator<Person> it = persons.iterator();
			boolean done = true;
			Person newPerson;
			while (it.hasNext() && done) {
				newPerson = it.next();
				if(newPerson.getFirstName().contentEquals(this.fnm) && newPerson.getLastName().equals(this.lnm)) {
					persons.set(persons.indexOf(newPerson), new Person(firstName,lastName));
					done = false;
					System.out.print(firstName+" "+lastName);
					//it.next().setFirstName(firstName);
					//it.next().setLastName(lastName);
				}
			}


			return "redirect:/personList";
		}

		model.addAttribute("errorMessage", errorMessage);
		return "person/updatePerson";
	}
	
	@RequestMapping(value = { "/deletePerson" }, method = RequestMethod.GET)
	public String showListAfterDelete(Model model,
			@RequestParam(required = true) String fn,
			@RequestParam(required = true) String ln) {
		Iterator<Person> it = persons.iterator();
		boolean done = true;
		Person newPerson;
		while (it.hasNext() && done) {
			newPerson = it.next();
			if(newPerson.getFirstName().contentEquals(fn) && newPerson.getLastName().equals(ln)) {
				persons.remove(newPerson);
				done = false;
				
			}
		}
		return "redirect:/personList";
	}

}
