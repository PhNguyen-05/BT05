package vn.iotstar.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.utils.Constant;

@WebServlet("/image")
public class ImageController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String fileName = req.getParameter("fname");
        if (fileName == null || fileName.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "File name is missing!");
            return;
        }

        // Sử dụng getRealPath để khớp với upload path
        String uploadPath = getServletContext().getRealPath("") + File.separator + Constant.UPLOAD_DIRECTORY;
        File file = new File(uploadPath, fileName);
        if (!file.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found!");
            return;
        }

        resp.setContentType(getServletContext().getMimeType(file.getName()));
        resp.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
        resp.setContentLengthLong(file.length());

        try (FileInputStream in = new FileInputStream(file);
             OutputStream out = resp.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.flush(); // Thêm flush để đảm bảo output
        } catch (IOException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error serving file");
        }
    }
}