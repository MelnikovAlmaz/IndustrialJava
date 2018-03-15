package controllers.listeners;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by admin on 20.04.2017.
 */
public class MySessionListener implements HttpSessionListener {
    private static final Logger LOGGER = Logger.getLogger(MySessionListener.class);
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        LOGGER.debug(httpSessionEvent.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
