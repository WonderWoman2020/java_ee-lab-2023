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

        User user2 = User.builder()
                .id(UUID.fromString("12345678-AAAA-AAAA-AAAA-123456789ABC"))
                .nick("First!")
                .login("haha-login")
                .password("Don'tHackMe!123")
                .birthDate(LocalDate.now())
                .roles(null)
                .reputation(10)
                .tutorial(null)
                .build();

        User user3 = User.builder()
                .id(UUID.fromString("12345678-CCCC-CCCC-CCCC-123456789ABC"))
                .nick("anonymous")
                .login("login-of-a-user")
                .password("SneakyPassword")
                .birthDate(LocalDate.now())
                .roles(null)
                .reputation(20)
                .tutorial(null)
                .build();

        User user4 = User.builder()
                .id(UUID.fromString("12345678-DDDD-DDDD-DDDD-123456789ABC"))
                .nick("average-music-enjoyer")
                .login("music-login")
                .password("MyFavouriteSong!")
                .birthDate(LocalDate.now())
                .roles(null)
                .reputation(30)
                .tutorial(null)
                .build();

        userController.create(user1);
        userController.create(user2);
        userController.create(user3);
        userController.create(user4);

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
