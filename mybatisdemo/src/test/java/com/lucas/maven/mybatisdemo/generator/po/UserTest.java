package com.lucas.maven.mybatisdemo.generator.po;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.github.pagehelper.PageHelper;
import com.lucas.maven.mybatisdemo.generator.mapper.UserMapper;



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
	public void test() {
		SqlSession sqlSession=null;
		try {
			sqlSession=sqlSessionFactory.openSession();
			UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
			
			
			
			//使用查询参数
			UserExample userExample=new UserExample();
			//增加限定条件
			//userExample.createCriteria().andIdEqualTo(40);
			
			//分页插件
			PageHelper.startPage(2, 2);
			
			List<User> selectByExample = userMapper.selectByExample(userExample);
			for (User user : selectByExample) {
				log.info(user);
			}
			
		} catch (Exception e) {
			log.error("Exception",e);
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}

}
