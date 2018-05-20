package com.spring.scrapper.jasper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/jasper")
public class JasperReportController {
	
	@RequestMapping(value="/report", method=RequestMethod.GET)
	public String viewReport(){
		// 그냥 심플하게 DB에 있는 데이터들의 값들을 리스트로 보고서에 출력하는걸로. 
		return "jasper/report";
	}
}
