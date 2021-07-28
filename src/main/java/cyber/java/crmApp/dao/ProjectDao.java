package cyber.java.crmApp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import cyber.java.crmApp.dbconnection.MySqlConnection;
import cyber.java.crmApp.dto.ProjectDto;
import cyber.java.crmApp.dto.RoleDto;
import cyber.java.crmApp.model.Project;
import cyber.java.crmApp.model.Role;
import cyber.java.crmApp.model.User;

public class ProjectDao {
	
	public List<Project> findAll() {
		List<Project> projects = new LinkedList<Project>();
		List<User> users = new LinkedList<>();
		
		String query = " SELECT p.id, p.name, p.description, p.start_date, p.end_date, p.owner, u.name as user_name"
				+  " FROM project p, user u WHERE p.owner = u.id";

		try {
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Project project = new Project();
				project.setId(resultSet.getInt("id"));
				project.setName(resultSet.getString("name"));
				project.setDescription(resultSet.getString("description"));
				project.setStart_date(resultSet.getDate("start_date"));
				project.setEnd_date(resultSet.getDate("end_date"));
				
				int owner_Id = resultSet.getInt("owner");
				project.setOwner(owner_Id);
				User user = getUserFromList(users, owner_Id);
				
				if(user == null) {
					user = new User();
					user.setName(resultSet.getNString("user_name"));
					users.add(user);
				}
				
				project.setUser(user);
				projects.add(project);
			}
			connection.close();

		} catch (Exception e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		}
		return projects;

	}

	private User getUserFromList(List<User> users, int owner_Id) {
		for(User user : users)
			if(user.getId() == owner_Id)
				return user;
		return null;
	}

	public void deleteById(int id) throws SQLException {
		Connection connection = MySqlConnection.getConnection();
		String query = "DELETE FROM project WHERE id = ?";

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();

		} finally {
			connection.close();

		}
	}

	public void add(ProjectDto projectdto) throws SQLException {
		String query = "INSERT INTO project( name, description,start_date, end_date,owner)" + "VALUES(?,?,?,?,?)";

		Connection connection = MySqlConnection.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setNString(1, projectdto.getName());
			statement.setNString(2, projectdto.getDescription());
			statement.setNString(3, projectdto.getStart_date().toString());
			statement.setNString(4, projectdto.getEnd_date().toString());
//			statement.setObject(3, projectdto.getStart_date());
//			statement.setObject(4, projectdto.getEnd_date());
			statement.setInt(5, projectdto.getOwner());
			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
public void update(ProjectDto projectdto) throws SQLException {
		
		String query = "UPDATE project set name=?, description=?, start_date=?, end_date=?,owner=? WHERE id=?";
				
		Connection connection = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			
			statement.setNString(1, projectdto.getName());
			statement.setNString(2, projectdto.getDescription());
			statement.setDate(3, projectdto.getStart_date());
			statement.setDate(4, (Date) projectdto.getEnd_date());
			statement.setInt(5, projectdto.getOwner());
			statement.setInt(6,projectdto.getId());
			statement.executeUpdate();
			
		}catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}

}

public Project findById(int id) {
	Project project = new Project();
	
	Connection connection = MySqlConnection.getConnection();
	String query ="SELECT * FROM project WHERE id=?";
	
	try {
		PreparedStatement statement = connection.prepareStatement(query);
		
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			project.setId(resultSet.getInt("id"));
			project.setName(resultSet.getString("name"));
			project.setDescription(resultSet.getString("description"));
			project.setStart_date(resultSet.getDate("start_date"));
			project.setEnd_date(resultSet.getDate("end_date"));
			project.setOwner(resultSet.getInt("owner"));
			
		}
		connection.close();
		
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	return project;
}


}