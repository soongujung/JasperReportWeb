package com.spring.scrapper.user;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


import com.spring.scrapper.domain.user.dto.UserDTO;
import com.spring.scrapper.domain.user.vo.UserVO;

@Repository
public class UserDAOImpl implements UserDAO{
	
	@Inject
	private SqlSession sqlSession;

	@Override
	public UserVO selectUserForLogin(UserDTO userDto) throws Exception {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("userId", userDto.getUserId());
		parameterMap.put("password", userDto.getPassword());
		return sqlSession.selectOne("com.spring.scrapper.user.selectUserForLogin", parameterMap);
	}
	
}
