package com.spring.scrapper.user;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.scrapper.domain.user.dto.UserDTO;
import com.spring.scrapper.domain.user.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Inject
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET )
	public void loginGet(@ModelAttribute("dto") UserDTO userDto){
		
	}
	
	@RequestMapping(value="/loginPost", method=RequestMethod.POST)
	public void loginPost(UserDTO userDto, HttpSession session, Model model) throws Exception{
//		boolean result = userService.login(userDto);
		UserVO resultVO = userService.login(userDto);
		
		if(resultVO == null){
			return;
		}
		
		model.addAttribute("userVO", resultVO);
		
	}
}
