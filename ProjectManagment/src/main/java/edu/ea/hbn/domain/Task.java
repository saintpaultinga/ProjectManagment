package edu.ea.hbn.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import edu.ea.hbn.utils.DateUtils;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startdate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date enddate;
    @Enumerated(EnumType.STRING)
	private Status status;
	@ManyToOne
	@JoinTable(name = "Task_Project")
	private Project project;
	@OneToMany(mappedBy = "task",cascade=CascadeType.ALL)
	private List<Resource> resourseList = new ArrayList<>();
	@OneToMany(mappedBy = "task", cascade=CascadeType.ALL)
	private List<Volunteer> volunteerList = new ArrayList<>();
	/**
	 * 
	 */
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @param name
	 * @param description
	 * @param startdate
	 * @param enddate
	 * @param status
	 */
	public Task(String name, String description, String startdate, String enddate, Status status) {
		super();
		this.name = name;
		this.description = description;
		this.startdate = DateUtils.convertStringToDate(startdate);
		this.enddate = DateUtils.convertStringToDate(enddate);
		this.status = status;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the startdate
	 */
	public Date getStartdate() {
		return startdate;
	}
	/**
	 * @param startdate the startdate to set
	 */
	public void setStartdate(String startdate) {
		this.startdate = DateUtils.convertStringToDate(startdate);
	}
	/**
	 * @return the enddate
	 */
	public Date getEnddate() {
		return enddate;
	}
	/**
	 * @param enddate the enddate to set
	 */
	public void setEnddate(String enddate) {
		this.enddate = DateUtils.convertStringToDate(enddate);
	}
	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}
	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}
	
	
	public void updateAll(Task task){
		this.setDescription(task.getDescription());
		this.setEnddate(DateUtils.convertDateToString(task.getEnddate()));
		this.setName(task.getName());
		this.setProject(task.getProject());
		this.setStartdate(DateUtils.convertDateToString(task.getStartdate()));
		this.setResourseList(task.getResourseList());
		this.setStatus(task.getStatus());
		this.setVolunteerList(task.getVolunteerList());
	}
	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	/**
	 * @return the resourseList
	 */
	public List<Resource> getResourseList() {
		return resourseList;
	}
	/**
	 * @param resourseList the resourseList to set
	 */
	public void setResourseList(List<Resource> resourseList) {
		this.resourseList = resourseList;
	}
	/**
	 * @return the volunteerList
	 */
	public List<Volunteer> getVolunteerList() {
		return volunteerList;
	}
	/**
	 * @param volunteerList the volunteerList to set
	 */
	public void setVolunteerList(List<Volunteer> volunteerList) {
		this.volunteerList = volunteerList;
	}
	
	public void addVolunteer(Volunteer volunteer) {
		volunteer.setTask(this);
		volunteerList.add(volunteer);

	}
	public void removeVolunteer(Volunteer v) {
		v.setTask(null);
		this.volunteerList.add(v);
	}
	
	public void addResource(Resource res) {
		res.setTask(this);
		resourseList.add(res);

	}
	public void removeResource(Resource res) {
		res.setTask(null);
		this.resourseList.add(res);
	}
}
