package vn.iotstar.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.entity.Category;
import vn.iotstar.entity.User;
import vn.iotstar.entity.Video;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.IVideoService;
import vn.iotstar.services.impl.CategoryService;
import vn.iotstar.services.impl.UserService;
import vn.iotstar.services.impl.VideoService;

@WebServlet("/admin/home")
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ICategoryService categoryService = new CategoryService();
    IUserService userService = new UserService();
    IVideoService videoService = new VideoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Category> listCate = categoryService.findAll();
        List<User> listUser = userService.findAll();
        List<Video> listVideo = videoService.findAll();

        req.setAttribute("listcate", listCate);
        req.setAttribute("listuser", listUser);
        req.setAttribute("listvideo", listVideo);

        req.getRequestDispatcher("/views/admin/dashboard.jsp").forward(req, resp);
    }
}
