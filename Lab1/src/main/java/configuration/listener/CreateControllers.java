package configuration.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import user.controller.api.UserController;
import user.controller.simple.UserSimpleController;
import user.service.UserService;

@WebListener
public class CreateControllers implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        UserService userService = (UserService) event.getServletContext().getAttribute("userService");
        UserController userController = new UserSimpleController(userService);
        event.getServletContext().setAttribute("userController", userController);
    }
}
