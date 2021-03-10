package org.geektimes.projects.user.web.listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionInitializerListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
      try {
         Context contextEnvironment = new InitialContext();
         Context envCtx = (Context) contextEnvironment.lookup("java:comp/env");
         DataSource ds = (DataSource) envCtx.lookup("jdbc/UserPlatformDB");
         ServletContext context = sce.getServletContext();
         Connection connection = ds.getConnection();
         context.setAttribute("connection", connection);
      } catch (NamingException | SQLException e ) {
         throw new RuntimeException(e.getCause());
      }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        sce.getServletContext().removeAttribute( "connection" ) ;
    }
}
