package com.codebattles.user;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.codebattles.role.Role;
import com.codebattles.user.CodebattlesUser;
import com.codebattles.role.RoleRepository;

@Service("userService")
public class UserService implements IUserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    public UserService(
        UserRepository userRepository,
        RoleRepository roleRepository,
        BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
      this.userRepository = userRepository;
      this.roleRepository = roleRepository;
      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    @Override
    public CodebattlesUser findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    @Override
    public CodebattlesUser findUserByUsername(String username) {
      return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(CodebattlesUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public List<CodebattlesUser> getAllUsers(Sort sort) {
      return (List<CodebattlesUser>) this.userRepository.findAll(sort);
    }
    
    @Override
    public List<CodebattlesUser> getAllUsers() {
      return (List<CodebattlesUser>) this.userRepository.findAll();
    }

    @Override
    public Page<CodebattlesUser> listAllByPage(Pageable pageable) {
      return this.userRepository.findAll(pageable);
    }

    @Override
    public void updateUserScore(String email, Long points) {
      CodebattlesUser user = this.userRepository.findByEmail(email);
      
      user.setRating(user.getRating() + points);
      
      this.userRepository.save(user);
    }

    @Override
    public void changeUserRole(String id, int inc) {
      Optional<CodebattlesUser> optionalUser = this.userRepository.findById(id);
      Role adminRole = this.roleRepository.findByRole("ADMIN");
      Role userRole = this.roleRepository.findByRole("USER");
      
      CodebattlesUser user = optionalUser.get();
      
      if (user != null) {
        Set<Role> roles = user.getRoles();
        Set<Role> newRoles = new HashSet<>();

        if (roles.contains(adminRole) && inc < 0) {
          newRoles.add(userRole);
          user.setRoles(newRoles);
          this.userRepository.save(user);
        } else if (roles.contains(userRole) && inc > 0) {
          newRoles.add(adminRole);
          user.setRoles(newRoles);
          this.userRepository.save(user);
        }
      }
      
    }

}