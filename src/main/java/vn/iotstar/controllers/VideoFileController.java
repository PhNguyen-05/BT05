package vn.iotstar.controllers;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.utils.Constant;

@WebServlet("/video-file")
public class VideoFileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fname = req.getParameter("fname");
        if (fname == null || fname.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "File name missing");
            return;
        }

        String uploadPath = getServletContext().getRealPath("") + File.separator + Constant.UPLOAD_DIRECTORY;
        File file = new File(uploadPath, fname);
        if (!file.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Video not found");
            return;
        }

        resp.setContentType("video/mp4");
        resp.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
        resp.setContentLengthLong(file.length());

        try (FileInputStream in = new FileInputStream(file);
             OutputStream out = resp.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.flush();
        }
    }
}
