package edu.gpnu.service.impl;

import edu.gpnu.entity.Follow;
import edu.gpnu.mapper.FollowMapper;
import edu.gpnu.service.IFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements IFollowService {

    @Autowired
    private FollowMapper followMapper;

    @Override
    public int add(Follow follow) {
        return followMapper.insert(follow);
    }

    @Override
    public int del(Follow follow) {
        return followMapper.delete(follow);
    }
}
