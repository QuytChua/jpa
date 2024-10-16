package vn.iotstar.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.configs.Constant;
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/video") 
public class LoadVideoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("run");
        String fileName = req.getParameter("fname");
        File file = new File(getServletContext().getRealPath("") + Constant.UPLOAD_DIRECTORY + "/" + fileName);
        System.out.println(file.getPath());
        
        if (file.exists()) {
            IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
        }
           
    }
}
