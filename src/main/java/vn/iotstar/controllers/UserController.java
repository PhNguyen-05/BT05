package vn.iotstar.controllers;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.User;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.impl.UserService;

@WebServlet(urlPatterns = { "/admin/users", "/admin/user/edit", "/admin/user/update", "/admin/user/insert",
		"/admin/user/add", "/admin/user/delete" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		if (url.contains("users")) {
			List<User> list = userService.findAll();
			req.setAttribute("listuser", list);
			req.getRequestDispatcher("/views/admin/user-list.jsp").forward(req, resp);
		} else if (url.contains("/admin/user/edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			User u = userService.findById(id);
			req.setAttribute("user", u);
			req.getRequestDispatcher("/views/admin/user-edit.jsp").forward(req, resp);
		} else if (url.contains("/admin/user/add")) {
			req.getRequestDispatcher("/views/admin/user-add.jsp").forward(req, resp);
		} else if (url.contains("/admin/user/delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				userService.delete(id);
			} catch (Exception e) {
				req.setAttribute("error", e.getMessage());
			}
			resp.sendRedirect(req.getContextPath() + "/admin/home");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		if (url.contains("/admin/user/update")) {
			int id = Integer.parseInt(req.getParameter("userid"));
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			String role = req.getParameter("role");
			int status = Integer.parseInt(req.getParameter("status"));

			User u = new User();
			u.setUserId(id);
			u.setUsername(username);
			u.setPassword(password);
			u.setEmail(email);
			u.setRole(role);
			u.setStatus(status);

			userService.update(u);
			resp.sendRedirect(req.getContextPath() + "/admin/home");

		} else if (url.contains("/admin/user/insert")) {
			User u = new User();
			u.setUsername(req.getParameter("username"));
			u.setPassword(req.getParameter("password"));
			u.setEmail(req.getParameter("email"));
			u.setRole(req.getParameter("role"));
			u.setStatus(Integer.parseInt(req.getParameter("status")));

			userService.insert(u);
			resp.sendRedirect(req.getContextPath() + "/admin/home");
		}
	}
}
