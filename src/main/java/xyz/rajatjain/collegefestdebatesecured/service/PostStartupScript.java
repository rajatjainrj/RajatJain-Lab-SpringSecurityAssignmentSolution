package xyz.rajatjain.collegefestdebatesecured.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.rajatjain.collegefestdebatesecured.model.Role;
import xyz.rajatjain.collegefestdebatesecured.model.User;
import xyz.rajatjain.collegefestdebatesecured.repository.RoleRepository;
import xyz.rajatjain.collegefestdebatesecured.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author rajatjain on - 27-03-2022
 * @project CollegeFestDebateSecured
 */
@Slf4j
@Service
public class PostStartupScript {

    private RoleRepository roleRepository;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void initiateRolesAndDefaultUsers(){
        Role adminRole = checkAndCreateRole("ADMIN");
        Role userRole = checkAndCreateRole("USER");

        initiateUsers(adminRole, userRole);
    }

    private Role checkAndCreateRole(String role) {
        Optional<Role> roleAdminCheck = roleRepository.findByName(role);
        if(!roleAdminCheck.isPresent()) {
            Role roleAdmin = Role.builder().name(role).build();
            roleRepository.save(roleAdmin);
            log.info("Role " + role + " Created ID - " + roleAdmin.getId());
            return roleAdmin;
        }else{
            log.info("Role " + role + " OK!");
            return roleAdminCheck.get();
        }
    }

    public void initiateUsers(Role adminRole, Role userRole){
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminRoles.add(userRole);

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);

        createAndCheckDefaultUser("rajatjain", "rajat123", "contact@rajatjain.xyz", adminRoles);
        createAndCheckDefaultUser("kiran", "kiran123", "kiran@rj.com", userRoles);
        createAndCheckDefaultUser("ajay", "ajay123", "ajay@rj.com", userRoles);
    }

    private void createAndCheckDefaultUser(String userName, String password, String emailId,
                                           Set<Role> roles) {
        Optional<User> userCheck = userRepository.findByUserName(userName);
        if(!userCheck.isPresent()) {
            User user = User.builder().id(1L).userName(userName).password(passwordEncoder.encode(password)).email(emailId).roles(roles).enabled(true).build();
            userRepository.save(user);
        }
    }
}
