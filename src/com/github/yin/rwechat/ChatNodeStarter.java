package com.github.yin.rwechat;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

public class ChatNodeStarter {
	private static final class ChatServlet extends HttpServlet {
		@Override
		protected void doGet(HttpServletRequest req,
				HttpServletResponse resp) throws ServletException,
				IOException {
			resp.getOutputStream().write("Hello world!".getBytes());
		}
	}

	public static void main(String[] args) throws Exception {
		Server server = new Server();
		ServerConnector http = new ServerConnector(server);
//		http.setHost("localhost");
		http.setPort(8080);
		server.addConnector(http);
		
		ServletContextHandler servletContextHandler = new ServletContextHandler();
		servletContextHandler.setContextPath("/");
		servletContextHandler.addServlet(new ServletHolder(new ChatServlet()), "/");
		server.setHandler(servletContextHandler);

// Use for jsp
//		WebAppContext webAppContext = new WebAppContext();
//		webAppContext.setContextPath("/jsp");
//		webAppContext.setResourceBase("./web/jsp");
				
		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[]{servletContextHandler, webAppContext});
		server.setHandler(handlers);

		server.start();
		server.join();
	}
	
}
