package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ChangeRequest")
public class ChangeRequest extends MyModel {
	public static final String UNPROSESED = "1";
	public static final String PROSESED = "2";
    
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    // true for delete
    // false for edit
    private boolean deleted =false;
	
    private Long clientId;
    
    private String newName;
    
    private String newSurname;
    
    private boolean newPotentialMoneyLaundring = false;
    
	private String description;
    
    private String state = UNPROSESED;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getNewSurname() {
		return newSurname;
	}

	public void setNewSurname(String newSurname) {
		this.newSurname = newSurname;
	}


	public boolean isNewPotentialMoneyLaundring() {
		return newPotentialMoneyLaundring;
	}

	public void setNewPotentialMoneyLaundring(boolean newPotentialMoneyLaundring) {
		this.newPotentialMoneyLaundring = newPotentialMoneyLaundring;
	}

	@Override
	public String toString() {
		return "ChangeRequest [id=" + id + ", deleted=" + deleted + ", clientId=" + clientId + ", newName=" + newName
				+ ", newSurname=" + newSurname + ", newPotentialMoneyLaundring=" + newPotentialMoneyLaundring
				+ ", description=" + description + ", state=" + state + "]";
	}
    
	
}