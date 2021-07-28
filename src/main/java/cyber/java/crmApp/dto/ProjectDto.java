package cyber.java.crmApp.dto;

import java.sql.Date;

import cyber.java.crmApp.model.Project;

public class ProjectDto {
	private int id;
	private String name;
	private String description;
	private Date start_date;
	private Date end_date;
	private int owner;
	
	

	public ProjectDto(int id, String name, String description, Date startDate, Date endDate, int owner) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.start_date = startDate;
		this.end_date = endDate;
		this.owner = owner;
	}

	public ProjectDto() {
	}
	
	public ProjectDto(Project project) {
		this.setId(project.getId());
		this.name = project.getName();
		this.description = project.getDescription();
		this.start_date = (Date) project.getStart_date();
		this.end_date = (Date) project.getEnd_date();
		this.owner = project.getOwner();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
}