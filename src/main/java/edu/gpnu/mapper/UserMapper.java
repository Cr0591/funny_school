package edu.gpnu.mapper;

import edu.gpnu.entity.User;

import java.util.List;

public interface UserMapper {
    /**
     * 通过用户名 获取用户列表
     * @param username
     * @return
     */
    List<User> getUsersByUserame(String username);

    User getUsersByStudentId(String studentId);

    int insert(User user);

    int update(User user);
}
