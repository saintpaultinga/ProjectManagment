package edu.ea.hbn.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String city;
	private String country;
	@OneToMany(mappedBy = "location")
	private List<Project> projectList = new ArrayList<Project>();
	
	/**
	 * 
	 */
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	/**
	 * @param city
	 * @param country
	 * @param projectList
	 */
	public Location(String city, String country) {
		super();
		this.city = city;
		this.country = country;
	}



	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	//Add convenient method to add a project into the 
	//project list
	

}
