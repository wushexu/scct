package foross.scctbi.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.pentaho.reporting.engine.classic.core.ClassicEngineBoot;

public class StartupListener implements ServletContextListener {
	public StartupListener() {
	}

	public void contextInitialized(final ServletContextEvent servletContextEvent) {
		ClassicEngineBoot.getInstance().start();
	}

	public void contextDestroyed(final ServletContextEvent servletContextEvent) {
	}
}
