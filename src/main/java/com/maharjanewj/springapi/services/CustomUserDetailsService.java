package com.maharjanewj.springapi.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.maharjanewj.springapi.models.Privileges;
import com.maharjanewj.springapi.repositories.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.maharjanewj.springapi.models.Role;
import com.maharjanewj.springapi.models.User;
import com.maharjanewj.springapi.repositories.RoleRepository;
import com.maharjanewj.springapi.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Role userRole = roleRepository.findByRole("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        Privileges userPrivileges = privilegeRepository.findByPrivilege("READ_PRIVILEGE");
        user.setPrivileges(new HashSet<>(Arrays.asList(userPrivileges)));

        userRepository.save(user);
    }

    public void saveSuperAdmin(User user) {
        String pw= user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Role userRole = roleRepository.findByRole("ROLE_SUPER_ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        Privileges userPrivileges = privilegeRepository.findByPrivilege("WRITE_PRIVILEGE");
        user.setPrivileges(new HashSet<>(Arrays.asList(userPrivileges)));

        userRepository.save(user);
        System.out.println("SUPER ADMIN ADDED: user: "+user.getEmail()+" , password: "+pw);

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        if (user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles(), user.getPrivileges());
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles, Set<Privileges> userPrivileges) {
        Set<GrantedAuthority> roles = new HashSet<>();
        Set<GrantedAuthority> privileges = new HashSet<>();

        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });

        userPrivileges.forEach((role) -> {
            privileges.add(new SimpleGrantedAuthority(role.getPrivilege()));
        });


        List<GrantedAuthority> grantedRoles = new ArrayList<>(roles);
        List<GrantedAuthority> grantedPrivileges = new ArrayList<>(privileges);

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(grantedRoles);
        grantedAuthorities.addAll(grantedPrivileges);

        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}