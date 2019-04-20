package com.lucas.maven.mybatisdemo.annotation;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

	Log log=LogFactory.getLog(UserTest.class);
	SqlSessionFactory sqlSessionFactory;
	@Before
	public void setUp() throws Exception {
		try{
			String res="mapper/SqlMapConfig.xml";
			InputStream inputStream =Resources.getResourceAsStream(res);
			sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
		}catch(Exception e){
			log.error("Exception",e);
		}
		
	}

	@Test
	public void testFindUserById() {
		SqlSession sqlSession=null;
		try{
			sqlSession=sqlSessionFactory.openSession();
			UserMapper mapper=sqlSession.getMapper(UserMapper.class);
			User user=mapper.findUserById(1);
			log.info(user);
		}catch(Exception e){
			log.error("Exception",e);
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}
	
	@Test
	public void testInsertUser(){
		SqlSession sqlSession=null;
		try{
			sqlSession=sqlSessionFactory.openSession();
			UserMapper mapper=sqlSession.getMapper(UserMapper.class);
			User user=new User();
			user.setUsername("lucas");
			user.setAddress("上海");
			int r=mapper.insertUser(user);
			sqlSession.commit();
			log.info(user);
			log.info("插入数据数量："+r);
		}catch(Exception e){
			log.error("Exception",e);
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}
	@Test
	public void testUpdatetUser(){
		SqlSession sqlSession=null;
		try{
			sqlSession=sqlSessionFactory.openSession();
			UserMapper mapper=sqlSession.getMapper(UserMapper.class);
			User user=new User();
			user.setUsername("lucas");
			user.setAddress("北京");
			user.setBirthday("2019-03-03");
			user.setSex("2");
			user.setId(40);
			int r=mapper.updateUser(user);
			sqlSession.commit();
			log.info(user);
			log.info("修改数据数量："+r);
		}catch(Exception e){
			log.error("Exception",e);
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}
	@Test
	public void testDeleteUserById(){
		SqlSession sqlSession=null;
		try{
			sqlSession=sqlSessionFactory.openSession();
			UserMapper mapper=sqlSession.getMapper(UserMapper.class);
			int r=mapper.deleteUserById(39);
			sqlSession.commit();
			log.info("删除数据数量："+r);
		}catch(Exception e){
			log.error("Exception",e);
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}

}
