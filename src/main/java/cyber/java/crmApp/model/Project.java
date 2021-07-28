package cyber.java.crmApp.model;

import java.util.Date;

public class Project {
	private int id;
	private String name;
	private String description;
	private Date start_date;
	private Date  end_date;
	private int owner;
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
//	public String getStart_date() {
//		return start_date;
//	}
//	public void setStart_date(String start_date) {
//		this.start_date = start_date;
//	}
//	public String getEnd_date() {
//		return end_date;
//	}
//	public void setEnd_date(String end_date) {
//		this.end_date = end_date;
//	}
	
	
	public int getOwner() {
		return owner;
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
	public void setOwner(int owner) {
		this.owner = owner;
	}
	
}