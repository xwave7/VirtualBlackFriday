package com.vbf.instantbuy.dao;

import com.vbf.instantbuy.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by BRODY on 2019/12/3.
 */

@Mapper
public interface UserDAO {

    @Select("select * from user where id = #{id}")
    public User getById(@Param("id") int id);

    @Insert("insert into user(id, name) values(#{id}, #{name})")
    public int insert(User user);
}
