package com.project.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean
@ViewScoped
public class bean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer numberOfGuests;
	private String[] guests;

	
	public void submit() {
		guests = new String[numberOfGuests];
	}

	public void save() {
		System.out.println("METOD SAVE");

		for (String i : guests) {
			System.out.println("Esto esta almacenado: " + i);
		}
	}

	public Integer getNumberOfGuests() {
		return numberOfGuests;
	}

	public void setNumberOfGuests(Integer numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}

	public String[] getGuests() {
		return guests;
	}

	public void setGuests(String[] guests) {
		this.guests = guests;
	}

}
