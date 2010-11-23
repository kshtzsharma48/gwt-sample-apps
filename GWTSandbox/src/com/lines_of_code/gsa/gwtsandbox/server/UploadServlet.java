package com.lines_of_code.gsa.gwtsandbox.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String loggerName = UploadServlet.class.getName();
	private static Logger log = Logger.getLogger(loggerName);
	
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		log.info("GET recieved");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ServletFileUpload upload = new ServletFileUpload();
		log.info("POST recieved");
		try {
			FileItemIterator iter = upload.getItemIterator(request);
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				log.info("FieldName: "+item.getFieldName());
				log.info("ContentType: " + item.getContentType());
				log.info("ItemName: "+item.getName());			
			}
		} catch (FileUploadException e) {
			log.severe(e.getMessage());
		}

	}
}
