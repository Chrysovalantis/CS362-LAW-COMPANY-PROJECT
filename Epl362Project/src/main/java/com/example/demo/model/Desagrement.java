package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Desagrement extends MyModel{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private Long clientId;
    
    private Long recomandationId;
    
    private Long overruledByStaffId;
    
    private Boolean overruled = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getRecomandationId() {
		return recomandationId;
	}

	public void setRecomandationId(Long recomandationId) {
		this.recomandationId = recomandationId;
	}

	public Long getOverruledByStaffId() {
		return overruledByStaffId;
	}

	public void setOverruledByStaffId(Long overruledByStaffId) {
		this.overruledByStaffId = overruledByStaffId;
	}

	public Boolean getOverruled() {
		return overruled;
	}

	public void setOverruled(Boolean overruled) {
		this.overruled = overruled;
	}

	@Override
	public String toString() {
		return "Desagrement [id=" + id + ", clientId=" + clientId + ", recomandationId=" + recomandationId
				+ ", overruledByStaffId=" + overruledByStaffId + ", overruled=" + overruled + "]";
	} 
	
	
    
}