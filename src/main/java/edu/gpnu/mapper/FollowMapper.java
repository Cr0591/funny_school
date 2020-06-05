package edu.gpnu.mapper;

import edu.gpnu.entity.Follow;
import edu.gpnu.entity.User;

import java.util.List;

public interface FollowMapper {

    /**
     * 获取关注   即当前studentId正在关注的人
     * @param studentId
     * @return
     */
    List<User> queryFollowing(String studentId);


    /**
     * 获取粉丝    即当前studentId的粉丝
     * @param studentId
     * @return
     */
    List<User> queryFollower(String studentId);

    /**
     * 新增
     * @param follow
     * @return
     */
    int insert(Follow follow);


    /**
     * 删除
     * @param follow
     * @return
     */
    int delete(Follow follow);

}
