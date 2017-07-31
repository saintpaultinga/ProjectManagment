package edu.ea.hbn.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;



@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="USER")
public abstract class User {
	@TableGenerator(name = "USER_GENERATOR", table = "ID_GENERATOR", 
			pkColumnName = "GENERATOR_NAME", valueColumnName = "GENERATOR_VAL",
			allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_GENERATOR")
	private long id;
	private String firstname;
	private String lastname;
	private String email;

	
	/**
	 * 
	 */
	public User() {
		super();
	}
	
	
	/**
	 * @param firstname
	 * @param lastname
	 * @param email
	 */
	public User(String firstname, String lastname, String email) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
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
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
}
