package edu.gpnu.service;

import edu.gpnu.entity.User;

import java.util.List;

public interface IUserService {

    /**
     * 通过用户名 获取用户列表
     * @param username
     * @return
     */
    List<User> getUsersByUserame(String username);

    User getUsersByStudentId(String studentId);

    int saveUser(User user);
}
