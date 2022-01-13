package com.example.examproject.Component;

import com.example.examproject.entity.Role;
import com.example.examproject.entity.User;
import com.example.examproject.entity.enums.Permission;
import com.example.examproject.repository.RoleRepository;
import com.example.examproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {
        @Value("${spring.sql.init.mode}")
        private String initialMode;

        @Autowired
        UserRepository userRepository;
        @Autowired
        PasswordEncoder passwordEncoder;
        @Autowired
        RoleRepository roleRepository;
    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")){
            Permission[] values = Permission.values();
            Set<Permission> permissionSet=new HashSet<>(Arrays.asList(values));

            Role admin =roleRepository.save(new Role(1,"Admin",true,permissionSet));
            Role user=roleRepository.save(new Role(1,"User",true,new HashSet<>(
                    Arrays.asList(
                            Permission.Read_User,
                            Permission.Read_Question,
                            Permission.Read_Test
                    ))));
            Role teacher = roleRepository.save(new Role(1,"Teacher",true,new HashSet<>(
                    Arrays.asList(
                    Permission.Add_Question,
                    Permission.Add_User,
                    Permission.Add_Test
            ))));
            userRepository.save(new User("Shahzod Qudratov",
                    "sheyx777",
                    passwordEncoder.encode("123"),
                    "990910630",
                    admin,
                    true

                    ));


        }

    }
}
