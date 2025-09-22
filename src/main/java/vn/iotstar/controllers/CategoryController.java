//package vn.iotstar.controllers;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Paths;
//import java.util.List;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.MultipartConfig;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.Part;
//
//import vn.iotstar.entity.Category;
//import vn.iotstar.services.ICategoryService;
//import vn.iotstar.services.impl.CategoryService;
//import vn.iotstar.utils.Constant;
//
//@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
//@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/edit", "/admin/category/update",
//		"/admin/category/insert", "/admin/category/add", "/admin/category/delete" })
//public class CategoryController extends HttpServlet {
//
//	private static final long serialVersionUID = 1L;
//
//	ICategoryService cateService = new CategoryService();
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		resp.setCharacterEncoding("UTF-8");
//
//		String url = req.getRequestURI();
//
//		if (url.contains("categories")) {
//			List<Category> list = cateService.findAll();
//			req.setAttribute("listcate", list);
//			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
//
//		} else if (url.contains("/admin/category/edit")) {
//			int id = Integer.parseInt(req.getParameter("id"));
//			Category category = cateService.findById(id);
//			req.setAttribute("cate", category);
//			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
//
//		} else if (url.contains("/admin/category/add")) {
//			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
//
//		} else if (url.contains("/admin/category/delete")) {
//			int id = Integer.parseInt(req.getParameter("id"));
//			try {
//				cateService.delete(id);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			resp.sendRedirect(req.getContextPath() + "/admin/category-list.jsp");
//		}
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		resp.setCharacterEncoding("UTF-8");
//
//		String url = req.getRequestURI();
//
//		if (url.contains("/admin/category/update")) {
//			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
//			String categoryname = req.getParameter("categoryname");
//			int status = Integer.parseInt(req.getParameter("status"));
//
//			Category category = new Category();
//			category.setCategoryId(categoryid);
//			category.setCategoryName(categoryname);
//			category.setStatus(status);
//
//			// lưu hình cũ
//			Category cateold = cateService.findById(categoryid);
//			String fileold = cateold.getImages();
//
//			// xử lý images
//			String fname = "";
//			String uploadPath = Constant.UPLOAD_DIRECTORY;
//			File uploadDir = new File(uploadPath);
//			if (!uploadDir.exists()) {
//				uploadDir.mkdir();
//			}
//
//			try {
//				Part part = req.getPart("images");
//				if (part.getSize() > 0) {
//					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
//					int index = filename.lastIndexOf(".");
//					String ext = filename.substring(index + 1);
//					fname = System.currentTimeMillis() + "." + ext;
//
//					part.write(uploadPath + "/" + fname);
//					category.setImages(fname);
//				} else {
//					category.setImages(fileold);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			cateService.update(category);
//			resp.sendRedirect(req.getContextPath() + "/admin/categories");
//
//		} else if (url.contains("/admin/category/insert")) {
//			Category category = new Category();
//			String categoryname = req.getParameter("categoryname");
//			int status = Integer.parseInt(req.getParameter("status"));
//			category.setCategoryName(categoryname);
//			category.setStatus(status);
//
//			String fname = "";
//			String uploadPath = Constant.UPLOAD_DIRECTORY;
//			File uploadDir = new File(uploadPath);
//			if (!uploadDir.exists()) {
//				uploadDir.mkdir();
//			}
//
//			try {
//				Part part = req.getPart("images");
//				if (part.getSize() > 0) {
//					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
//					int index = filename.lastIndexOf(".");
//					String ext = filename.substring(index + 1);
//					fname = System.currentTimeMillis() + "." + ext;
//
//					part.write(uploadPath + "/" + fname);
//					category.setImages(fname);
//				} else {
//					category.setImages("avata.png");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			cateService.insert(category);
//			resp.sendRedirect(req.getContextPath() + "/admin/categories");
//		}
//	}
//}

