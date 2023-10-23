package configuration.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import user.entity.User;
import user.service.UserService;

import java.time.LocalDate;
import java.util.UUID;

/**
 * When other listeners are finished and data source, services, and controllers are created, this listener
 * initializes some example data, so the application can respond to requests even when it just started
 */
@WebListener
public class InitializedData implements ServletContextListener {

    /**
     * User service.
     */
    private UserService userService;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        userService = (UserService) event.getServletContext().getAttribute("userService");
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

        userService.create(user1);
        userService.create(user2);
        userService.create(user3);
        userService.create(user4);

    }
}
