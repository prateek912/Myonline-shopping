package com.spring.MyOnlineShopping.utility;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {
	
	// For logging
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);

	// Developer project location
	private static final String absolute_path = 
			"C:\\Personal\\Spring Workspace\\Myonline-shopping\\MyOnlineShopping\\src\\main"
						+ "\\webapp\\resources\\images\\";
	
	// Where TomCat deploy the application
	private static String real_path = "";

	// Method to Upload file 
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		// Get the real path
		real_path =request.getSession().getServletContext().
											getRealPath("/resources/images/");
		logger.info("Real path of uploaded file :"+real_path);
		
		// To make sure directories exist
		if(! new File(absolute_path).exists()) {
			// Create the directory
			new File(absolute_path).mkdirs();
		}
		if(! new File(real_path).exists()) {
			// Create the directory
			new File(real_path).mkdirs();
		}
		
		try {
			// Transferring files 2 times, one to server and one to project development location
			file.transferTo(new File(real_path+code+".jpg"));
			file.transferTo(new File(absolute_path+code+".jpg"));
			
		} catch (IOException e) {
			logger.error("Exception has occured",e);
		}
		
	}
	
	
}
