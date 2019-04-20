package com.lucas.maven.mybatisdemo.pojo;

import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

public class MybatisTest {

	static final Log log = LogFactory.getLog(MybatisTest.class);
	SqlSessionFactory sqlSessionFactory;

	@Before
	public void setUp() throws Exception {
		// 全局配置文件路径
		String res = "mapper/SqlMapConfig.xml";
		try {
			// 加载所有配置文件，mapper.xml
			InputStream inputStream = Resources.getResourceAsStream(res);
			// 创建工厂
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			log.error("Exception", e);
		}
	}

	@Test
	public void test() {
		// 通过工厂创建会话Session
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			// 调用SqlSession接口的api操作数据库
			User user = sqlSession.selectOne("test.findUserById", 1);
			log.info(user);
		} catch (Exception e) {
			log.error("Exception",e);
		} finally {
			// 释放资源
			if(sqlSession!=null){
				sqlSession.close();
			}
		}

	}

}
