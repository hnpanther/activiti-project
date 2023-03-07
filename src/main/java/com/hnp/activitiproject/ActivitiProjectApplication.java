package com.hnp.activitiproject;

import com.hnp.activitiproject.entity.User;
import com.hnp.activitiproject.repository.UserRepository;
import com.hnp.activitiproject.service.UserService;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ActivitiProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiProjectApplication.class, args);
    }


    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProcessRuntime processRuntime;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername("admin");
        user.setPassword(this.passwordEncoder.encode("admin"));
        user.setFirstName("admin");
        user.setLastName("admin");
        user.setEmail("admin@admin.com");



        try {
            this.userService.loadUserByUsername("admin");
        } catch (UsernameNotFoundException usernameNotFoundException) {
            this.userRepository.save(user);
        }

    }
}
