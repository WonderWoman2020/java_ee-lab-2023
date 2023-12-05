package configuration.singleton;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import skill.entity.DifficultyLevel;
import skill.entity.Skill;
import skill.service.SkillService;
import tutorial.entity.Tutorial;
import tutorial.service.TutorialService;
import user.entity.User;
import user.service.UserService;

import java.time.LocalDate;
import java.util.UUID;

@Singleton
@Startup
@TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
@NoArgsConstructor
public class InitializedData {


    /**
     * User service.
     */
    private UserService userService;

    private SkillService skillService;

    private TutorialService tutorialService;

    @EJB
    public void setUserService(UserService userService)
    {
        this.userService = userService;
    }

    //@EJB
    @Inject
    public void setTutorialService(TutorialService tutorialService)
    {
        this.tutorialService = tutorialService;
    }

    @EJB
    public void setSkillService(SkillService skillService)
    {
        this.skillService = skillService;
    }

    /**
     * Initializes database with some example values. Should be called after creating this object. This object should be
     * created only once.
     */
    @PostConstruct
    @SneakyThrows
    private void init() {
        System.out.println("Singleton data initializer");
        if(skillService.findAll().isEmpty())
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

            //System.out.println("Init");
            skillService.create(skill1);
            skillService.create(skill2);
            skillService.create(skill3);

            Tutorial tutorial1 = Tutorial.builder()
                    .id(UUID.fromString( "12345678-AAAA-AAAA-AAAA-123456789ABC"))
                    .title("How to master skate jumps")
                    .author(user1)
                    .skill(skill2)
                    .description("You have to do...")
                    .duration(12)
                    .build();

            Tutorial tutorial2 = Tutorial.builder()
                    .id(UUID.fromString( "12345678-BBBB-BBBB-BBBB-123456789ABC"))
                    .title("How to catch balance")
                    .author(user2)
                    .skill(skill2)
                    .description("Don't start from...")
                    .duration(6)
                    .build();

            Tutorial tutorial3 = Tutorial.builder()
                    .id(UUID.fromString( "12345678-CCCC-CCCC-CCCC-123456789ABC"))
                    .title("What you shouldn't do while running")
                    .author(user3)
                    .skill(skill1)
                    .description("Let's start with basics...")
                    .duration(3)
                    .build();

            tutorialService.create(tutorial1);
            tutorialService.create(tutorial2);
            tutorialService.create(tutorial3);
        }
    }

}