package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client extends MyModel {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
      
    private String name;
    
    private String surname;

    private boolean locked=false;
    
    private boolean potentialMoneyLaundring = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean isPotentialMoneyLaundring() {
		return potentialMoneyLaundring;
	}

	public void setPotentialMoneyLaundring(boolean potentialMoneyLaundring) {
		this.potentialMoneyLaundring = potentialMoneyLaundring;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", surname=" + surname + ", locked=" + locked
				+ ", potentialMoneyLaundring=" + potentialMoneyLaundring + "]";
	}
   
	

	
	
}