package configuration.listener;

import datastore.component.DataStore;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import user.repository.api.UserRepository;
import user.repository.memory.UserInMemoryRepository;
import user.service.UserService;

public class CreateServices implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        DataStore dataStore = (DataStore) event.getServletContext().getAttribute("datasource");

        UserRepository userRepository = new UserInMemoryRepository(dataStore);
        UserService userService = new UserService(userRepository);
        event.getServletContext().setAttribute("userService", userService);
    }
}
