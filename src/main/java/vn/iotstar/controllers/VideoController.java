//package vn.iotstar.controllers;
//
//import java.io.IOException;
//import java.util.List;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.*;
//import vn.iotstar.entity.Video;
//import vn.iotstar.services.IVideoService;
//import vn.iotstar.services.impl.VideoService;
//
//@WebServlet(urlPatterns = {"/admin/videos", "/admin/video/edit", "/admin/video/update",
//        "/admin/video/insert", "/admin/video/add", "/admin/video/delete"})
//public class VideoController extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    IVideoService videoService = new VideoService();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String url = req.getRequestURI();
//        if (url.contains("videos")) {
//            List<Video> list = videoService.findAll();
//            req.setAttribute("listvideo", list);
//            req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
//        } else if (url.contains("/admin/video/edit")) {
//            String id = req.getParameter("id");
//            Video v = videoService.findById(id);
//            req.setAttribute("video", v);
//            req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
//        } else if (url.contains("/admin/video/add")) {
//            req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
//        } else if (url.contains("/admin/video/delete")) {
//            String id = req.getParameter("id");
//            try { videoService.delete(id); } catch (Exception e) { req.setAttribute("error", e.getMessage()); }
//            resp.sendRedirect(req.getContextPath() + "/admin/videos");
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String url = req.getRequestURI();
//        if (url.contains("/admin/video/update")) {
//            String id = req.getParameter("videoid");
//            Video v = videoService.findById(id);
//            if (v == null) v = new Video();
//            v.setVideoId(id);
//            v.setTitle(req.getParameter("title"));
//            v.setDescription(req.getParameter("description"));
//            v.setPoster(req.getParameter("poster"));
//            v.setViews(Integer.parseInt(req.getParameter("views")));
//            v.setActive(Integer.parseInt(req.getParameter("active")));
//            videoService.update(v);
//            resp.sendRedirect(req.getContextPath() + "/admin/videos");
//        } else if (url.contains("/admin/video/insert")) {
//            Video v = new Video();
//            v.setVideoId(req.getParameter("videoid"));
//            v.setTitle(req.getParameter("title"));
//            v.setDescription(req.getParameter("description"));
//            v.setPoster(req.getParameter("poster"));
//            v.setViews(Integer.parseInt(req.getParameter("views")));
//            v.setActive(Integer.parseInt(req.getParameter("active")));
//            videoService.insert(v);
//            resp.sendRedirect(req.getContextPath() + "/admin/videos");
//        }
//    }
//}


package vn.iotstar.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.Video;
import vn.iotstar.services.IVideoService;
import vn.iotstar.services.impl.VideoService;
import vn.iotstar.utils.Constant;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 1024 * 1024 * 100, // 100MB
    maxRequestSize = 1024 * 1024 * 200
)
@WebServlet(urlPatterns = {"/admin/videos", "/admin/video/edit", "/admin/video/update",
        "/admin/video/insert", "/admin/video/add", "/admin/video/delete"})
public class VideoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IVideoService videoService = new VideoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        if (url.contains("videos")) {
            String keyword = req.getParameter("keyword");
            List<Video> list;
            if (keyword != null && !keyword.trim().isEmpty()) {
                list = videoService.findByTitle(keyword);
            } else {
                list = videoService.findAll();
            }
            req.setAttribute("listvideo", list);
            req.setAttribute("keyword", keyword);
            req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
        }

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
            resp.sendRedirect(req.getContextPath() + "/admin/home");
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

            // Giữ video cũ nếu không chọn file mới
            String oldFile = v.getVideoFile();
            Part filePart = req.getPart("videofile");
            if (filePart != null && filePart.getSize() > 0) {
                String filename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uploadPath = getServletContext().getRealPath("") + File.separator + Constant.UPLOAD_DIRECTORY;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                String ext = filename.substring(filename.lastIndexOf(".") + 1);
                String newFilename = System.currentTimeMillis() + "." + ext;
                filePart.write(uploadPath + File.separator + newFilename);

                v.setVideoFile(newFilename);
            } else {
                v.setVideoFile(oldFile);
            }

            videoService.update(v);
            resp.sendRedirect(req.getContextPath() + "/admin/home");

        } else if (url.contains("/admin/video/insert")) {
            Video v = new Video();
            v.setVideoId(req.getParameter("videoid"));
            v.setTitle(req.getParameter("title"));
            v.setDescription(req.getParameter("description"));
            v.setPoster(req.getParameter("poster"));
            v.setViews(Integer.parseInt(req.getParameter("views")));
            v.setActive(Integer.parseInt(req.getParameter("active")));

            // upload video file
            Part filePart = req.getPart("videofile");
            if (filePart != null && filePart.getSize() > 0) {
                String filename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uploadPath = getServletContext().getRealPath("") + File.separator + Constant.UPLOAD_DIRECTORY;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                String ext = filename.substring(filename.lastIndexOf(".") + 1);
                String newFilename = System.currentTimeMillis() + "." + ext;
                filePart.write(uploadPath + File.separator + newFilename);

                v.setVideoFile(newFilename);
            } else {
                v.setVideoFile(null);
            }

            videoService.insert(v);
            resp.sendRedirect(req.getContextPath() + "/admin/home");
        }
    }
}
