package cyber.java.crmApp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import cyber.java.crmApp.util.UrlConst;
import cyber.java.crmApp.dto.UserCreateDto;
import cyber.java.crmApp.model.User;
import cyber.java.crmApp.service.UserService;
import cyber.java.crmApp.util.JspConst;
import java.util.List;


@WebServlet(name = "userServlet", urlPatterns = {
		UrlConst.USER_DASHBOARD,
//		UrlConst.USER_PROFILE,
		UrlConst.USER_ADD,
		UrlConst.USER_UPDATE,
		UrlConst.USER_DELETE,
		UrlConst.USER_SEARCH
//		UrlConst.PROJECT_STAFF_ADD
})
public class UserServlet extends HttpServlet {
	private UserService service;
	
	@Override
	public void init() throws ServletException {
		super.init();
		service = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlConst.USER_DASHBOARD:
			getUserDashboard(req,resp);
			break;
//		case UrlConst.USER_PROFILE:
//			getUserProfile(req,resp);
//			break;
		case UrlConst.USER_ADD:
			getUserAdd(req,resp);
			break;
		case UrlConst.USER_UPDATE:
			getUserUpdate(req,resp);
			break;
		case UrlConst.USER_DELETE:
			getUserDelete(req,resp);
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlConst.USER_DASHBOARD:
			
			break;
//		case UrlConst.USER_PROFILE:
//			
//			break;
		case UrlConst.USER_ADD:
			postUserAdd(req,resp);
			break;
		case UrlConst.USER_UPDATE:
			postUserUpdate(req,resp);
			break;
		case UrlConst.USER_SEARCH:
			postUserSearch(req,resp);
			break;
		case UrlConst.USER_DELETE:
			
			break;
		}
	}
	
	

	private void postUserSearch(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String emailSearch = req.getParameter("search");
		List<User> userNoPJ = service.findAllUserNoPJ(emailSearch);
		if(userNoPJ != null && !userNoPJ.isEmpty())
			req.setAttribute("userNoPJ", userNoPJ);
		req.getRequestDispatcher(JspConst.PROJECT_STAFF_ADD)
			.forward(req, resp);
//		UserCreateDto dto = extractDtoFromReq(req);
//		service.add(dto);
//		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_STAFF_ADD);
	}

	private void getUserDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		service.deleteById(id);
		
		resp.sendRedirect(req.getContextPath() + UrlConst.USER_DASHBOARD);
	}

	private void getUserUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
//		UserCreateDto userDto = new UserCreateDto();
//		userDto.setId(id);
		User user = service.findUserByID(id);
//		UserCreateDto userDto = extractDtoFromReq(req);
		
		req.setAttribute("user", user);
		req.getRequestDispatcher(JspConst.USER_UPDATE)
			.forward(req, resp);
		
	}

	private void getUserAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher(JspConst.USER_ADD)
			.forward(req, resp);
	}

//	private void getUserProfile(HttpServletRequest req, HttpServletResponse resp) {
//		// TODO Auto-generated method stub
//		
//	}

	private void getUserDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> users = service.findAll();
		
		if(users != null && !users.isEmpty())
			req.setAttribute("users", users);
		
		req.getRequestDispatcher(JspConst.USER_DASHBOARD)
			.forward(req, resp);
	}

	private void postUserAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserCreateDto dto = extractDtoFromReq(req);
		
		service.add(dto);
		
		resp.sendRedirect(req.getContextPath() + UrlConst.USER_DASHBOARD);
	}
	// User Update
	private void postUserUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserCreateDto dto = extractDtoFromReq(req);
		
		service.update(dto);
		
		resp.sendRedirect(req.getContextPath() + UrlConst.USER_DASHBOARD);
		
//		String email = req.getParameter("email");
//		String password = req.getParameter("password");
//		String name = req.getParameter("name");
//		String phone = req.getParameter("phone");
//		String address = req.getParameter("address");
//		int roleId = Integer.parseInt(req.getParameter("role"));
//		System.out.printf("%s %s %s %s %d\n", email, password, phone, address, roleId);
//		
	}
	
	private UserCreateDto extractDtoFromReq(HttpServletRequest req) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		int roleId = Integer.parseInt(req.getParameter("role"));
	    int id = Integer.parseInt(req.getParameter("id"));
//		int id = 2007;
		return new UserCreateDto(email, password, name, address, phone, roleId,id);
	}
	private UserCreateDto extractaddDtoFromReq(HttpServletRequest req) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		int roleId = Integer.parseInt(req.getParameter("role"));
	   
		return new UserCreateDto(email, password, name, address, phone, roleId,0);
	}
}
