<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LOGIN PAGE</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">
	function sendData() {
		for (var i = 0; i < document.forms[0].elements.length; i++) {
			if (document.forms[0].elements[i].value == "") {
				alert(document.forms[0].elements[i].name + " 를 입력하지 않았습니다. ");
				document.forms[0].elements[i].focus();
				return;
			}
		}
		document.forms[0].submit();
	}
</script>
</head>
<body>
	<div class="header container" style="width: 600px; padding: 15px;">

	</div>
	<div class="container" style="width: 600px; padding: 15px;">
		<h1></h1>
		<!-- <div class="input-group"> -->
		<table class="table table-bordered">
			<thead>
				<h1>LOGIN</h1>
			</thead>
			<tbody>
				<form action="/scrapper/user/loginPost" method="post">
					<tr>
						<th>id</th>
						<td>
							<input type="text" name="userId" value="${userVO.id}" class="form-control" placeholder="username" aria-describedby="basic-addon1"/>
						</td>
					</tr>
					<tr>
						<th>password</th>
						<td>
							<input type="password" name="password" value="${userVO.password}" class="form-control" placeholder="password" aria-describedby="basic-addon1"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<span type="submit" class="btn btn-primary pull-right" value="로그인" onclick="sendData()">로그인 </span> <span type="reset" class="btn btn-primary pull-left" value="reset" class="pull-left">reset
							</span>
						</td>
					</tr>
				</form>
			</tbody>
		</table>
	</div>
</body>
</html>