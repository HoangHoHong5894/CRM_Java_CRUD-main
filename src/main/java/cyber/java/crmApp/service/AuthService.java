package cyber.java.crmApp.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;


import cyber.java.crmApp.dao.AuthDao;
import cyber.java.crmApp.dao.UserDao;
import cyber.java.crmApp.dto.UserLoginDto;
import cyber.java.crmApp.dbconnection.MySqlConnection;

public class AuthService {
	private AuthDao dao;
	
	public AuthService() {
		dao = new AuthDao();
	}

	public boolean login(String username,String password)
	{
		UserLoginDto dto = null;
		
		try {
			dto = dao.findUserLogin(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(dto == null)
			return false;
		
		return BCrypt.checkpw(password, dto.getPassword());
	}
}
