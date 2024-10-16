package vn.iotstar.controller;

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
import vn.iotstar.configs.Constant;
import vn.iotstar.entity.Category;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.impl.CategoryService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/categories", "/category/add", "/category/insert",
		"/category/edit", "/category/update", "/category/delete", "/category/search" })
public class CategoryController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ICategoryService cateService = new CategoryService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		
		if(url.contains("categories"))
		{
			List<Category> list = cateService.findAll();
			req.setAttribute("listcategory", list);
			req.getRequestDispatcher("/View/category-list.jsp").forward(req, resp);
		}
		
		else if (url.contains("add"))
		{
			req.getRequestDispatcher("/View/category-add.jsp").forward(req, resp);

		}
		
		else if (url.contains("edit"))
		{
			int id = Integer.parseInt(req.getParameter("id"));
			Category category = cateService.findById(id);
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/View/category-edit.jsp").forward(req, resp);

		}
		else if (url.contains("delete"))
		{
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				cateService.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath()+"/categories");
		}
		
		
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if(url.contains("categories"))
		{
			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
			String categoryname = req.getParameter(req.getParameter("categoryname"));
			int status = Integer.parseInt(req.getParameter("status"));
			String images = "https://simg.zalopay.com.vn/zlp-website/assets/phim_kinh_di_my_hay_nhat_The_Nun_6c798b2df8.jpg";
			Category category = new Category();
			category.setCategoryId(categoryid);
			category.setCategoryname(categoryname);
			category.setStatus(status);
			category.setImages(images);
			cateService.update(category);
			resp.sendRedirect(req.getContextPath()+"/categories");
		}
		else if(url.contains("insert"))
		{
			String categoryname = req.getParameter("categoryname");
			String status = req.getParameter("status");
			int statuss= Integer.parseInt(status);
			Category category = new Category();
			category.setCategoryname(categoryname);
			category.setStatus(statuss);
			String fname="";
			String uploadPath = getServletContext().getRealPath("") + Constant.UPLOAD_DIRECTORY;
			System.out.print(uploadPath);
		    File uploadDir = new File(uploadPath);
			if(!uploadDir.exists())
			{
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if(part.getSize()>0)
				{
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring (index+1);
					fname = System.currentTimeMillis()+"."+ext;
					System.out.println(filename);
	                part.write(uploadPath + File.separator + fname);
					category.setImages(fname);
				}
				else
				{
					category.setImages("avata.png");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			cateService.insert(category);
			resp.sendRedirect(req.getContextPath()+"/categories");
		}
		else if(url.contains("update"))
		{
			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
			String categoryname = req.getParameter("categoryname");
			String status = req.getParameter("status");
			int statuss= Integer.parseInt(status);
			Category category = new Category();
			
			String fname="";
			String uploadPath = getServletContext().getRealPath("") + Constant.UPLOAD_DIRECTORY;
			System.out.print(uploadPath);
		    File uploadDir = new File(uploadPath);
			if(!uploadDir.exists())
			{
				uploadDir.mkdir();
			}
			try {
				System.out.println("2");
				Part part = req.getPart("images");
				System.out.println("3");
				if(part.getSize()>0)
				{
					System.out.println("1");
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring (index+1);
					fname = System.currentTimeMillis()+"."+ext;
					System.out.println(filename);
	                part.write(uploadPath + File.separator + fname);
					category.setImages(fname);
				}
				else
				{
					category.setImages("avata.png");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			category.setCategoryId(categoryid);
			category.setCategoryname(categoryname);
			category.setStatus(statuss);
			cateService.update(category);
			resp.sendRedirect(req.getContextPath()+"/categories");
		}
	}
}
