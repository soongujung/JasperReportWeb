<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>���� �˻�</title>
</head>

	<!--
	private String reportFormat = "Html"
	private String authorName;  
	-->
<body>
	<h2>Spring MVC JASPER Report Example</h2>
	<h3>å �˻� (Jasper Report)</h3>
	
	<form method="POST" action="/scrapper/jasper/generate">
		<table width="350px" border="1">
			<tr>
				<td>
					�۰� �̸� : <input type="text" name="authorName">
				</td>
				<td>
					<select name="reportType">
						<c:forEach items="${jasperFormatMap}" var="item">
							<option value="${item.value}"> ${item.key} </option> 
						</c:forEach>
					</select>
				</td>
				<td colspan="2">
					<input type="submit" value="�˻�">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>