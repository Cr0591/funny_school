package edu.gpnu.mapper;

import edu.gpnu.entity.Follow;

public interface FollowMapper {

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
