package com.spring.scrapper.user;

import com.spring.scrapper.domain.user.dto.UserDTO;
import com.spring.scrapper.domain.user.vo.UserVO;

public interface UserDAO {
	public UserVO selectUserForLogin(UserDTO userDto) throws Exception;
}
