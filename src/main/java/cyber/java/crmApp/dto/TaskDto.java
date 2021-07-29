package cyber.java.crmApp.dto;

import cyber.java.crmApp.model.Task;

public class TaskDto {

	private int id;
	private String name;
	private String description;
	private String start_date;
	private String end_date;
	private int projectId;
	private int userId;
	private int statusId;

	public TaskDto(int id, String name, String description, String start_date, String end_date, int projectId,
			int userId, int statusId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.projectId = projectId;
		this.userId = userId;
		this.statusId = statusId;
	}

	public TaskDto(Task task) {
		this.setId(task.getId());
		this.name = task.getName();
		this.description = task.getDescription();
		this.start_date = task.getStart_date();
		this.end_date = task.getEnd_date();
		this.projectId = task.getProject().getId();
		this.userId = task.getUser().getId();
		this.statusId = task.getStatus().getId();
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

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

}