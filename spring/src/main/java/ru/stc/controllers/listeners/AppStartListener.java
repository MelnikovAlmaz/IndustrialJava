package ru.stc.controllers.listeners;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by admin on 20.04.2017.
 */
public class AppStartListener implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(AppStartListener.class);
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        PropertyConfigurator.configure(AppStartListener.class.getClassLoader().getResource("log4j.properties"));

        String admin_email = servletContextEvent.getServletContext().getInitParameter("admin_email");

        LOGGER.debug(admin_email);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
