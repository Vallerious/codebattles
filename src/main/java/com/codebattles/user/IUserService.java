package com.codebattles.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.codebattles.user.CodebattlesUser;

public interface IUserService {
    public List<CodebattlesUser> getAllUsers(Sort sort);
    public Page<CodebattlesUser> listAllByPage(Pageable pageable);
    public CodebattlesUser findUserByEmail(String email);
    public CodebattlesUser findUserByUsername(String username);
    public void saveUser(CodebattlesUser user);
    public void updateUserScore(String email, Long points);
    public List<CodebattlesUser> getAllUsers();
}
