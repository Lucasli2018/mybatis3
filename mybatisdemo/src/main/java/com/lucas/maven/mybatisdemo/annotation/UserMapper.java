package com.lucas.maven.mybatisdemo.annotation;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
	@Insert("insert into user(username,sex,address,birthday)values(#{username},#{sex},#{address},#{birthday})")
	int insertUser(User user);
	@Select("select * from user where id=#{id}")
	User findUserById(int id);
	@Delete("delete from user where id=#{id}")
	int deleteUserById(int id);
	@Update("update user set sex=#{sex},address=#{address},birthday=#{birthday},username=#{username} where id=#{id}")
	int updateUser(User user);
}
