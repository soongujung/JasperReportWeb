<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Reports</title>

<!-- Bootstrap -->
<link href="assets/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="assets/css/page.css" rel="stylesheet">
</head>
<body>
	<%
		System.out.println("0");
	%>
	<div class="container">
	<%
		System.out.println("1");
	%>
		<h3>
			Reports.... (<a href="javascript:void(0);" class="reload-link">Reload</a>)
		</h3>
		<div class="row report-rows"></div>
		<%
			System.out.println("2");
		%>
		<div class="report-view" style="display: none">
		<h3>AAAAAAA</h3>
		<%
			System.out.println("3");
		%>
		<h3>BBBBBBB</h3>
			<h2>
				<span></span> <a href="javascript:void(0)">back</a>
			</h2>
			<div class="clearfix report-form">
				<form class="form-inline">
					<div class="form-group">
						<label for="exampleInputName2">Name</label> 
						<input type="text" class="form-control" id="exampleInputName2" placeholder="Jane Doe">
					</div>
					<button type="submit" class="btn btn-default">Generate</button>
				</form>
			</div>
			<div class="clearfix content"></div>
		</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="assets/jquery/jquery-3.1.0.min.js""></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="assets/js/report.js"></script>
</body>
</html>