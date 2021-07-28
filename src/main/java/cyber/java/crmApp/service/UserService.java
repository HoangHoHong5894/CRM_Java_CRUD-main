package cyber.java.crmApp.service;

import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import cyber.java.crmApp.dao.UserDao;
import cyber.java.crmApp.dto.UserCreateDto;
import cyber.java.crmApp.model.User;
import cyber.java.crmApp.util.PwdUtils;


public class UserService {
private UserDao dao;
	
	public UserService() {
		dao = new UserDao();
	}

	public List<User> findAll() {
		List<User> users = null;
		try {
			users = dao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public User findUserById(int id) {
		User users = new User();
		try {
			users = dao.findUserById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public List<User> findListUserByID() {
		List<User> users = null;
		try {
			users = dao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public void deleteById(int id) {
		try {
			dao.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean findUserExist( String email) throws SQLException
	{
		boolean exist = false;
		List<User> users = null;
		users = dao.findAll();
		for(User user : users)
		{
			if(user.getEmail().equalsIgnoreCase(email))
			{
				exist = true;
				return exist;
			}
		}
		return exist;
	}

	public void add(UserCreateDto dto) {
		String hashedPwd = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		dto.setPassword(hashedPwd);
		
		try {
			dao.add(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(UserCreateDto dto) {
		String hashedPwd = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		dto.setPassword(hashedPwd);
		try {
			dao.update(dto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<User> findAllUserNoPJ() {
		List<User> users = null;
		try {
			users = dao.findAllUserNoPJ();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

}
