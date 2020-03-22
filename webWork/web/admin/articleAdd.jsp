<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 2020/3/11
  Time: 11:37
  To change this template use File | Settings | File Templates.

  管理员才能登陆的界面
--%>
<%@ page language="java" contentType="text/html;   charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.XJK.service.ArticleService" %>
<%@ page import="com.XJK.service.impl.ArticleServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.XJK.pojo.Article" %>
<%@ page import="com.XJK.srcFileManage.NewsFilesPathData" %>
<%
    ArticleService articleService =new ArticleServiceImpl();
    List<Article> articleList = articleService.getAllArticle();
    String username = (String) request.getSession().getAttribute("user");

    //获取动态路径 格式：   http://localhost:8080/test/
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <base href="<%=basePath%>">
    <title>添加文章</title>
    <script charset="utf-8"   src="kindeditor/kindeditor-all.js"></script>
    <script   charset="utf-8" src="kindeditor/lang/zh-CN.js"></script>
    <script   src="kindeditor/jquery-1.10.2.js" ></script>

    <%--加载kindeditor富文本编辑器--%>
    <script>
        KindEditor.ready(function(K) {
            window.editor = K.create('#editor_id',{
                uploadJson :   'kindeditor/jsp/upload_json.jsp',
                fileManagerJson :   'kindeditor/jsp/file_manager_json.jsp',
                allowFileManager : true
            });
        });
        //点击使用ajax访问修改文章的servlet，先判断文章内容
        $(function(){
            //异步提交文章标题与内容
            $('#saveBtn').click(function(){
                var   title   = $('input[name=title]').val();
                //将kindeditor中的内容同步到textarea中
                window.editor.sync();
                var   content = $('#editor_id').val().trim();
                if(title.length   == 0){
                    alert('请输入标题'); return;
                }
                if(content.length   == 0){
                    alert('请输入内容'); return;
                }
                $.ajax({
                    url:   'articleServlet?action=saveArticle',
                    type: 'post',
                    data:{
                        'title'     : title,
                        'content' : content
                    },
                    timeout: 1000,
                    success: function (data, status) {
                        if(data.success == true){
                            alert(data.msg);
                        }else if(data.success == false){
                            alert(data.msg);
                            window.location.href="login.jsp";
                        }
                    },
                    fail: function (err, status) {
                        console.log(err)
                    }
                });
            });
        });
    </script>

</head>

<body style="width:   860px; margin: 0 auto;">
<div>
    <h5>管理员： <%=username%></h5>
    <form   id="articleForm">
        <div style="margin-top:   10px;">
            <input   type="text" name="title" value="<%out.println(articleList.get(0).getTitle());%>" style="width:   300px; height: 30px;"> </input>
        </div>
        <div style="margin-top:   10px;">
            <textarea   id="editor_id" name="content"   style="width:700px;height:400px;">
                <%--先显示原来内容--%>
                 <%  out.println(articleList.get(0).getContent()); %>
            </textarea>
        </div>
    </form>
    <div>
        <input   type="button" value="提交修改" id="saveBtn" />
        <a href="UserServlet?action=adminLogout" method="post">退出登陆</a>
    </div>
</div>
</body>


<%--循环检查登陆状态--%>
<script>
    var queryStatus = setInterval("loginStatus();",3000);//3s执行一次
    function loginStatus () {
        $.ajax({
            url:   'UserServlet?action=loginStatus',
            type: 'post',
            timeout: 1000,
            success: function (data, status) {
                if(data.loginOn == false){
                    clearInterval(queryStatus);   //结束询问
                    alert("登陆异常");
                    window.location.href="login.jsp";
                }
            },
            fail: function (err, status) {
                console.log(err)
            }
        });
    }
</script>

<%--获取所有文章的资源路径,并通知ArticleServlet.jsp更新文件，清除不用的文件--%>
<div style="display:none" id="news"><%=NewsFilesPathData.getAddStringOfContent(articleList) %> </div>
<script>
    window.onload = function () {
        var imgPath  = document.getElementById("news").getElementsByTagName("img");
        var srcFilesArr = [];
        console.log(imgPath.length)
        if(imgPath.length >0) {
            for (var i = 0; i < imgPath.length; i++) {
                srcFilesArr.push(imgPath[i].src);
            }
        }
        $.ajax({
            url:   'articleServlet?action=updateArticleSrcFiles',
            dataType: "json",
            traditional: true,
            type: 'post',
            data:{
                'srcFiles' : srcFilesArr
            },
            timeout: 1000,
            success: function (data, status) {
                if(data.success == true){
                    alert(data.msg);
                }else if(data.success == false){
                    alert(data.msg);
                }
            },
            fail: function (err, status) {
                console.log(err)
            }
        });
    }
</script>
</html>
