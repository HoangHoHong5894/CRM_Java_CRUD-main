package cyber.java.crmApp.service;

import java.sql.SQLException;
import java.util.List;

import cyber.java.crmApp.dao.ProjectUserDao;
import cyber.java.crmApp.model.Project;
import cyber.java.crmApp.model.Project_User;



public class ProjectUserService {
	private ProjectUserDao projectuserDao;
	
	public ProjectUserService() {
		projectuserDao = new ProjectUserDao();
	}
	
	public List<Project_User> findAllPU(int id){
		List<Project_User> projects = null;
		try {
			projects = projectuserDao.findAllPU(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return projects;
	}
//	public Project countTotalUserInProject(int id) {
//		Project project = new Project();
//		
//		try {
//			for()
//			project = projectuserDao.findById(id);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return project;
//	}
//	
//	
	 public void deleteById(int project_id, int user_id) {
			try {
				projectuserDao.deleteById(project_id,user_id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	 public void add(Project_User projectdto) {
			
			try {
				projectuserDao.add(projectdto);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//	 public void update(ProjectDto projectdto) {
//
//			try {
//				projectDao.update(projectdto);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}	

}