//package vn.iotstar.controllers;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Paths;
//import java.util.List;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.MultipartConfig;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.Part;
//
//import vn.iotstar.entity.Category;
//import vn.iotstar.services.ICategoryService;
//import vn.iotstar.services.impl.CategoryService;
//import vn.iotstar.utils.Constant;
//
//@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
//@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/edit", "/admin/category/update",
//		"/admin/category/insert", "/admin/category/add", "/admin/category/delete" })
//public class CategoryController extends HttpServlet {
//
//	private static final long serialVersionUID = 1L;
//
//	ICategoryService cateService = new CategoryService();
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		resp.setCharacterEncoding("UTF-8");
//
//		String url = req.getRequestURI();
//
//		if (url.contains("categories")) {
//			List<Category> list = cateService.findAll();
//			req.setAttribute("listcate", list);
//			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
//
//		} else if (url.contains("/admin/category/edit")) {
//			int id = Integer.parseInt(req.getParameter("id"));
//			Category category = cateService.findById(id);
//			req.setAttribute("cate", category);
//			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
//
//		} else if (url.contains("/admin/category/add")) {
//			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
//
//		} else if (url.contains("/admin/category/delete")) {
//			int id = Integer.parseInt(req.getParameter("id"));
//			try {
//				cateService.delete(id);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			resp.sendRedirect(req.getContextPath() + "/admin/categories");
//		}
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		resp.setCharacterEncoding("UTF-8");
//
//		String url = req.getRequestURI();
//
//		try {
//			if (url.contains("/admin/category/update")) {
//				int categoryid = Integer.parseInt(req.getParameter("categoryid"));
//				String categoryname = req.getParameter("categoryname");
//				int status = Integer.parseInt(req.getParameter("status"));
//
//				Category category = new Category();
//				category.setCategoryId(categoryid);
//				category.setCategoryName(categoryname);
//				category.setStatus(status);
//
//				// lấy hình cũ
//				Category cateold = cateService.findById(categoryid);
//				String fileold = cateold.getImages();
//
//				// xử lý images
//				String fname = "";
//				String uploadPath = getServletContext().getRealPath("") + File.separator + Constant.UPLOAD_DIRECTORY;
//				File uploadDir = new File(uploadPath);
//				if (!uploadDir.exists()) {
//					uploadDir.mkdirs();
//				}
//
//				Part part = req.getPart("images");
//				if (part != null && part.getSize() > 0) {
//					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
//					int index = filename.lastIndexOf(".");
//					String ext = filename.substring(index + 1);
//					fname = System.currentTimeMillis() + "." + ext;
//
//					part.write(uploadPath + File.separator + fname);
//					category.setImages(fname);
//				} else {
//					category.setImages(fileold);
//				}
//
//				cateService.update(category);
//				resp.sendRedirect(req.getContextPath() + "/admin/categories");
//
//			} else if (url.contains("/admin/category/insert")) {
//				Category category = new Category();
//				String categoryname = req.getParameter("categoryname");
//				int status = Integer.parseInt(req.getParameter("status"));
//				category.setCategoryName(categoryname);
//				category.setStatus(status);
//
//				String fname = "";
//				String uploadPath = getServletContext().getRealPath("") + File.separator + Constant.UPLOAD_DIRECTORY;
//				File uploadDir = new File(uploadPath);
//				if (!uploadDir.exists()) {
//					uploadDir.mkdirs();
//				}
//
//				Part part = req.getPart("images");
//				if (part != null && part.getSize() > 0) {
//					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
//					int index = filename.lastIndexOf(".");
//					String ext = filename.substring(index + 1);
//					fname = System.currentTimeMillis() + "." + ext;
//
//					part.write(uploadPath + File.separator + fname);
//					category.setImages(fname);
//				} else {
//					category.setImages("avata.png"); // ảnh mặc định
//				}
//
//				cateService.insert(category);
//				resp.sendRedirect(req.getContextPath() + "/admin/categories");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			req.setAttribute("error", "Lỗi khi thêm/sửa: " + e.getMessage());
//			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
//		}
//	}
//}


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

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/edit", "/admin/category/update",
		"/admin/category/insert", "/admin/category/add", "/admin/category/delete" })
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	ICategoryService cateService = new CategoryService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String url = req.getRequestURI();

		if (url.contains("categories")) {
			List<Category> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);

		} else if (url.contains("/admin/category/edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Category category = cateService.findById(id);
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);

		} else if (url.contains("/admin/category/add")) {
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);

		} else if (url.contains("/admin/category/delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				cateService.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("error", "Lỗi khi xóa: " + e.getMessage()); // Thêm error attribute
				req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
				return;
			}
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String url = req.getRequestURI();

		try {
			if (url.contains("/admin/category/update")) {
				int categoryid = Integer.parseInt(req.getParameter("categoryid"));
				String categoryname = req.getParameter("categoryname");
				int status = Integer.parseInt(req.getParameter("status"));

				Category category = new Category();
				category.setCategoryId(categoryid);
				category.setCategoryName(categoryname);
				category.setStatus(status);

				// lấy hình cũ
				Category cateold = cateService.findById(categoryid);
				String fileold = cateold.getImages();

				// xử lý images
				String fname = "";
				String uploadPath = getServletContext().getRealPath("") + File.separator + Constant.UPLOAD_DIRECTORY;
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdirs(); // Tạo thư mục nếu chưa có
				}
				System.out.println("Upload path: " + uploadPath); // Debug log

				Part part = req.getPart("images");
				if (part != null && part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;

					part.write(uploadPath + File.separator + fname);
					category.setImages(fname);
				} else {
					category.setImages(fileold);
				}

				cateService.update(category);
				resp.sendRedirect(req.getContextPath() + "/admin/categories");

			} else if (url.contains("/admin/category/insert")) {
				Category category = new Category();
				String categoryname = req.getParameter("categoryname");
				int status = Integer.parseInt(req.getParameter("status"));
				category.setCategoryName(categoryname);
				category.setStatus(status);

				String fname = "";
				String uploadPath = getServletContext().getRealPath("") + File.separator + Constant.UPLOAD_DIRECTORY;
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdirs(); 
				}
				System.out.println("Upload path: " + uploadPath); 

				Part part = req.getPart("images");
				if (part != null && part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;

					part.write(uploadPath + File.separator + fname);
					category.setImages(fname);
				} else {
					category.setImages("avata.png"); 
				}

				cateService.insert(category);
				resp.sendRedirect(req.getContextPath() + "/admin/categories");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Lỗi khi thêm/sửa: " + e.getMessage());
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		}
	}
}