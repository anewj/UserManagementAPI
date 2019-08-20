package com.maharjanewj.springapi;

import com.maharjanewj.springapi.models.Privileges;
import com.maharjanewj.springapi.models.Role;
import com.maharjanewj.springapi.models.User;
import com.maharjanewj.springapi.repositories.PrivilegeRepository;
import com.maharjanewj.springapi.repositories.RoleRepository;
import com.maharjanewj.springapi.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringApiApplication {


    @Autowired
    private CustomUserDetailsService userService;

    public static void main(String[] args) {
        SpringApplication.run(SpringApiApplication.class, args);
    }

    @Bean
    CommandLineRunner init(RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        return args -> {

//        	Add system privileges and roles
//			Add a super admin role
            insertPrivilege(privilegeRepository, "WRITE_PRIVILEGE");
            insertPrivilege(privilegeRepository, "READ_PRIVILEGE");


            if (insertRole(roleRepository, "ROLE_SUPER_ADMIN") == null) {
                User user = new User();
                user.setFullname("superadmin");
                user.setPassword("superadmin");
                user.setEmail("superadmin");
                userService.saveSuperAdmin(user);
            }
            ;
            insertRole(roleRepository, "ROLE_ADMIN");
            insertRole(roleRepository, "ROLE_USER");
        };
    }

    private static Role insertRole(RoleRepository roleRepository, String roleCheck) {
        Role role = roleRepository.findByRole(roleCheck);

        if (role == null) {
            Role newRole = new Role();
            newRole.setRole(roleCheck);
            roleRepository.save(newRole);
            System.out.println("Role added: " + roleCheck);
        }

        return role;
    }

    private static void insertPrivilege(PrivilegeRepository privilegeRepository, String privilegeCheck) {
        Privileges privileges = privilegeRepository.findByPrivilege(privilegeCheck);

        if (privileges == null) {
            Privileges newPrivileges = new Privileges();
            newPrivileges.setPrivilege(privilegeCheck);
            newPrivileges.setDescription(privilegeCheck);
            privilegeRepository.save(newPrivileges);
            System.out.println("Privilege added: " + privilegeCheck);

        }
    }
}
