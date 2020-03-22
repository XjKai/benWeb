<%@ page language="java" contentType="text/html;   charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.XJK.service.ArticleService" %>
<%@ page import="com.XJK.service.impl.ArticleServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.XJK.pojo.Article" %>
<%@ page import="com.XJK.srcFileManage.NewsFilesPathData" %>
<%@ page import="java.util.Map" %>
<%
    String username = (String) request.getSession().getAttribute("user");
    List<Article> articles = NewsFilesPathData.getArticleList();
    int mapLen = articles.size();

    //获取动态路径 格式：   http://localhost:8080/test/
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>admin</title>
    <!-- Bootstrap Styles-->
    <link href="admin/assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FontAwesome Styles-->
    <link href="admin/assets/css/font-awesome.css" rel="stylesheet" />
    <!-- Morris Chart Styles-->
    <link href="admin/assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
    <!-- Custom Styles-->
    <link href="admin/assets/css/custom-styles.css" rel="stylesheet" />


    <script charset="utf-8"   src="kindeditor/kindeditor-all.js"></script>
    <script   charset="utf-8" src="kindeditor/lang/zh-CN.js"></script>
    <script   src="kindeditor/jquery-1.10.2.js" ></script>

    <%--加载kindeditor富文本编辑器--%>
    <script>
         KindEditor.ready(function(K) {
            window.editor1 = K.create('#editor_id',{
                uploadJson :   'kindeditor/jsp/upload_json.jsp',
                fileManagerJson :   'kindeditor/jsp/file_manager_json.jsp',
                themeType: 'simple',
                allowFileManager : true
            });
        });
         KindEditor.ready(function(K) {
            window.editor2 = K.create('#editor_idImg',{
                uploadJson :   'kindeditor/jsp/upload_json.jsp',
                fileManagerJson :   'kindeditor/jsp/file_manager_json.jsp',
                themeType: 'simple',
                items : [
                    'image'
                ],
                allowFileManager : true
            });
        });
        //点击使用ajax访问修改文章的servlet，先判断文章内容
        $(function(){
            //异步提交文章标题与内容
            $('#saveBtn').click(function(){
                //将kindeditor中的内容同步到textarea中
                window.editor1.sync();
                window.editor2.sync();
                var   title   = $('input[name=title]').val();
                var   introduct   = $('input[name=introduct]').val();
                var   img = $('#editor_idImg').val().trim();
                var   content = $('#editor_id').val().trim();
                if(title.length   == 0){alert('请输入标题'); return;}
                if(introduct.length   == 0){alert('请输入简介'); return;}
                if(img.length   == 0){alert('请输入简介图片'); return;}
                if(content.length   == 0){alert('请输入内容'); return;}
                $.ajax({
                    url:   'articleServlet?action=saveArticle',
                    type: 'post',
                    data:{
                        'title'     : title,
                        'introduct' : introduct,
                        'img'     : img,
                        'content' : content
                    },
                    timeout: 1000,
                    success: function (data, status) {
                        if(data.success == true){
                            alert(data.msg);
                            location.reload();
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
            //删除文章
            $('#delBtn').click(function(){
                $.ajax({
                    url:   'articleServlet?action=deleteArticle&delTitle='+$("#title_id").val(),
                    type: 'post',
                    timeout: 1000,
                    success: function (data, status) {
                        if(data.success == true){
                            alert("删除成功");
                            location.reload();
                        }else if(data.success == false){
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


<body>

    <div id="wrapper">
        <nav class="navbar navbar-default top-navbar" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="javascript:;" onclick="newsOnView()"  class="navbar-brand" > <strong>管理员: </strong><%=username%></a>
            </div>
            <ul class="nav navbar-top-links navbar-right">
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">

                        <li class="divider"></li>
                        <li><a href="UserServlet?action=adminLogout" method="post"><i class="fa fa-sign-out fa-fw"></i> 退出</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
        </nav>
        <!--/. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
		<div id="sideNav" href=""><i class="fa fa-caret-right"></i></div>
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
                    <li>
                        <a href="javascript:;" onclick="newsOnView()"><i class="fa fa-sitemap"></i>修改<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level" >
                            <% int count = 0; %>
                            <% for (Article article : articles) {  count++; %>
                            <li >
                                <a id="title<%= count %>"  href="javascript:;" onclick="newsOnViewAndRePageView(id,this)" ><%=article.getTitle()%></a>
                            </li>
                            <div id="introduct<%= count %>" style="display: none"><%= article.getIntroduct()%></div>
                            <div id="img<%= count %>" style="display: none"><%= article.getImg()%></div>
                            <div id="content<%= count %>" style="display: none"><%= article.getContent()%></div>
                            <%}%>
                        </ul>
                    </li>
                    <li>
                        <a href="javascript:;" onclick="addNews()"><i class="fa fa-fw fa-file"></i>添加</a>
                    </li>
                    <li>
                        <%--点击不跳转--%>
                        <a  href="javascript:;" onclick="pageInnerOnView()"><i class="fa fa-dashboard"></i> 点击量</a>
                    </li>
                </ul>
            </div>
        </nav>
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper" >
<%--            当前页面--%>
            <div  class="alert alert-info">
                <center id="page_now">修改</center>
            </div>

            <div id="page-inner"  >
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-header">
                            Dashboard <small>Summary of your App</small>
                        </h1>
                    </div>
                </div>

				<div class="row">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							Line Chart
						</div>
						<div class="panel-body">
							<div id="morris-line-chart"></div>
						</div>
					</div>
					</div>
				</div>


                <div class="row">
                    <div class="col-md-9 col-sm-12 col-xs-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Bar Chart Example
                            </div>
                            <div class="panel-body">
                                <div id="morris-bar-chart"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-12 col-xs-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Donut Chart Example
                            </div>
                            <div class="panel-body">
                                <div id="morris-donut-chart"></div>
                            </div>
                        </div>
                    </div>

                </div>
				<div class="row">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							Area Chart
						</div>
						<div class="panel-body">
							<div id="morris-area-chart"></div>
						</div>
					</div>
					</div>
				</div>
                <!-- /. ROW  -->
            </div>
            <script> $("#page-inner").hide();//隐藏</script>

            <div id="page-article" style="margin-top: 30px">
                <form role="form">
                    <div class="form-group has-success">

                        <label class="control-label" >标题</label>
                        <input  id="title_id" class="form-control"  name="title"  value="" >

                        <label class="control-label" >简介</label>
                        <input  id="introduct_id" class="form-control"  name="introduct"  value="" >

                    </div>
                    <div class="form-group has-warning">

                        <label class="control-label" >简介图片</label>
                        <textarea   id="editor_idImg" class="form-control" name="content"   style="width:60%;height:300px;">

                        </textarea>
                        <label class="control-label" >修改</label>
                        <textarea   id="editor_id" class="form-control" name="content"   style="width:100%;height:800px;">

                        </textarea>
                    </div>

                </form>
                    <div>
                        <a href="javascript:;" id="delBtn" class="btn btn-primary " style="float: left" >删除</a>
                        <a href="javascript:;" id="saveBtn" class="btn btn-primary " style="float: right" >提交修改</a>

                    </div>
                </div>
        </div>
        <!-- /. PAGE WRAPPER  -->
    </div>
    <!-- /. WRAPPER  -->
    <!-- JS Scripts-->
    <!-- jQuery Js -->
    <script src="admin/assets/js/jquery-1.10.2.js"></script>
    <!-- Bootstrap Js -->
    <script src="admin/assets/js/bootstrap.min.js"></script>
    <!-- Metis Menu Js -->
    <script src="admin/assets/js/jquery.metisMenu.js"></script>
    <!-- Morris Chart Js -->
    <script src="admin/assets/js/morris/raphael-2.1.0.min.js"></script>
    <script src="admin/assets/js/morris/morris.js"></script>
	<script src="admin/assets/js/easypiechart.js"></script>
	<script src="admin/assets/js/easypiechart-data.js"></script>
    <!-- Custom Js -->
    <script src="admin/assets/js/custom-scripts.js"></script>



    <%--隐藏页面与显示页面--%>
    <script>
        function pageInnerOnView () {
            $("#page-inner").show();   //显示
            $("#page-article").hide();    //隐藏
            $("#page_now").html("数据");
             window.scrollTo(0, 0);    //页面跳到顶部
        }
        function newsOnView () {
            $("#page-article").show();   //显示
            $("#page-inner").hide();    //隐藏
            $("#delBtn").show();    //显示
            $("#page_now").html("修改");
            window.scrollTo(0, 0);    //页面跳到顶部
        }
        function addNews() {
            $("#page-article").show();   //显示
            $("#page-inner").hide();    //隐藏
            $("#delBtn").hide();    //隐藏
            $("#page_now").html("添加");
            //清空内容
            $("#title_id").val("");
            $("#introduct_id").val("");
            $("#editor_idImg").val("");
            $("#editor_id").html("");
            //获取富文本编辑器，清空内容
            window.editor1.html("");
            window.editor2.html("");
        }
        function newsOnViewAndRePageView(titleList,title) {
            var lisIindex = titleList.substring(5);
            var introduct = $("#introduct"+lisIindex).text();
            var img = $("#img"+lisIindex).html();
            var content = $("#content"+lisIindex).html();
            $("#title_id").val(title.innerText);
            $("#introduct_id").val(introduct);
            window.editor2.html(img);
            window.editor1.html(content);
            newsOnView();
        }

    </script>

    <%--循环检查登陆状态--%>
    <script>
        var queryStatus = setInterval("loginStatus();",5000);//5s执行一次
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

<%--    &lt;%&ndash;要改成点击清除&ndash;%&gt;--%>
<%--    &lt;%&ndash;获取所有文章的资源路径,并通知ArticleServlet.jsp更新文件，清除不用的文件&ndash;%&gt;--%>
<%--    <div style="display:none" id="news"><%=NewsFilesPathData.getAddStringOfContent(articleList) %> </div>--%>
<%--    <script>--%>
<%--        window.onload = function () {--%>
<%--            var imgPath  = document.getElementById("news").getElementsByTagName("img");--%>
<%--            var srcFilesArr = [];--%>
<%--            if(imgPath.length >0) {--%>
<%--                for (var i = 0; i < imgPath.length; i++) {--%>
<%--                    srcFilesArr.push(imgPath[i].src);--%>
<%--                }--%>
<%--            }--%>
<%--            $.ajax({--%>
<%--                url:   'articleServlet?action=updateArticleSrcFiles',--%>
<%--                dataType: "json",--%>
<%--                traditional: true,--%>
<%--                type: 'post',--%>
<%--                data:{--%>
<%--                    'srcFiles' : srcFilesArr--%>
<%--                },--%>
<%--                timeout: 1000,--%>
<%--                success: function (data, status) {--%>
<%--                    if(data.success == true){--%>
<%--                        alert(data.msg);--%>
<%--                    }else if(data.success == false){--%>
<%--                        alert(data.msg);--%>
<%--                    }--%>
<%--                },--%>
<%--                fail: function (err, status) {--%>
<%--                    console.log(err)--%>
<%--                }--%>
<%--            });--%>
<%--        }--%>
<%--    </script>--%>
</body>

</html>