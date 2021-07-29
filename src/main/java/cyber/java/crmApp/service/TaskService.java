package cyber.java.crmApp.service;

import java.sql.SQLException;
import java.util.List;

import cyber.java.crmApp.dao.TaskDao;
import cyber.java.crmApp.dto.TaskDto;
import cyber.java.crmApp.model.Task;

public class TaskService {

	private TaskDao dao;

	public TaskService() {
		dao = new TaskDao();

	}

	public List<Task> findTask() {
		List<Task> tasks = null;
		try {
			tasks = dao.findTask();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tasks;
	}

	public void deleteById(int id) {
		try {
			dao.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Task findTaskById(int id) {
		Task task = new Task();
		try {
			task = dao.findTaskById(id);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return task;

	}

	public void updateTask(TaskDto taskDto) {
		try {
			dao.updateTask(taskDto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void add(TaskDto taskDto) {
		try {
			dao.addTask(taskDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}