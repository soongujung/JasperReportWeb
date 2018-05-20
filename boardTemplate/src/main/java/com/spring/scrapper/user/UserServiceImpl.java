package com.spring.scrapper.user;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.scrapper.domain.user.dto.UserDTO;
import com.spring.scrapper.domain.user.vo.UserVO;

@Service
public class UserServiceImpl implements UserService{
	
	@Inject
	private UserDAO userDAO;

	@Override
	public UserVO login(UserDTO userDto) throws Exception {
//		return userDAO.selectUserForLogin(userDto) != null ? true : false;
		return userDAO.selectUserForLogin(userDto);
	}

}
