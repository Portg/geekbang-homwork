package org.geektimes.projects.user.web.controller;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.impl.UserServiceImpl;
import org.geektimes.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.sql.Connection;

/**
 * 输出 “Hello,World” Controller
 */
@Path("/user")
public class UserController implements PageController {

	@POST
	@Path("/register")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		Connection connection = (Connection) request.getServletContext().getAttribute("connection");
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		user.setPhoneNumber(request.getParameter("phoneNumber"));
		boolean flag = new UserServiceImpl(connection).register(user);
		if (flag) {
			return "/WEB-INF/jsp/user/register_success.jsp";
		} else {
			return "/WEB-INF/jsp/user/register_fail.jsp";
		}
	}
}
