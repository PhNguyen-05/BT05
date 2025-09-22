package vn.iotstar.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import vn.iotstar.entity.Category;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.impl.CategoryService;
import vn.iotstar.utils.Constant;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 1024 * 1024 * 5,       // 5MB
    maxRequestSize = 1024 * 1024 * 25    // 25MB
)
@WebServlet(urlPatterns = {
        "/admin/categories",
        "/admin/category/add",
        "/admin/category/insert",
        "/admin/category/edit",
        "/admin/category/update",
        "/admin/category/delete"
})
public class CategoryController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ICategoryService cateService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String url = req.getRequestURI();

        if (url.contains("categories")) {
            // Danh sách
            List<Category> list = cateService.findAll();
            req.setAttribute("listcate", list);
            req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);

        } else if (url.contains("add")) {
            // Form thêm mới
            req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);

        } else if (url.contains("edit")) {
            // Form sửa
            int id = Integer.parseInt(req.getParameter("id"));
            Category category = cateService.findById(id);
            req.setAttribute("cate", category);
            req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);

        } else if (url.contains("delete")) {
            // Xóa
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                cateService.delete(id);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "Lỗi khi xóa: " + e.getMessage());
                List<Category> list = cateService.findAll();
                req.setAttribute("listcate", list);
                req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
                return;
            }
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String url = req.getRequestURI();

        try {
            if (url.contains("insert")) {
                // Xử lý thêm mới
                Category category = new Category();
                category.setCategoryName(req.getParameter("categoryname"));
                category.setStatus(Integer.parseInt(req.getParameter("status")));

                // Upload ảnh
                Part part = req.getPart("images");
                if (part != null && part.getSize() > 0) {
                    String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    String ext = filename.substring(filename.lastIndexOf(".") + 1);
                    String newFile = System.currentTimeMillis() + "." + ext;

                    String uploadPath = getServletContext().getRealPath("") + File.separator + Constant.UPLOAD_DIRECTORY;
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) uploadDir.mkdirs();

                    part.write(uploadPath + File.separator + newFile);
                    category.setImages(newFile);
                } else {
                    category.setImages("default.png"); // ảnh mặc định
                }

                cateService.insert(category);
                resp.sendRedirect(req.getContextPath() + "/admin/categories");

            } else if (url.contains("update")) {
                // Xử lý cập nhật
                int categoryid = Integer.parseInt(req.getParameter("categoryid"));
                Category category = cateService.findById(categoryid);

                category.setCategoryName(req.getParameter("categoryname"));
                category.setStatus(Integer.parseInt(req.getParameter("status")));

                String oldFile = category.getImages();
                Part part = req.getPart("images");
                if (part != null && part.getSize() > 0) {
                    String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    String ext = filename.substring(filename.lastIndexOf(".") + 1);
                    String newFile = System.currentTimeMillis() + "." + ext;

                    String uploadPath = getServletContext().getRealPath("") + File.separator + Constant.UPLOAD_DIRECTORY;
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) uploadDir.mkdirs();

                    part.write(uploadPath + File.separator + newFile);
                    category.setImages(newFile);
                } else {
                    category.setImages(oldFile); // giữ ảnh cũ
                }

                cateService.update(category);
                resp.sendRedirect(req.getContextPath() + "/admin/categories");
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Lỗi khi thêm/sửa: " + e.getMessage());
            req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
        }
    }
}
