package cyber.java.crmApp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cyber.java.crmApp.dto.UserCreateDto;
import cyber.java.crmApp.model.User;
import cyber.java.crmApp.service.UserService;
import cyber.java.crmApp.util.JspConst;
import cyber.java.crmApp.util.ServletConst;
import cyber.java.crmApp.util.UrlConst;

//@WebServlet(name="homeservlet", urlPatterns = {"/home", "/home/level2"})
@WebServlet(name= ServletConst.HOME, urlPatterns = {
		UrlConst.HOME,
		UrlConst.USER_EDIT,
		UrlConst.USER_PROFILE
})

public class HomeServlet extends HttpServlet {
	private UserService service;

	@Override
	public void init() throws ServletException {
		super.init();
		service = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		HttpSession currentSession = req.getSession();
		String email = (String)currentSession.getAttribute("email");
		req.setAttribute("email", email);
		switch(req.getServletPath()) {
		case UrlConst.USER_PROFILE:
			getUserProfile(req,resp);
			break;
		case UrlConst.USER_EDIT:
			getUserEdit(req,resp);
			break;
		default:
			req.getRequestDispatcher(JspConst.HOME).forward(req, resp);
		}
	}

	private void getUserEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = (String) req.getAttribute("email");
		User user  = service.findUserByEmail(email);
		
		req.setAttribute("user", user);
		req.getRequestDispatcher(JspConst.USER_EDIT)
			.forward(req, resp);
	}

	private void getUserProfile(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlConst.USER_PROFILE:
			break;
		case UrlConst.USER_EDIT:
			postUserProfileEdit(req,resp);
			break;
		}
	}

	private void postUserProfileEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserCreateDto dto = extractDtoFromReq(req);
		service.updateProfile(dto);
		resp.sendRedirect(req.getContextPath() + UrlConst.HOME);
	}
	
	private UserCreateDto extractDtoFromReq(HttpServletRequest req) {
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		int id = Integer.parseInt(req.getParameter("id"));
		return new UserCreateDto(email, name, address, phone,id);
	}
}
