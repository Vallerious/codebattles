package com.codebattles.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.codebattles.interfaces.IUserService;
import com.codebattles.models.CodebattlesUser;
import com.codebattles.models.Role;
import com.codebattles.repositories.RoleRepository;
import com.codebattles.repositories.UserRepository;

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

    @Override
    public void deleteUser(String id) {
      this.userRepository.deleteById(id);
    }
    
    private List<String> mapRolesToString(CodebattlesUser user) {
      Set<Role> roles = user.getRoles();
      List<Role> rolesList = new ArrayList<>(roles);

      return rolesList.stream().map(role -> role.getRole()).collect(Collectors.toList());
    }

    @Override
    public List<List<String>> getUserRoles(List<CodebattlesUser> users) {
      return users.stream()
          .map(u -> mapRolesToString(u))
          .collect(Collectors.toList());
    }

}