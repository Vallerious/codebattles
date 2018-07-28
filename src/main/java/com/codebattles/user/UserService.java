package com.codebattles.user;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.codebattles.role.Role;
import com.codebattles.user.User;
import com.codebattles.role.RoleRepository;
import com.codebattles.user.UserRepository;

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
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    @Override
    public User findUserByUsername(String username) {
      return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers(Sort sort) {
      return (List<User>) this.userRepository.findAll(sort);
    }

    @Override
    public Page<User> listAllByPage(Pageable pageable) {
      return this.userRepository.findAll(pageable);
    }

}