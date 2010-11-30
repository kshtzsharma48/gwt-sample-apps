package com.lines_of_code.gsa.gwtsandbox.server;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.ServletHandler;
import org.mortbay.jetty.servlet.ServletHolder;

public class UploadServletTest extends TestCase{
	private static Server jettyServer;
	private static URL servletURL;
	private static WebClient client;
	
	
	
	@Before
	public void setUp() throws Exception {
		servletURL = new URL("http://localhost:9876/gwtsandbox/upload");
		jettyServer = new Server(9876);
		client = new WebClient();
		ServletHandler context = new ServletHandler();
		jettyServer.setHandler(context);
		
		context.addServletWithMapping(new ServletHolder(new UploadServlet()), "/gwtsandbox/upload");
		
		jettyServer.start();
		jettyServer.setStopAtShutdown(true);
	}

	
	@Test
	public void testUploadServletGet() throws IOException {
		String get = client.getContent(servletURL);
		assertEquals("GET", "GET recieved", get);
	}
	@After
	public void tearDown() throws Exception {
		jettyServer.stop();
	}
}
