package configuration.listener;

import datastore.component.DataStore;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import user.controller.api.UserController;
import user.controller.simple.UserSimpleController;

@WebListener
public class CreateControllers implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        DataStore dataStore = (DataStore) event.getServletContext().getAttribute("datasource");

        UserSimpleController userController = new UserSimpleController();
        userController.setStore(dataStore);
        event.getServletContext().setAttribute("userController", userController);
    }
}
