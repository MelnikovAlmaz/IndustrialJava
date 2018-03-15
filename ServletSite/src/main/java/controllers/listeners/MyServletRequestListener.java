package controllers.listeners;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by admin on 20.04.2017.
 */
public class MyServletRequestListener implements ServletRequestListener {
    private static final Logger LOGGER = Logger.getLogger(AppStartListener.class);

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        PropertyConfigurator.configure(AppStartListener.class.getClassLoader().getResource("log4j.properties"));

        HttpServletRequest servletRequest = (HttpServletRequest)servletRequestEvent.getServletRequest();

        if ((servletRequest.getMethod().equals("POST")) && (servletRequest.getRequestURI().equals("/editStudent"))){
            //LOGGER.debug("MyServletRequestListener: " + servletRequest.getSession().getAttribute("idToEdit"));
            if(((String)servletRequest.getSession().getAttribute("idToEdit")).equals("-1")){
                String admin_email = servletRequestEvent.getServletContext().getInitParameter("admin_email");
            }
        }
    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }
}
