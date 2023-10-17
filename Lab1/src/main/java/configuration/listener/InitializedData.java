package configuration.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.SneakyThrows;
import user.controller.api.UserController;

import java.io.InputStream;

@WebListener
public class InitializedData implements ServletContextListener {

    /**
     * User service.
     */
    //private UserService userService;
    private UserController userController;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        //userService = (UserController) event.getServletContext().getAttribute("userService");
        userController = (UserController) event.getServletContext().getAttribute("userController");
        init();
    }

    private void init()
    {

    }

    /**
     * @param name name of the desired resource
     * @return array of bytes read from the resource
     */
    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            if (is != null) {
                return is.readAllBytes();
            } else {
                throw new IllegalStateException("Unable to get resource %s".formatted(name));
            }
        }
    }
}
