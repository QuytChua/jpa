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
import vn.iotstar.entity.Video;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.IVideoService;
import vn.iotstar.services.impl.CategoryService;
import vn.iotstar.services.impl.VideoService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/videos", "/video/add", "/video/insert", "/video/edit",
		"/video/update", "/video/delete", "/video/search" })
public class VideoController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public IVideoService vidService = new VideoService();
	public ICategoryService cateService = new CategoryService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();

		if (url.contains("videos")) {
			List<Video> list = vidService.findAll();
			req.setAttribute("listVideos", list);
			req.getRequestDispatcher("/View/video-list.jsp").forward(req, resp);
			
			for(var l : list) {
				System.out.print(l.getId());
			}
		}

		else if (url.contains("add")) {
			List<Category> listCate = cateService.findAll();
			req.setAttribute("listcate", listCate);
			req.getRequestDispatcher("/View/video-add.jsp").forward(req, resp);

		}

		else if (url.contains("edit")) {
			String id = req.getParameter("id");
			Video video = vidService.findById(id);
			List<Category> listCate = cateService.findAll();
			req.setAttribute("listcate", listCate);
			req.setAttribute("vid", video);
			req.getRequestDispatcher("/View/video-edit.jsp").forward(req, resp);

		} else if (url.contains("delete")) {
			String id = req.getParameter("id");
			try {
				vidService.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/videos");
		} else if (url.contains("search"))
		{
			String name = req.getParameter("name");
			if (name == null || name.trim().isEmpty()) {
		        resp.sendRedirect(req.getContextPath() + "/videos");
		        return; 
		    }
			List<Video> listVi = vidService.findByVideoname(name);
			req.setAttribute("listVideos", listVi);
			req.getRequestDispatcher("/View/video-list.jsp").forward(req, resp);

		}
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if (url.contains("videos")) {
			
			resp.sendRedirect(req.getContextPath() + "/videos");
			
			
			
		} else if (url.contains("insert")) {
		 	String videoIdStr = req.getParameter("videoId"); 
		    String title = req.getParameter("title");
		    String description = req.getParameter("description"); 
		    String categoryIdStr = req.getParameter("categoryId"); 
		    Category cate = cateService.findById(Integer.parseInt(categoryIdStr)); 
		    Video video = new Video();
		    video.setVideoId(videoIdStr);
		    video.setTitle(title);
		    video.setDescription(description);
		    video.setCategory(cate); 
		    
		    
			String fname = "";
			String uploadPath = getServletContext().getRealPath("") + Constant.UPLOAD_DIRECTORY;
			System.out.print(uploadPath);
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				System.out.println("2");
				Part part = req.getPart("poster");
				System.out.println("3");
				if (part.getSize() > 0) {
					System.out.println("1");
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					System.out.println(filename);
					part.write(uploadPath + File.separator + fname);
					video.setPoster(fname);
				} else {
					video.setPoster("video.mp4");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			vidService.insert(video);
			resp.sendRedirect(req.getContextPath() + "/videos");

			
		} else if (url.contains("update")) {
			
			String videoIdStr = req.getParameter("videoId"); // Video ID
		    String title = req.getParameter("title"); // Video Title
		    String description = req.getParameter("description"); // Video Description
		    String categoryIdStr = req.getParameter("categoryId"); // Category ID
		    Category cate = cateService.findById(Integer.parseInt(categoryIdStr)); // Convert category ID to int
		    Video video = new Video();
		    video.setVideoId(videoIdStr);
		    video.setTitle(title);
		    video.setDescription(description);
		    video.setCategory(cate); 
		    
			String fname = "";
			String uploadPath = getServletContext().getRealPath("") + Constant.UPLOAD_DIRECTORY;
			System.out.print(uploadPath);
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				System.out.println("2");
				Part part = req.getPart("poster");
				System.out.println("3");
				if (part.getSize() > 0) {
					System.out.println("1");
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					System.out.println(filename);
					part.write(uploadPath + File.separator + fname);
					video.setPoster(fname);
				} else {
					video.setPoster("video.mp4");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			vidService.update(video);
			resp.sendRedirect(req.getContextPath() + "/videos");
		}
		
	}
}
