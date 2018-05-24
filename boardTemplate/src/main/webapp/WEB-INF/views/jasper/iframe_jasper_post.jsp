<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title> Jasper Report 페이지 </title>
</head>
<body>
<div id="header">
	<h1> ${jasperBookInputFormDto.reportType} 샘플 페이지 </h1>
</div>

<div id="pdf_section">
	<iframe src="http://localhost:8080/scrapper/jasper/generate?authorName=${jasperBookInputFormDto.authorName}&reportType=${jasperBookInputFormDto.reportType}" height="500px" width="1000px">
		<p>브라우저가 iframe을 지원하지 않습니다...</p>
	</iframe>
</div>
</body>
</html>