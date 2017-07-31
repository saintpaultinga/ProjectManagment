package edu.ea.hbn.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import edu.ea.hbn.utils.DateUtils;

@Entity
@NamedQueries({
	@NamedQuery(name = "Project.findAll",query="select p from Project p"),
	@NamedQuery(name = "Project.findByStatus",query="select p from Project p where p.status = :status"),
	@NamedQuery(name = "Project.findProjectWithVolunteerNotNull",query="select distinct p from Project p Join p.taskList t where size(t.volunteerList) > 0 order by t.startdate"), 
	@NamedQuery(name = "Project.findProjectByKeyWordAnLocation",query="select p from Project p where p.projectName like lower (:searchkeyword) or p.location.city like (:searchkeyword) or p.location.country like (:searchkeyword)") 
})
@SecondaryTable(name = "Project_Description")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long projectId;
	private String projectName;
	@Basic(fetch = FetchType.LAZY)
	@Lob
	@Column(table = "Project_Description")
	private byte[] description;
	@Enumerated(EnumType.STRING)
	private Status status;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startdate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date enddate;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "Project_Location")
	private Location location;
	@OneToMany(mappedBy = "project", orphanRemoval = true, cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Task> taskList = new HashSet<>();
	@OneToMany(mappedBy = "project1",cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	Set<Beneficiaire> beneficiaireList = new HashSet<>();

	/**
	 * 
	 */
	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param projectName
	 * @param description
	 * @param status
	 * @param startdate
	 * @param enddate
	 */
	public Project(String projectName, byte[] description, Status status, String startdate, String enddate) {
		super();
		this.projectName = projectName;
		this.description = description;
		this.status = status;
		this.startdate = DateUtils.convertStringToDate(startdate);
		this.enddate = DateUtils.convertStringToDate(enddate);
	}

	/**
	 * @return the projectId
	 */
	public long getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId
	 *            the projectId to set
	 */
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName
	 *            the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the description
	 */
	public byte[] getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(byte[] description) {
		this.description = description;
	}

	/**
	 * @return the startdate
	 */
	public Date getStartdate() {
		return startdate;
	}

	/**
	 * @param startdate
	 *            the startdate to set
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
	 * @param enddate
	 *            the enddate to set
	 */
	public void setEnddate(String enddate) {
		this.enddate = DateUtils.convertStringToDate(enddate);
	}

	/**
	 * @return the taskList
	 */
	public Set<Task> getTaskList() {
		return this.taskList;
	}

	/**
	 * @return the beneficiaireList
	 */
	public Set<Beneficiaire> getBeneficiaireList() {
		return beneficiaireList;
	}

	/**
	 * @param beneficiaireList
	 *            the beneficiaireList to set
	 */
	public void setBeneficiaireList(Set<Beneficiaire> beneficiaireList) {
		this.beneficiaireList = beneficiaireList;
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
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @param startdate the startdate to set
	 */
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	/**
	 * @param enddate the enddate to set
	 */
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	
	

	public void addTask(Task task) {
		task.setProject(this);
		this.taskList.add(task);
		

	}

	public void updateTask(Task task) {
		this.taskList.stream().filter(t -> t.getId() == task.getId()).collect(Collectors.toList()).get(0)
				.updateAll(task);

	}

	public void removeTask(Task task) {
		task.setProject(null);
		this.taskList.remove(task);
	}
	
	public void addBeneficiaire(Beneficiaire b) {
		b.setProject(this);
		this.beneficiaireList.add(b);

	}
    

	public void removeBeneficiaire(Beneficiaire b) {
		b.setProject(null);
		this.beneficiaireList.remove(b);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (projectId ^ (projectId >>> 32));
		result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (projectId != other.projectId)
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		return true;
	}
	
	
	
    
	
}
