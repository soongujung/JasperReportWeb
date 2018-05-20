package com.spring.scrapper.user;

import com.spring.scrapper.domain.user.dto.UserDTO;
import com.spring.scrapper.domain.user.vo.UserVO;

public interface UserService {
	public UserVO login(UserDTO userDto) throws Exception;
}
