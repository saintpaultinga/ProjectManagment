package edu.ea.hbn.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SecondaryTable;

@Entity
@SecondaryTable(name = "Beneficaire_Informations")
public class Beneficiaire {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@Enumerated(EnumType.STRING)
	private Category category;
	//I use lob type here because information can be html file with image
	@Lob
	@Column(table = "Beneficaire_Informations")
	private byte[] information;
	@ManyToOne
	@JoinTable(name = "Project_Beneficiaire", joinColumns = @JoinColumn(name = "ProjectID"), inverseJoinColumns = @JoinColumn(name = "BeneficiaireID"))
	private Project project1;

	/**
	 * 
	 */
	public Beneficiaire() {
		super();
	}
	
	
	
	/**
	 * @param name
	 * @param category
	 * @param information
	 */
	public Beneficiaire(String name, Category category, byte[] information) {
		super();
		this.name = name;
		this.category = category;
		this.information = information;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	/**
	 * @return the information
	 */
	public byte[] getInformation() {
		return information;
	}
	/**
	 * @param information the information to set
	 */
	public void setInformation(byte[] information) {
		this.information = information;
	}

	/**
	 * @return the projects
	 */
	public Project getProject() {
		return project1;
	}


	/**
	 * @param projects the projects to set
	 */
	public void setProject(Project project) {
		this.project1 = project;
	}
	
}
