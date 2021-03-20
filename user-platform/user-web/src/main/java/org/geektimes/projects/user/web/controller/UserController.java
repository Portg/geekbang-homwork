package org.geektimes.projects.user.web.controller;

import org.geektimes.context.ComponentContext;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.web.mvc.controller.PageController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * 输出 “Hello,World” Controller
 */
@Path("/user")
public class UserController implements PageController {

	@Resource(name = "bean/UserService")
	private UserService userService;

	@POST
	@Path("/register")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		user.setPhoneNumber(request.getParameter("phoneNumber"));
		boolean flag = userService.register(user);
		if (flag) {
			return "/WEB-INF/jsp/user/register_success.jsp";
		}
		return null;
	}
}
