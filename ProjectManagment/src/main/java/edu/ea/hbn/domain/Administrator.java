package edu.ea.hbn.domain;

import javax.persistence.Entity;

@Entity
public class Administrator extends User {
	
	private String login;
	private String password;
	
	
	/**
	 * 
	 */
	public Administrator() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
