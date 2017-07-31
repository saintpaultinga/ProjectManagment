package edu.ea.hbn.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
public class Resource {
	@Id
	@GeneratedValue
	private long id;
	private String type;
	private int quantity;
	@ManyToOne
	@JoinTable(name ="Resource_Task")
	private Task task;
	
	
	
	
	/**
	 * 
	 */
	public Resource() {
		super();
	}
	

	/**
	 * @param type
	 * @param quantity
	 */
	public Resource(String type, int quantity) {
		super();
		this.type = type;
		this.quantity = quantity;
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
	public String getType() {
		return type;
	}
	/**
	 * @param name the name to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
