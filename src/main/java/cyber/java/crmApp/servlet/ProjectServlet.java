package cyber.java.crmApp.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cyber.java.crmApp.dto.ProjectDto;
import cyber.java.crmApp.model.Project;
import cyber.java.crmApp.model.Project_User;
import cyber.java.crmApp.model.User;
import cyber.java.crmApp.service.ProjectService;
import cyber.java.crmApp.service.ProjectUserService;
import cyber.java.crmApp.service.UserService;
import cyber.java.crmApp.util.JspConst;
import cyber.java.crmApp.util.UrlConst;


@WebServlet(name = "projectServlet", urlPatterns = {
		UrlConst.PROJECT_DASHBOARD,
		UrlConst.PROJECT_MANAGE,
		UrlConst.PROJECT_ADD,
		UrlConst.PROJECT_UPDATE,
		UrlConst.PROJECT_DELETE,
		UrlConst.PROJECT_STAFF,
		UrlConst.PROJECT_STAFF_ADD,
		UrlConst.PROJECT_STAFF_REMOVE,
		UrlConst.PROJECT_STAFF_UPDATE
})
public class ProjectServlet extends HttpServlet{
private ProjectService service;
private UserService userService;
private ProjectUserService projectUserService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		service = new ProjectService();
		userService = new UserService();
		projectUserService = new ProjectUserService();
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.PROJECT_DASHBOARD:
			getDashboard(req,resp);
			break;
		case UrlConst.PROJECT_MANAGE:
			
			break;
		case UrlConst.PROJECT_ADD:
			getProjectAdd(req,resp);
			break;
		case UrlConst.PROJECT_UPDATE:
			getProjectUpdate(req,resp);
			break;
		case UrlConst.PROJECT_DELETE:
			getProjectDelete(req,resp);
			break;
		case UrlConst.PROJECT_STAFF:
			getProject_Staff(req,resp);
			break;
		case UrlConst.PROJECT_STAFF_ADD:
			getProject_Staff_Add(req,resp);
			break;
		case UrlConst.PROJECT_STAFF_UPDATE:
			getProject_Staff_Update(req,resp);
			break;
		case UrlConst.PROJECT_STAFF_REMOVE:
			getProject_Staff_Remove(req,resp);
			break;
		default:
			
