package vn.iotstar.controllers;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.Video;
import vn.iotstar.services.IVideoService;
import vn.iotstar.services.impl.VideoService;

@WebServlet(urlPatterns = {"/admin/videos", "/admin/video/edit", "/admin/video/update",
        "/admin/video/insert", "/admin/video/add", "/admin/video/delete"})
public class VideoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IVideoService videoService = new VideoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        if (url.contains("videos")) {
            List<Video> list = videoService.findAll();
            req.setAttribute("listvideo", list);
            req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
        } else if (url.contains("/admin/video/edit")) {
            String id = req.getParameter("id");
            Video v = videoService.findById(id);
            req.setAttribute("video", v);
            req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
        } else if (url.contains("/admin/video/add")) {
            req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
        } else if (url.contains("/admin/video/delete")) {
            String id = req.getParameter("id");
            try { videoService.delete(id); } catch (Exception e) { req.setAttribute("error", e.getMessage()); }
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        if (url.contains("/admin/video/update")) {
            String id = req.getParameter("videoid");
            Video v = videoService.findById(id);
            if (v == null) v = new Video();
            v.setVideoId(id);
            v.setTitle(req.getParameter("title"));
            v.setDescription(req.getParameter("description"));
            v.setPoster(req.getParameter("poster"));
            v.setViews(Integer.parseInt(req.getParameter("views")));
            v.setActive(Integer.parseInt(req.getParameter("active")));
            videoService.update(v);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        } else if (url.contains("/admin/video/insert")) {
            Video v = new Video();
            v.setVideoId(req.getParameter("videoid"));
            v.setTitle(req.getParameter("title"));
            v.setDescription(req.getParameter("description"));
            v.setPoster(req.getParameter("poster"));
            v.setViews(Integer.parseInt(req.getParameter("views")));
            v.setActive(Integer.parseInt(req.getParameter("active")));
            videoService.insert(v);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
    }
}
