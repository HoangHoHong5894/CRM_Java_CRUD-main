package cyber.java.crmApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;




import cyber.java.crmApp.dbconnection.MySqlConnection;
import cyber.java.crmApp.dto.UserCreateDto;
import cyber.java.crmApp.model.Role;
import cyber.java.crmApp.model.User;



public class UserDao {

	public List<User> findAll() throws SQLException {
		List<User> users = new LinkedList<>();
		List<Role> roles = new ArrayList<>();
		
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT u.id as id, u.name as name, u.email as email, "
				+ "u.phone as phone, r.id as role_id, r.name as role_name, r.description as role_description "
				+ "FROM user u, role r WHERE u.role_id = r.id";
		  
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				User user = new User();
				
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setEmail(resultSet.getString("email"));
				user.setPhone(resultSet.getString("phone"));
				// role_id
				int roleId = resultSet.getInt("role_id");
				// role
				Role role = getRoleFromList(roles, roleId);
				
				if(role == null) {
					role = new Role();
					role.setId(resultSet.getInt("role_id"));
					role.setName(resultSet.getString("role_name"));
					role.setDescription(resultSet.getString("role_description"));
					
					roles.add(role);
				}
				
				user.setRole(role);
				
				users.add(user);
			}
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return users;
	}
	
	public List<User> findListUserById() throws SQLException {
		List<User> users = new LinkedList<>();
		List<Role> roles = new ArrayList<>();
		
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT p.id as project_id, u.id as user_id, u.name as user_name, p.name as project_name,u.email as email,\n"
				+ "				u.phone as phone,pu.join_date,pu.role_description"
				+ "				FROM user u, project p, project_user pu WHERE u.id = pu.user_id and p.id = pu.project_id";
		  
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				User user = new User();
				
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setEmail(resultSet.getString("email"));
				user.setPhone(resultSet.getString("phone"));
				// role_id
				int roleId = resultSet.getInt("role_id");
				// role
				Role role = getRoleFromList(roles, roleId);
				
				if(role == null) {
					role = new Role();
					role.setId(resultSet.getInt("role_id"));
					role.setName(resultSet.getString("role_name"));
					role.setDescription(resultSet.getString("role_description"));
					
					roles.add(role);
				}
				
				user.setRole(role);
				
				users.add(user);
			}
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return users;
	}
	
	
	public User findUserById(int id) throws SQLException {
		User user = new User();
		Role role = new Role();
		
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT u.id as id, u.name as name, u.email as email,"
				+ "	u.phone as phone, u.password, u.address, r.id as role_id, r.name as role_name, r.description as role_description "
				+ "	FROM user u, role r WHERE u.role_id = r.id and u.id = ?";
		  
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setEmail(resultSet.getString("email"));
				user.setPhone(resultSet.getString("phone"));
				user.setAddress(resultSet.getString("address"));
				user.setPassword(resultSet.getString("password"));
				// role_id
				int roleId = resultSet.getInt("role_id");
				role.setId(roleId);
				user.setRole(role);
			}
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return user;
	}
	
	public User findUserByEmail(String email) throws SQLException {
		User user = new User();
		Role role = new Role();
		
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT u.id as id, u.name as name, u.email as email,"
				+ "	u.phone as phone, u.address, u.password, r.id as role_id, r.name as role_name, r.description as role_description "
				+ "	FROM user u, role r WHERE u.role_id = r.id and u.email = ?";
		  
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setEmail(resultSet.getString("email"));
				user.setPhone(resultSet.getString("phone"));
				user.setAddress(resultSet.getString("address"));
				user.setPassword(resultSet.getString("password"));
				// role_id
				int roleId = resultSet.getInt("role_id");
				role.setId(roleId);
				user.setRole(role);
			}
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return user;
	}
	
