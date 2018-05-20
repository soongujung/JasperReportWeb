package com.spring.scrapper.user;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.scrapper.common.CommonDAO;
import com.spring.scrapper.domain.user.dto.UserDTO;
import com.spring.scrapper.domain.user.vo.UserVO;
import com.spring.scrapper.user.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserDAOImplTest extends CommonDAO {

	@Inject
	private SqlSession sqlSession;
	public UserVO selectUserForLogin(UserDTO userDto) throws Exception {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("userId", userDto.getUserId());
		parameterMap.put("password", userDto.getPassword());
//		UserVO resultVO = super.getSqlSession().selectOne("com.spring.scrapper.user.selectUserForLogin", parameterMap);
		UserVO resultVO = sqlSession.selectOne("com.spring.scrapper.user.selectUserForLogin", parameterMap);
		return resultVO;
	}
	
	@Test
	public void testSelectUserForLogin() throws Exception{
		UserDTO userDto = new UserDTO();
		userDto.setUserId("Admin");
		userDto.setPassword("1111");
		
		selectUserForLogin(userDto);
	}
}
