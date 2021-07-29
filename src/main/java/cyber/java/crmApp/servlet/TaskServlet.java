package cyber.java.crmApp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import cyber.java.crmApp.dto.TaskDto;
import cyber.java.crmApp.model.Project;
import cyber.java.crmApp.model.Status;
import cyber.java.crmApp.model.Task;
import cyber.java.crmApp.model.User;
import cyber.java.crmApp.service.ProjectService;
import cyber.java.crmApp.service.StatusService;
import cyber.java.crmApp.service.TaskService;
import cyber.java.crmApp.service.UserService;
import cyber.java.crmApp.util.JspConst;
import cyber.java.crmApp.util.UrlConst;

@WebServlet(name="taskServlet", urlPatterns = {
		UrlConst.TASK_DASHBOARD	,
		UrlConst.TASK_ADD,
		UrlConst.TASK_UPDATE,
		UrlConst.TASK_DELETE	
	})
public class TaskServlet extends HttpServlet {
	private TaskService service;
	private ProjectService projectService;
	private UserService userService;
	private StatusService statusService;

	@Override
	public void init() throws ServletException {
		service = new TaskService();
		projectService = new ProjectService();
		userService = new UserService();
		statusService = new  StatusService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		switch (req.getServletPath()) {	
		case UrlConst.TASK_DASHBOARD:
			getTaskDashboard(req,resp);
			break;
		case UrlConst.TASK_ADD:
			getTaskAdd(req,resp);
			break;
		case UrlConst.TASK_UPDATE:
			getTaskUpdate(req,resp);
			break;
		case UrlConst.TASK_DELETE:
			getTaskDelete(req,resp);
			break;			
		}	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		switch(req.getServletPath()) {
		
		case UrlConst.TASK_DASHBOARD:
			break;
		
		case UrlConst.TASK_ADD:
			postTaskAdd(req,resp);
			break;
		case UrlConst.TASK_UPDATE:
			postTaskUpdate(req,resp);
			break;
		case UrlConst.TASK_DELETE:
			
			break;
		}			
	}
	
	private void getTaskDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Task> tasks = service.findTask();
		
		if (tasks != null && !tasks.isEmpty())
			req.setAttribute("tasks", tasks);

		req.getRequestDispatcher(JspConst.TASK_DASHBOARD).forward(req, resp);
		
	}
	private void getTaskDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		service.deleteById(id);
		
		resp.sendRedirect(req.getContextPath() + UrlConst.TASK_DASHBOARD);
		
	}
	private void getTaskUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		 Task task =new  Task();
		 task = service.findTaskById(id);
		 
		 req.setAttribute("task", task );
		 
		 // infor load to UI
		 List<Project> projects = projectService.findAll();
			
			if(projects != null && !projects.isEmpty())
				req.setAttribute("projects", projects);
			
		List<User> users = userService.findAll();
			
			if(users != null && !users.isEmpty())
				req.setAttribute("users", users);
			
		List<Status > statuss = statusService.findAll();
			
			if(statuss != null && !statuss.isEmpty())
				req.setAttribute("statuss", statuss);
			
		req.getRequestDispatcher(JspConst.TASK_UPDATE)
		.forward(req, resp);
		
	}
	private void getTaskAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 List<Project> projects = projectService.findAll();
			if(projects != null && !projects.isEmpty())
				req.setAttribute("projects", projects);
			
		List<User> users = userService.findAll();
			if(users != null && !users.isEmpty())
				req.setAttribute("users", users);
			
			
		List<Status > statuss = statusService.findAll();
			if(statuss != null && !statuss.isEmpty())
				req.setAttribute("statuss", statuss);
		
		req.getRequestDispatcher(JspConst.TASK_ADD).forward(req, resp);
		
	}
	
	private void postTaskUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TaskDto taskDto =  extractUpdateDtoStaskFromReq(req);
		service.updateTask(taskDto) ;
		resp.sendRedirect(req.getContextPath() +  UrlConst.TASK_DASHBOARD);

	}
	private void postTaskAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		TaskDto taskDto = extractAddDtoStaskFromReq(req);
		service.add(taskDto);
		resp.sendRedirect(req.getContextPath() + UrlConst.TASK_DASHBOARD);
	}
	
	private TaskDto extractUpdateDtoStaskFromReq(HttpServletRequest req) {
		int id = -1;
		if (req.getParameter("id") != null) {
			id = Integer.parseInt(req.getParameter("id"));
		}
		
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		String start_date = req.getParameter("start_date");
		String end_date = req.getParameter("end_date");
		int projectId = Integer.parseInt(req.getParameter("project1"));
		int userId = Integer.parseInt(req.getParameter("user1"));
		int statusId = Integer.parseInt(req.getParameter("status1"));

		return new TaskDto(id, name, description, start_date, end_date, projectId, userId, statusId);

	}
	private TaskDto extractAddDtoStaskFromReq(HttpServletRequest req) {
		
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		String start_date = req.getParameter("start_date");
		String end_date = req.getParameter("end_date");
		int projectId = Integer.parseInt(req.getParameter("project1"));
		int userId = Integer.parseInt(req.getParameter("user1"));
		int statusId = Integer.parseInt(req.getParameter("status1"));

		return new TaskDto(0, name, description, start_date, end_date, projectId, userId, statusId);

	}

	}