			break;
		}
	}
	
	

	private void getProject_Staff_Update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		 List<Project> projects = new ArrayList<>();
		 projects = service.findAll();
		 req.setAttribute("projects", projects);
		 
		 User user = userService.findUserById(id);
			
			if(user != null)
				req.setAttribute("user", user);
			req.setAttribute("role", user.getRole());
				
		req.getRequestDispatcher(JspConst.PROJECT_STAFF_UPDATE)
		.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.PROJECT_DASHBOARD:
		//	getDashboard(req,resp);
			break;
		case UrlConst.PROJECT_MANAGE:
			postProject_Manage(req,resp);
			break;
		case UrlConst.PROJECT_ADD:
			try {
				postProjectAdd(req,resp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case UrlConst.PROJECT_UPDATE:
			try {
				postProject_Update(req,resp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case UrlConst.PROJECT_DELETE:
			postProject_Delete(req,resp);
			break;
		case UrlConst.PROJECT_STAFF:
			
			break;
		case UrlConst.PROJECT_STAFF_ADD:
			
			break;
		case UrlConst.PROJECT_STAFF_UPDATE:
			postRemoveProjectStaffUpdate(req, resp);
			break;
		case UrlConst.PROJECT_STAFF_REMOVE:
			postRemoveProjectStaff(req,resp);
			break;
		default:
			
			break;
		}
	}




	private void postRemoveProjectStaffUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Project_User projectUserAdd = new Project_User();
		int projectId = Integer.parseInt(req.getParameter("project_id"));
		int userId = Integer.parseInt(req.getParameter("id"));
//		int roleId = Integer.parseInt(req.getParameter("role"));
		projectUserAdd.setProject_id(projectId);
		projectUserAdd.setUser_id(userId);
//		projectUserAdd.setRole_description(roleId);
		projectUserService.add(projectUserAdd);
		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_STAFF_ADD);
	}

	private void postRemoveProjectStaff(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

	private void getDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Project> projects = service.findAll();
		
		if(projects != null && !projects.isEmpty())
			req.setAttribute("projects", projects);
		req.getRequestDispatcher(JspConst.PROJECT_DASHBOARD)
			.forward(req, resp);
	}
	

	

	private void getProject_Staff_Remove(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int projecId = Integer.parseInt(req.getParameter("project_id"));
		int userId = Integer.parseInt(req.getParameter("user_id"));
		projectUserService.deleteById(projecId,userId);
		
		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_STAFF+"?id="+projecId);
	}

	private void getProject_Staff_Add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> userNoPJ = userService.findAllUserNoPJ();
		if(userNoPJ != null && !userNoPJ.isEmpty())
			req.setAttribute("userNoPJ", userNoPJ);
		req.getRequestDispatcher(JspConst.PROJECT_STAFF_ADD)
			.forward(req, resp);
		
	}

	private void getProject_Staff(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		 List<Project_User> project_Users = projectUserService.findAllPU(id);
			
			if(project_Users != null && !project_Users.isEmpty())
				req.setAttribute("project_Users", project_Users);
		
		req.getRequestDispatcher(JspConst.PROJECT_STAFF)
		.forward(req, resp);
		
	}

	private void getProjectDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		service.deleteById(id);
		
		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DASHBOARD);
		
	}
	private void getProjectAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 // infor from owner user load to UI
		 List<User> users = userService.findAll();
			
			if(users != null && !users.isEmpty())
				req.setAttribute("users", users);
		
		req.getRequestDispatcher(JspConst.PROJECT_ADD)
		.forward(req, resp);
	}

	private void getProjectUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		 Project project =new  Project();
		 project = service.findById(id);
		 
		 req.setAttribute("project", project );
		 
		 // infor from owner user load to UI
		 List<User> users = userService.findAll();
			
			if(users != null && !users.isEmpty())
				req.setAttribute("users", users);
				req.setAttribute("userChoosen", project.getOwner());
		req.getRequestDispatcher(JspConst.PROJECT_UPDATE)
		.forward(req, resp);
	}
	
	private void postProject_Delete(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
	}

	private void postProject_Manage(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
	}

	private void postProjectAdd(HttpServletRequest req, HttpServletResponse resp) throws ParseException, IOException {
		ProjectDto projectdto = extractAddDtoFromReq(req);
		service.add(projectdto);
		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DASHBOARD);
	}
	
	private void postProject_Update(HttpServletRequest req, HttpServletResponse resp) throws ParseException, IOException {
		ProjectDto projectdto = extractUpdateDtoFromReq(req);
		service.update(projectdto);
		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DASHBOARD);
	}


	// Post Add Extract
	private ProjectDto extractAddDtoFromReq(HttpServletRequest req) throws ParseException  {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		String start_date =req.getParameter("start_date");
		String end_date = req.getParameter("end_date");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = sdf.parse(start_date);
		Date endDate = sdf.parse(end_date);
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		int owner = Integer.parseInt(req.getParameter("owner"));
		
		return new ProjectDto(0, name, description, sqlStartDate, sqlEndDate, owner);
		


}
	private ProjectDto extractDtoFromReq(HttpServletRequest req) throws ParseException  {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		
		String start_date = req.getParameter("start_date");
		String end_date = req.getParameter("end_date");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = (Date) sdf.parse(start_date);
		Date endDate = (Date) sdf.parse(end_date);
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		int owner = Integer.parseInt(req.getParameter("owner"));
	    int id = Integer.parseInt(req.getParameter("id"));
		return new ProjectDto(id,name, description, sqlStartDate, sqlEndDate, owner);

}
	private ProjectDto extractUpdateDtoFromReq(HttpServletRequest req) throws ParseException  {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		String start_date =req.getParameter("start_date");
		String end_date = req.getParameter("end_date");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date utilStartDate = sdf.parse(start_date);
		Date utilEndDate = sdf.parse(end_date);
		
		java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(utilEndDate.getTime());
		
		int owner = Integer.parseInt(req.getParameter("owner"));
	    int id = Integer.parseInt(req.getParameter("id"));
	    Project project =new  Project();
	    project.setId(id);
	    project.setName(name);
	    project.setDescription(description);
	    project.setStart_date(sqlStartDate);
	    project.setEnd_date(sqlEndDate);
	    project.setOwner(owner);
	   
		return new ProjectDto(project);

}
}
