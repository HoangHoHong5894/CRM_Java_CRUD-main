package cyber.java.crmApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cyber.java.crmApp.dbconnection.MySqlConnection;
import cyber.java.crmApp.dto.TaskDto;
import cyber.java.crmApp.model.Project;
import cyber.java.crmApp.model.Role;
import cyber.java.crmApp.model.Status;
import cyber.java.crmApp.model.Task;
import cyber.java.crmApp.model.User;

public class TaskDao {

	public List<Task> findTask() throws SQLException {
		List<Task> tasks = new LinkedList<>();
		List<Project> projects = new ArrayList<>();
		List<Status> listStatus = new ArrayList<>();
		List<User> users = new ArrayList<>();
		List<Role> roles = new ArrayList<>();
		Connection connection = MySqlConnection.getConnection();

		String query = "SELECT t.id as id, t.name as name, t.description as description, t.start_date as start_date, t.end_date as end_date,"
				+ " p.id as project_id, p.name as project_name, p.description as project_description, p.start_date as project_start_date, p.end_date as project_end_date,p.owner as project_owner,"
				+ "s.id as status_id, s.description as status_description, s.name as status_name,"
				+ "u.id as user_id, u.name as user_name, u.email as user_email,u.password as user_password, u.phone as user_phone,  u.address as user_address,"
				+ " r.id as role_id, r.name as role_name, r.description as role_description "
				+ " FROM  task t, project p, status s,user u,role r"
				+ " WHERE t. project_id = p.id and t.user_id =u.id and t.status_id=s.id and u.role_id = r.id ORDER BY t.id ASC";

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Task task = new Task();
				task.setId(resultSet.getInt("id"));
				task.setName(resultSet.getNString("name"));
				task.setDescription(resultSet.getString("description"));
				task.setStart_date(resultSet.getString("start_date"));
				task.setEnd_date(resultSet.getString("end_date"));

				int projectId = resultSet.getInt("project_id");
				Project project = getProjectFromList(projects, projectId);

				if (project == null) {
					project = new Project();
					project.setId(resultSet.getInt("project_id"));
					project.setName(resultSet.getNString("project_name"));
					project.setDescription(resultSet.getNString("project_description"));
					project.setStart_date(resultSet.getDate("project_start_date"));
					project.setEnd_date(resultSet.getDate("project_end_date"));
					project.setOwner(resultSet.getInt("project_owner"));
					projects.add(project);

				}

				int statusId = resultSet.getInt("status_id");
				Status status = getStatusFromList(listStatus, statusId);
				if (status == null) {
					status = new Status();
					status.setId(resultSet.getInt("status_id"));
					status.setDescription(resultSet.getNString("status_description"));
					status.setName(resultSet.getNString("status_name"));
					listStatus.add(status);
				}
				int userId = resultSet.getInt("user_Id");
				User user = getUserFromList(users, userId);
				if (user == null) {

					user = new User();
					user.setId(resultSet.getInt("user_id"));
					user.setName(resultSet.getNString("user_name"));
					user.setEmail(resultSet.getString("user_email"));
					user.setPassword(resultSet.getString("user_password"));
					user.setPhone(resultSet.getString("user_phone"));
					user.setAddress(resultSet.getNString("user_address"));

					int roleId = resultSet.getInt("role_id");
					Role role = getRoleFromList(roles, roleId);

					if (role == null) {
						role = new Role();
						role.setId(resultSet.getInt("role_id"));
						role.setName(resultSet.getString("role_name"));
						role.setDescription(resultSet.getString("role_description"));

						roles.add(role);
					}
					user.setRole(role);
					users.add(user);

				}

				task.setProject(project);
				task.setStatus(status);
				task.setUser(user);
				tasks.add(task);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return tasks;
	}

	private Role getRoleFromList(List<Role> roles, int roleId) {
		for (Role role : roles)
			if (role.getId() == roleId)
				return role;
		return null;
	}

	private User getUserFromList(List<User> users, int userId) {
		for (User user : users)
			if (user.getId() == userId)
				return user;
		return null;
	}

