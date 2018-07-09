<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>저자 검색</title>
</head>
<body>
	<h2>Spring MVC BIRT Report Example</h2>
	<h3>책 검색 (BIRT report)</h3>
	
	<form method="POST" action="/scrapper/birt/viewReport">
		<table width="350px" border="1">
			<tr>
				<td>
					작가 이름 : <input type="text" name="authorName">
				</td>
				<td>
					<select name="reportType">
						<c:forEach items="${birtFormatMap}" var="item">
							<option value="${item.value}"> ${item.key} </option> 
						</c:forEach>
					</select>
				</td>
				<td colspan="2">
					<input type="submit" value="검색">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>