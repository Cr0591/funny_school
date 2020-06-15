package edu.gpnu.service;

import edu.gpnu.entity.Follow;
import edu.gpnu.entity.User;

import java.util.List;

public interface IFollowService {
    /**
     * 获取关注   即当前studentId正在关注的人
     * @return
     */
    List<User> getFollowing(String studentId);

    /**
     * 获取粉丝    即当前studentId的粉丝
     * @return
     */
    List<User> getFollower(String studentId);

    int add(Follow follow);

    int del(Follow follow);

    boolean checkIsFollow(String follower,String studentId);

}