	private Status getStatusFromList(List<Status> listStatus, int statusId) {
		for (Status status : listStatus)
			if (status.getId() == statusId)
				return status;
		return null;
	}

	private Project getProjectFromList(List<Project> projects, int projectId) {
		for (Project project : projects)
			if (project.getId() == projectId)
				return project;
		return null;

	}

	public void deleteById(int id) throws SQLException {
		String query = "DELETE FROM task WHERE id=?";
		Connection connection = MySqlConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public Task findTaskById(int id) throws SQLException {
		Task task = new Task();
		Project project = new Project();
		Status status = new Status();
		User user = new User();
		Role role = new Role();

		String query = "SELECT t.id as id, t.name as name, t.description as description, t.start_date as start_date, t.end_date as end_date,"
				+ " p.id as project_id, p.name as project_name, p.description as project_description, p.start_date as project_start_date, p.end_date as project_end_date,p.owner as project_owner,"
				+ "s.id as status_id, s.description as status_description, s.name as status_name,"
				+ "u.id as user_id, u.name as user_name, u.email as user_email,u.password as user_password, u.phone as user_phone,  u.address as user_address,"
				+ " r.id as role_id, r.name as role_name, r.description as role_description "
				+ " FROM  task t, project p, status s,user u,role r"
				+ " WHERE t. project_id = p.id and t.user_id =u.id and t.status_id=s.id and u.role_id = r.id and t.id=?";
		Connection connection = MySqlConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				task.setId(resultSet.getInt("id"));
				task.setName(resultSet.getNString("name"));
				task.setDescription(resultSet.getString("description"));
				task.setStart_date(resultSet.getString("start_date"));
				task.setEnd_date(resultSet.getString("end_date"));

				project.setId(resultSet.getInt("project_id"));
				project.setName(resultSet.getNString("project_name"));
				project.setDescription(resultSet.getNString("project_description"));
				project.setStart_date(resultSet.getDate("project_start_date"));
				project.setEnd_date(resultSet.getDate("project_end_date"));
				project.setOwner(resultSet.getInt("project_owner"));

				status.setId(resultSet.getInt("status_id"));
				status.setDescription(resultSet.getNString("status_description"));
				status.setName(resultSet.getNString("status_name"));

				user.setId(resultSet.getInt("user_id"));
				user.setName(resultSet.getNString("user_name"));
				user.setEmail(resultSet.getString("user_email"));
				user.setPassword(resultSet.getString("user_password"));
				user.setPhone(resultSet.getString("user_phone"));
				user.setAddress(resultSet.getNString("user_address"));

				role.setId(resultSet.getInt("role_id"));
				role.setName(resultSet.getString("role_name"));
				role.setDescription(resultSet.getString("role_description"));

				user.setRole(role);

				task.setProject(project);
				task.setStatus(status);
				task.setUser(user);

			}
			return task;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public void updateTask(TaskDto taskDto) throws SQLException {
		String query = "UPDATE task SET name = ?,description = ?,start_date = ?,end_date = ?,project_id = ?,status_id = ?,user_id = ? WHERE id = ?";
		Connection connection = MySqlConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setNString(1, taskDto.getName());
			statement.setString(2, taskDto.getDescription());
			statement.setString(3, taskDto.getStart_date());
			statement.setString(4, taskDto.getEnd_date());
			statement.setInt(5, taskDto.getProjectId());
			statement.setInt(6, taskDto.getStatusId());
			statement.setInt(7, taskDto.getUserId());
			statement.setInt(8, taskDto.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

	}

	public void addTask(TaskDto taskDto) throws SQLException {
		String query = "INSERT INTO task(name, description, start_date, end_date, project_id, user_id, status_id) VALUES(?,?,?,?,?,?,?)";
		Connection connection = MySqlConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setNString(1, taskDto.getName());
			statement.setString(2, taskDto.getDescription());
			statement.setString(3, taskDto.getStart_date());
			statement.setString(4, taskDto.getEnd_date());
			statement.setInt(5, taskDto.getProjectId());
			statement.setInt(6, taskDto.getUserId());
			statement.setInt(7, taskDto.getStatusId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}

	}
}