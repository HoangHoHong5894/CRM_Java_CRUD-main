package cyber.java.crmApp.service;

import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import cyber.java.crmApp.dao.RoleDao;
import cyber.java.crmApp.dto.RoleDto;

import cyber.java.crmApp.model.Role;

public class RoleService {
	private RoleDao roleDao;
		
		public RoleService(){
		roleDao = new RoleDao();
	}
		public List<Role> findAll(){
			List<Role> roles = null;
			try {
				roles = roleDao.findAll();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return roles;
		}
		
		
		
		public Role findById(int id) {
			Role role = new Role();
			
			try {
				role = roleDao.findById(id);
			} catch (Exception e) {
				// TODO: handle exception
			}
			return role;
		}
		
		
		public void deleteById(int id) {
		try {
			roleDao.deleteById(id);
			} catch (SQLException e) {
			e.printStackTrace();
			}
			
		}
		public void add(RoleDto rodto) {
//			String hashedPwd = BCrypt.hashpw(rodto.getPassword(), BCrypt.gensalt());
//			rodto.setPassword(hashedPwd);
//			
			try {
				roleDao.add(rodto);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public void update(RoleDto rodto) {
//			String hashedPwd = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
//			dto.setPassword(hashedPwd);
			try {
				roleDao.update(rodto);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	

}
