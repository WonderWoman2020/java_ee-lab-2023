package configuration.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.SneakyThrows;
import user.controller.api.UserController;
import user.controller.simple.UserSimpleController;
import user.entity.User;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.UUID;

@WebListener
public class InitializedData implements ServletContextListener {

    /**
     * User service.
     */
    //private UserService userService;
    private UserSimpleController userController;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        //userService = (UserController) event.getServletContext().getAttribute("userService");
        userController = (UserSimpleController) event.getServletContext().getAttribute("userController");
        init();
    }

    private void init()
    {
        User user1 = User.builder()
                .id(UUID.fromString("12345678-BBBB-BBBB-BBBB-123456789ABC"))
                .nick("Second :(")
                .login("im-the-one")
                .password("pass")
                .birthDate(LocalDate.now())
                .roles(null)
                .reputation(10)
                .tutorial(null)
                .build();

        userController.create(user1);
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
