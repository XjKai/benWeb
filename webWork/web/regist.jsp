<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //获取动态路径 格式：   http://localhost:8080/test/
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <base href="<%=basePath%>">
    <title>注册页面</title>
    <link rel="stylesheet" type="text/css" href="css/regist.css">
<!--    <script type="text/javascript" src="js/jquery.min.js"></script>-->
</head>
<body>
    <div class="wrapper">
        <article>
            <div class="main">
                <form action="UserServlet?action=register" method="post">
                    <div class="userName">
                        <input type="text" name="username" placeholder="用户名" autocomplete="off"><em></em>
                    </div>
                    <div class="password">
                        <input type="password" name="password" placeholder="密码" autocomplete="off"><em></em>
                    </div>
                    <div class="password">
                        <input type="email" name="email" placeholder="邮箱" autocomplete="off"><em></em>
                    </div>
                    <div class="againpwd">
                        <input type="password" name="pwd" placeholder="再次输入密码" autocomplete="off"><em></em>
                    </div>
                    <button type="submit">注册</button>
                </form>
            </div>
        </article>

    </div>
</body>
</html>
