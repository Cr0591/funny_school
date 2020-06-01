package edu.gpnu.service.impl;

import edu.gpnu.entity.User;
import edu.gpnu.mapper.UserMapper;
import edu.gpnu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUsersByUserame(String username) {
        return userMapper.getUsersByUserame(username);
    }

    @Override
    public User getUsersByStudentId(String studentId) {
        return userMapper.getUsersByStudentId(studentId);
    }

    @Override
    public int saveUser(User user) {
        return userMapper.insert(user);
    }
}
