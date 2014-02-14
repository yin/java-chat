package com.github.yin.rwechat;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ChatNodeStarter {
	public static void main(String[] args) throws Exception {
		Server server = new Server();
		ServerConnector http = new ServerConnector(server);
		http.setHost("localhost");
		http.setPort(8080);
		server.addConnector(http);
		
		ServletContextHandler servletContextHandler = new ServletContextHandler();
		servletContextHandler.setContextPath("/");
		servletContextHandler.addServlet(new ServletHolder(new HttpServlet() {
			@Override
			protected void doGet(HttpServletRequest req,
					HttpServletResponse resp) throws ServletException,
					IOException {
				resp.getOutputStream().write("Hello world!".getBytes());
			}
		}), "/");
		
		server.join();
		server.start();
	}
	
}
