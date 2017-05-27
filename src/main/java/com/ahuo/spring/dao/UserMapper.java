package com.ahuo.spring.dao;

import com.ahuo.spring.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("uuid") String uuid);

    int insert(User record);

    User selectByPrimaryKey(@Param("id") Integer id, @Param("uuid") String uuid);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}