//	public boolean findUserExist(List<User> users, String email)
//	{
//		for(User user : users)
//			if(user.getEmail() == email)
//				return false;
//		return true;
//	}
	public void deleteById(int id) throws SQLException {
		String query = "DELETE FROM user WHERE id = ?";
		Connection connection = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
	}
	
	private Role getRoleFromList(List<Role> roles, int roleId) {
		for(Role role : roles)
			if(role.getId() == roleId)
				return role;
		return null;
	}

	public void add(UserCreateDto dto) throws SQLException {
		String query = "INSERT INTO user(email, password, name, address, phone, role_id)"
				+ "VALUES(?,?,?,?,?,?)";
		
		Connection connection = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setNString(1, dto.getEmail());
			statement.setNString(2, dto.getPassword());
			statement.setNString(3, dto.getName());
			statement.setNString(4, dto.getAddress());
			statement.setNString(5, dto.getPhone());
			statement.setInt(6, dto.getRoleId());
			
			statement.executeUpdate();
			
		}catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public void update(UserCreateDto dto) throws SQLException {
		String query = "UPDATE user set email =?, password =?,name=?, address=?, phone=?, role_id=? WHERE id=?";
				
		Connection connection = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setNString(1, dto.getEmail());
			statement.setNString(2, dto.getPassword());
			statement.setNString(3, dto.getName());
			statement.setNString(4, dto.getAddress());
			statement.setNString(5, dto.getPhone());
			statement.setInt(6, dto.getRoleId());
			statement.setInt(7, dto.getId());
			
			
			statement.executeUpdate();
			
		}catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
	public void updateProfile(UserCreateDto dto) throws SQLException {
		String query = "UPDATE user set email =?,name=?, address=?, phone=? WHERE id=?";
				
		Connection connection = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setNString(1, dto.getEmail());
			statement.setNString(2, dto.getName());
			statement.setNString(3, dto.getAddress());
			statement.setNString(4, dto.getPhone());
			statement.setInt(5, dto.getId());
			statement.executeUpdate();
			
		}catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public List<User> findAllUserNoPJ() throws SQLException {
		List<User> users = new LinkedList<>();
		List<Role> roles = new ArrayList<>();
		
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT u.id as id, u.name as name, u.email as email,"
				+ " u.phone as phone, r.id as role_id, r.name as role_name, r.description as role_description "
				+ " FROM user u, role r WHERE u.role_id = r.id and u.id not in (select user_id from project_user) and r.id<>1";
		  
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				User user = new User();
				
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setEmail(resultSet.getString("email"));
				user.setPhone(resultSet.getString("phone"));
				// role_id
				int roleId = resultSet.getInt("role_id");
				// role
				Role role = getRoleFromList(roles, roleId);
				
				if(role == null) {
					role = new Role();
					role.setId(resultSet.getInt("role_id"));
					role.setName(resultSet.getString("role_name"));
					role.setDescription(resultSet.getString("role_description"));
					
					roles.add(role);
				}
				
				user.setRole(role);
				
				users.add(user);
			}
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return users;
	}
	
	public List<User> searchUserNoPJ(String email) throws SQLException {
		List<User> users = new LinkedList<>();
		List<Role> roles = new ArrayList<>();
		
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT u.id as id, u.name as name, u.email as email,"
				+ " u.phone as phone, r.id as role_id, r.name as role_name, r.description as role_description "
				+ " FROM user u, role r WHERE u.role_id = r.id and u.id not in (select user_id from project_user) and r.id<>1 and "
				+ "u.name like ? ";
		  
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, "%" +email + "%");
//			statement.setString(2, "%" +email + "%");
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				User user = new User();
				
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setEmail(resultSet.getString("email"));
				user.setPhone(resultSet.getString("phone"));
				// role_id
				int roleId = resultSet.getInt("role_id");
				// role
				Role role = getRoleFromList(roles, roleId);
				
				if(role == null) {
					role = new Role();
					role.setId(resultSet.getInt("role_id"));
					role.setName(resultSet.getString("role_name"));
					role.setDescription(resultSet.getString("role_description"));
					
					roles.add(role);
				}
				
				user.setRole(role);
				
				users.add(user);
			}
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return users;
	}
	

	

}
