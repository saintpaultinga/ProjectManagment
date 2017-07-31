package edu.ea.hbn.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import edu.ea.hbn.utils.DateUtils;

@Entity
public class Volunteer extends User {
	
   private String title;
   @Temporal(TemporalType.DATE)
   private Date dateOfBirth;
   private String registerNum;
   @ManyToOne
   @JoinColumn (name = "Volunteer_task")
   private Task task;
   
   
   
/**
 * 
 */
public Volunteer() {
	super();
	// TODO Auto-generated constructor stub
}



/**
 * @param title
 * @param dateOfBirth
 * @param registerNum
 */
public Volunteer(String firstname,String lastname,String email,String title, String dateOfBirth, String registerNum) {
	super(firstname, lastname, email);
	this.title = title;
	this.dateOfBirth = DateUtils.convertStringToDate(dateOfBirth);
	this.registerNum = registerNum;
}



/**
 * @return the title
 */
public String getTitle() {
	return title;
}
/**
 * @param title the title to set
 */
public void setTitle(String title) {
	this.title = title;
}
/**
 * @return the dateOfBirth
 */
public Date getDateOfBirth() {
	return dateOfBirth;
}
/**
 * @param dateOfBirth the dateOfBirth to set
 */
public void setDateOfBirth(String dateOfBirth) {
	this.dateOfBirth = DateUtils.convertStringToDate(dateOfBirth);
}
/**
 * @return the registerNum
 */
public String getRegisterNum() {
	return registerNum;
}
/**
 * @param registerNum the registerNum to set
 */
public void setRegisterNum(String registerNum) {
	this.registerNum = registerNum;
}
/**
 * @return the task
 */
public Task getTask() {
	return task;
}
/**
 * @param task the task to set
 */
public void setTask(Task task) {
	this.task = task;
}
   
   
}
