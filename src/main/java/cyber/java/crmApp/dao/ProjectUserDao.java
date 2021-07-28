package cyber.java.crmApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import cyber.java.crmApp.dbconnection.MySqlConnection;
import cyber.java.crmApp.model.Project;
import cyber.java.crmApp.model.Project_User;

public class ProjectUserDao {
	
	public List<Project_User> findAllPU(int id) {
		List<Project_User> project_users = new LinkedList<Project_User>();
//		List<User> users = new LinkedList<>();
		
//		String query = " SELECT u.id as user_id, u.name as name, u.email as email,"
//				+ " u.phone as phone, p.id as project_id, p.name as project_name,pu.join_date,pu.role_description"
//				+ " FROM user u, project p, project_user pu WHERE u.id = pu.user_id and p.id = pu.project_id";
		
		String query = "SELECT p.id as project_id, u.id as user_id, u.name as user_name, p.name as project_name,u.email as email,"
				+ " u.phone as phone,pu.join_date,pu.role_description"
				+ " FROM user u, project p, project_user pu WHERE u.id = pu.user_id and p.id = pu.project_id and project_id = ?";

		try {
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			
			while (resultSet.next()) {
				Project_User project_user = new Project_User();
				project_user.setUser_id(resultSet.getInt("user_id"));
				project_user.setProject_id(resultSet.getInt("project_id"));
				project_user.setProject_name(resultSet.getString("project_name"));
				project_user.setUser_name(resultSet.getString("user_name"));
				project_user.setEmail(resultSet.getString("email"));
				project_user.setPhone(resultSet.getString("phone"));
				project_user.setRole_description(resultSet.getString("role_description"));
				project_user.setJoin_date(resultSet.getDate("join_date"));
				project_users.add(project_user);
			}
			connection.close();

		} catch (Exception e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		}
		return project_users;

	}
	
	
//	private int countTotalUserInProject(List<Project> projects) {
//		String query = "select count(*) from project_user where project_id = ?";
//
//		try {
//			Connection connection = MySqlConnection.getConnection();
//			PreparedStatement statement = connection.prepareStatement(query);
//			statement.setInt(1, projects.get);
//			ResultSet resultSet = statement.executeQuery();
//			
//			
//			while (resultSet.next()) {
//				Project_User project_user = new Project_User();
//				project_user.setUser_id(resultSet.getInt("user_id"));
//				project_user.setProject_id(resultSet.getInt("project_id"));
//				project_user.setProject_name(resultSet.getString("project_name"));
//				project_user.setUser_name(resultSet.getString("user_name"));
//				project_user.setEmail(resultSet.getString("email"));
//				project_user.setPhone(resultSet.getString("phone"));
//				project_user.setRole_description(resultSet.getString("role_description"));
//				project_user.setJoin_date(resultSet.getDate("join_date"));
//				project_users.add(project_user);
//			}
//			connection.close();
//
//		} catch (Exception e) {
//			System.out.println("Unable to connect to database.");
//			e.printStackTrace();
//		}
//		return project_users;
//	}

//
//	private User getUserFromList(List<User> users, int owner_Id) {
//		for(User user : users)
//			if(user.getId() == owner_Id)
//				return user;
//		return null;
//	}
//
	public void deleteById(int project_id, int user_id) throws SQLException {
		Connection connection = MySqlConnection.getConnection();
		String query = "delete from project_user where project_id = ? and user_id = ?";

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, project_id);
			statement.setInt(2, user_id);
			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();

		} finally {
			connection.close();

		}
	}
//
	public void add(Project_User projectdto) throws SQLException {
		String query = "INSERT INTO project_user(project_id, user_id,join_date, role_description)" + "VALUES(?,?,?,?)";

		Connection connection = MySqlConnection.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, projectdto.getProject_id());
			statement.setInt(2, projectdto.getUser_id());
			statement.setDate(3, new java.sql.Date(System.currentTimeMillis()));
			statement.setString(4, projectdto.getRole_description());
			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
//	
//public void update(ProjectDto projectdto) throws SQLException {
//		
//		String query = "UPDATE project set name=?, description=?, start_date=?, end_date=?,owner=? WHERE id=?";
//				
//		Connection connection = MySqlConnection.getConnection();
//		
//		try {
//			PreparedStatement statement = connection.prepareStatement(query);
//			
//			
//			statement.setNString(1, projectdto.getName());
//			statement.setNString(2, projectdto.getDescription());
//			statement.setDate(3, projectdto.getStart_date());
//			statement.setDate(4, (Date) projectdto.getEnd_date());
//			statement.setInt(5, projectdto.getOwner());
//			statement.setInt(6,projectdto.getId());
//			statement.executeUpdate();
//			
//		}catch (SQLException e) {
//			System.out.println("Unable to connect to database.");
//			e.printStackTrace();
//		} finally {
//			connection.close();
//		}
//
//}
//
//public Project findById(int id) {
//	Project project = new Project();
//	
//	Connection connection = MySqlConnection.getConnection();
//	String query ="SELECT * FROM project WHERE id=?";
//	
//	try {
//		PreparedStatement statement = connection.prepareStatement(query);
//		
//		statement.setInt(1, id);
//		ResultSet resultSet = statement.executeQuery();
//		
//		while(resultSet.next()) {
//			
//			project.setId(resultSet.getInt("id"));
//			project.setName(resultSet.getString("name"));
//			project.setDescription(resultSet.getString("description"));
//			project.setStart_date(resultSet.getDate("start_date"));
//			project.setEnd_date(resultSet.getDate("end_date"));
//			project.setOwner(resultSet.getInt("owner"));
//			
//		}
//		connection.close();
//		
//		
//	} catch (Exception e) {
//		// TODO: handle exception
//	}
//	return project;
//}


}