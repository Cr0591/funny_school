package edu.gpnu.service.impl;

import edu.gpnu.entity.Follow;
import edu.gpnu.entity.User;
import edu.gpnu.mapper.FollowMapper;
import edu.gpnu.service.IFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements IFollowService {

    @Autowired
    private FollowMapper followMapper;

    @Override
    public List<User> getFollowing(String studentId) {
        return followMapper.queryFollowing(studentId);
    }

    @Override
    public List<User> getFollower(String studentId) {
        return followMapper.queryFollower(studentId);
    }

    @Override
    public int add(Follow follow) {
        return followMapper.insert(follow);
    }

    @Override
    public int del(Follow follow) {
        return followMapper.delete(follow);
    }
}
