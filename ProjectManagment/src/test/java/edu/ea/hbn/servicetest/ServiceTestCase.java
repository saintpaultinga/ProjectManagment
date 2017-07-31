/**
 * 
 */
package edu.ea.hbn.servicetest;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import edu.ea.hbn.dao.ProjectDAO;
import edu.ea.hbn.dao.TaskDAO;
import edu.ea.hbn.domain.Beneficiaire;
import edu.ea.hbn.domain.Category;
import edu.ea.hbn.domain.Location;
import edu.ea.hbn.domain.Project;
import edu.ea.hbn.domain.Resource;
import edu.ea.hbn.domain.Status;
import edu.ea.hbn.domain.Task;
import edu.ea.hbn.domain.Volunteer;

/**
 * @author TSPDEV
 *
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ServiceTestCase {

    Project project = null;
	
	
	@Before
	public void prepareDatabase() {
	  
		Location location = new Location("IOWA city","USA");
		
		Beneficiaire beneficiaire1 = new Beneficiaire("MidleWestOne Bank",Category.ENTERPRISE ,"Bank institution in USA created in 1995".getBytes());
		Beneficiaire beneficiaire2 = new Beneficiaire("Accenture",Category.ENTERPRISE ,"International company created in USA and available in more than 20 country".getBytes());
	    
		Volunteer v1 = new Volunteer("Engela","RYAN","engela@micro.com","Java Developer","1988/01/26","VOOO1");
		Volunteer v2 = new Volunteer("Anderson","Bewery","anderson@ando.com","Java Architect","1982/08/29","VOOO2");
		Volunteer v3 = new Volunteer("Saint-Paul","TINGA","stinga@mum.edu","Scrum Master","1988/01/26","VOOO3");
		
		Resource resource1 = new Resource("Java Developer",5);
		Resource resource2 = new Resource("Java Architect",3);
		Resource resource3 = new Resource("Scrum Master",1);
		
		Task task1 = new Task("Data managment layer development","Implementation of The project persistent and the service layer ","08/14/2017","08/18/2017",Status.NONE);
		task1.addVolunteer(v1);
		task1.addVolunteer(v2);
		task1.addVolunteer(v3);
		
		task1.addResource(resource1);
		task1.addResource(resource2);
		task1.addResource(resource3);
		
		project = new Project("ACM project","This project aims to develop an Advance Customer Managment Software".getBytes() ,Status.STARTED, "07/25/2017","08/23/2017");
		project.setLocation(location);
		//One task is added to the project
		project.addTask(task1);
	    project.addBeneficiaire(beneficiaire1);
	    project.addBeneficiaire(beneficiaire2);
	    
	  
	}
	
	@Test
	public void testCreateProject() {
		
		assertTrue(" test if The project is created ", ProjectDAO.createProject(project));
	}

	@Test
	public void testReadProject() {
	  assertEquals("ACM project", ProjectDAO.getProject(1).getProjectName());
	}
	
	@Test
	public void testCreateTask() {
		Volunteer v4 = new Volunteer("Eamn","RYAN","enga@micro.com","Oracle administrator","1988/01/26","VOOO4");
		Volunteer v5 = new Volunteer("Andson","Bewary","andson@ando.com","C# Architect","1982/08/29","VOOO5");
		Volunteer v6 = new Volunteer("Paul","TIAMA","stinga@mum.edu","Agile Master","1988/01/26","VOOO6");
		
		Resource resource4 = new Resource("Oracle administrator",2);
		Resource resource5 = new Resource("C# Architect",5);
		Resource resource6 = new Resource("Agile Master",1);
		
		Task task2 = new Task("Code Optimization","Implementation of The project persistent and the service layer ","2017/08/14","2017/08/18",Status.PENDING);
		task2.addVolunteer(v4);
		task2.addVolunteer(v5);
		task2.addVolunteer(v6);
		
		task2.addResource(resource4);
		task2.addResource(resource5);
		task2.addResource(resource6);
		Project p = ProjectDAO.getProject(1);
		TaskDAO.createTask(p, task2);
		Project p1 = ProjectDAO.getProject(1);
		assertEquals(2, p1.getTaskList().size());
	}
	
	@Test
	public void testGetTask() {
		Task t = TaskDAO.getTask(2);
		Project p = ProjectDAO.getProject(1);
		System.out.println("--------------------"+p.getStatus());
		assertTrue("test if project 1 contains tast with id 1", p.getTaskList().stream().filter(tk -> tk.getId()==t.getId()).findAny().isPresent());
	}
	
	
	@Test
	public void testListProjectAndBeneficiaireInfo(){
		List<Project> projectList = ProjectDAO.findAll();
		for(Project p: projectList){
			System.out.println("------Project ID----"+p.getProjectId()+"------Project Name-----"+p.getProjectName()+"------Project----Status");
	       for(Beneficiaire b: p.getBeneficiaireList()){
	    	   System.out.println("--Beneficiaire ID-----"+b.getId()+"-----Beneficiaire Name--"+b.getName()+"--Category--"+b.getCategory());
	       }
		}
		
		assertEquals(2, ProjectDAO.getProject(1).getBeneficiaireList().size());
	}
	
	@Test
	public void testListAllProjectAndTheyTasks(){
		List<Project> projectList = ProjectDAO.findAll();
		for(Project p: projectList){
			System.out.println("------Project ID----"+p.getProjectId()+"------Project Name-----"+p.getProjectName()+"------Project----Status");
	       for(Task t: p.getTaskList()){
	    	   System.out.println("--Task ID-----"+t.getId()+"-----Name--"+t.getName()+"-Task-Status--"+t.getStatus());
	       }
		}
		
		assertEquals(2, ProjectDAO.getProject(1).getTaskList().size());
	}
	

	@Test
	public void testListProjectByStatus(){
		List<Project> projectList = ProjectDAO.findProjectByStatus(Status.STARTED);
		for(Project p: projectList){
			System.out.println("------Project ID----"+p.getProjectId()+"------Project Name-----"+p.getProjectName()+"------Project-Status----"+p.getStatus());
		}
		
		assertEquals(1, projectList.size());
	}
	
	@Test
	public void testListProjectAndTaskWhereVolunteerNotNull(){
		List<Project> projectList = ProjectDAO.findProjectWithVolunteerNotNull();
		for(Project p: projectList){
			System.out.println("------Project ID----"+p.getProjectId()+"------Project Name-----"+p.getProjectName()+"------Project----Status");
	       for(Task t: p.getTaskList()){
	    	   System.out.println("--Task ID-----"+t.getId()+"-----Name--"+t.getName()+"-Task-Status--"+t.getStatus());
	       }
		}
		assertEquals(1, projectList.size());
	}
	
	@Test
	public void testfindProjectByKeyWordAnLocation(){
		List<Project> projectList = ProjectDAO.findProjectByKeyWordAnLocation("IOWA");
		for(Project p: projectList){
			System.out.println("------Project ID----"+p.getProjectId()+"------Project Name-----"+p.getProjectName()+"-location country--"+p.getLocation().getCity()+"--location city--"+p.getLocation().getCity());
		}
		assertEquals(1, projectList.size());
	}
	
}
