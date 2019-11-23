package com.sip.ams.entities;

public class Animal {
	
	private String nom;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	private int age;
	public Animal(String nom, int age) {
		
		this.nom = nom;
		this.age = age;
	}

}
