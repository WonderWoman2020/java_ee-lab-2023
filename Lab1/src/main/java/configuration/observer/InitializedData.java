package configuration.observer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.control.RequestContextController;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.SneakyThrows;
import skill.entity.DifficultyLevel;
import skill.entity.Skill;
import skill.service.SkillService;
import user.entity.User;
import user.service.UserService;

import java.time.LocalDate;
import java.util.UUID;

@ApplicationScoped
public class InitializedData {


    /**
     * User service.
     */
    private final UserService userService;

    private final SkillService skillService;


    /**
     * The CDI container provides a built-in instance of {@link RequestContextController} that is dependent scoped for
     * the purposes of activating and deactivating.
     */
    private final RequestContextController requestContextController;

    /**
     * @param userService              user service
     * @param skillService
     * @param requestContextController CDI request context controller
     */
    @Inject
    public InitializedData(
            UserService userService,
            SkillService skillService, RequestContextController requestContextController
    ) {
        this.userService = userService;
        this.skillService = skillService;
        this.requestContextController = requestContextController;
    }

    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
    }

    /**
     * Initializes database with some example values. Should be called after creating this object. This object should be
     * created only once.
     */
    @SneakyThrows
    private void init() {
        requestContextController.activate();// start request scope in order to inject request scoped repositories

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

        Skill skill1 = Skill.builder()
                .id(UUID.fromString("87654321-AAAA-AAAA-AAAA-CBA987654321"))
                .name("Running")
                .level(DifficultyLevel.EASY)
                .description("Running so cool")
                .tags(null)
                .favouriteRank(20)
                .tutorial(null)
                .build();

        Skill skill2 = Skill.builder()
                .id(UUID.fromString("87654321-BBBB-BBBB-BBBB-CBA987654321"))
                .name("Roller skating")
                .level(DifficultyLevel.HARD)
                .description("Cooler than ice skating")
                .tags(null)
                .favouriteRank(30)
                .tutorial(null)
                .build();

        Skill skill3 = Skill.builder()
                .id(UUID.fromString("87654321-CCCC-CCCC-CCCC-CBA987654321"))
                .name("Drawing")
                .level(DifficultyLevel.MEDIUM)
                .description("Use your imagination freely")
                .tags(null)
                .favouriteRank(50)
                .tutorial(null)
                .build();

        skillService.create(skill1);
        skillService.create(skill2);
        skillService.create(skill3);

        requestContextController.deactivate();
    }

}