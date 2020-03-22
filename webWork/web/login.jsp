<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	//获取动态路径 格式：   http://localhost:8080/test/
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html lang="zxx">

<head>
	<base href="<%=basePath%>">
	<title>管理员登陆</title>
	<!-- Meta tag Keywords -->

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8" />
	<meta name="keywords" content="Latest Login Form Responsive Widget,Login form widgets, Sign up Web forms , Login signup Responsive web form,Flat Pricing table,Flat Drop downs,Registration Forms,News letter Forms,Elements" />

	<!-- Meta tag Keywords -->

	<!-- css files -->
	<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />

</head>

<body>
	<div class="main-bg">
		<!-- title -->
		<h1>Latest Login Form</h1>
		<!-- //title -->
		<!-- content -->
		<div class="sub-main-w3">
			<div class="bg-content-w3pvt">
				<div class="top-content-style">
					<h2>后台管理中心</h2>
				</div>
				<form action="UserServlet?action=adminLogin" method="post">
<!--					<p class="legend">登陆<span class="fa fa-hand-o-down"></span></p>-->
					<div class="input">
						<input type="text" placeholder="管理员账号" name="username" autocomplete="off" required />
						<span class="fa fa-envelope"></span>
					</div>
					<div class="input">
						<input type="password" placeholder="管理员密码" name="password"  autocomplete="off" required />
						<span class="fa fa-unlock"></span>
					</div>
					<button type="submit" class="btn submit">
						登陆
					</button>
				</form>
				<!-- <a href="#" class="bottom-text-w3ls">Forgot Password?</a> -->
			</div>
		</div>

	</div>
</body>

</html>