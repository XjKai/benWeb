<%@ page language="java" contentType="text/html;   charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.XJK.service.ArticleService" %>
<%@ page import="com.XJK.service.impl.ArticleServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.XJK.pojo.Article" %>
<%@ page import="com.XJK.srcFileManage.NewsFilesPathData" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.XJK.pojo.Message" %>
<%@ page import="com.XJK.service.MessageService" %>
<%@ page import="com.XJK.service.impl.MessageServiceImpl" %>
<%
    String username = (String) request.getSession().getAttribute("user");

    ArticleService articleService =new ArticleServiceImpl();
    List<Article> articles = articleService.getAllArticle();    //所有文章

    MessageService messageService = new MessageServiceImpl();
    List<Message> messages = messageService.getAllMessage();     //所有留言

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
    <!-- Custom Styles-->
    <link href="admin/assets/css/custom-styles.css" rel="stylesheet" />
    <!-- TABLE STYLES-->
    <link href="admin/assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />

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
                    <li>
                        <%--点击不跳转--%>
                        <a  href="javascript:;" onclick="pageMessage()"><i class="fa fa-dashboard"></i> 留言</a>
                    </li>
                </ul>
            </div>
        </nav>
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper" >
            <%--当前页面标识--%>
            <div  class="alert alert-info">
                <center id="page_now">修改</center>
            </div>

            <%--点击量数据--%>
            <div id="page-inner" class="panel panel-default">
                <div class="panel-heading">
                    网站访问数据
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                            <thead>
                            <tr>
                                <th>Rendering engine</th>
                                <th>Browser</th>
                                <th>Platform(s)</th>
                                <th>Engine version</th>
                                <th>CSS grade</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="odd gradeX">
                                <td>Trident</td>
                                <td>Internet Explorer 4.0</td>
                                <td>Win 95+</td>
                                <td class="center">4</td>
                                <td class="center">X</td>
                            </tr>
                            <tr class="even gradeC">
                                <td>Trident</td>
                                <td>Internet Explorer 5.0</td>
                                <td>Win 95+</td>
                                <td class="center">5</td>
                                <td class="center">C</td>
                            </tr>
                            <tr class="odd gradeA">
                                <td>Trident</td>
                                <td>Internet Explorer 5.5</td>
                                <td>Win 95+</td>
                                <td class="center">5.5</td>
                                <td class="center">A</td>
                            </tr>
                            <tr class="even gradeA">
                                <td>Trident</td>
                                <td>Internet Explorer 6</td>
                                <td>Win 98+</td>
                                <td class="center">6</td>
                                <td class="center">A</td>
                            </tr>
                            <tr class="odd gradeA">
                                <td>Trident</td>
                                <td>Internet Explorer 7</td>
                                <td>Win XP SP2+</td>
                                <td class="center">7</td>
                                <td class="center">A</td>
                            </tr>
                            <tr class="even gradeA">
                                <td>Trident</td>
                                <td>AOL browser (AOL desktop)</td>
                                <td>Win XP</td>
                                <td class="center">6</td>
                                <td class="center">A</td>
                            </tr>
                            <tr class="gradeA">
                                <td>Gecko</td>
                                <td>Firefox 1.0</td>
                                <td>Win 98+ / OSX.2+</td>
                                <td class="center">1.7</td>
                                <td class="center">A</td>
                            </tr>
                            <tr class="gradeA">
                                <td>Gecko</td>
                                <td>Firefox 1.5</td>
                                <td>Win 98+ / OSX.2+</td>
                                <td class="center">1.8</td>
                                <td class="center">A</td>
                            </tr>
                            <tr class="gradeA">
                                <td>Gecko</td>
                                <td>Firefox 2.0</td>
                                <td>Win 98+ / OSX.2+</td>
                                <td class="center">1.8</td>
                                <td class="center">A</td>
                            </tr>
                            <tr class="gradeA">
                                <td>Gecko</td>
                                <td>Firefox 3.0</td>
                                <td>Win 2k+ / OSX.3+</td>
                                <td class="center">1.9</td>
                                <td class="center">A</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
            <script> $("#page-inner").hide();//隐藏</script>

            <%--修改页面--%>
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
                        <a href="javascript:;" id="delBtn" class="btn btn-danger " style="float: left" >删除</a>
                        <a href="javascript:;" id="saveBtn" class="btn btn-primary " style="float: right" >提交修改</a>

                    </div>
                </div>
            <%--空白块--%>
            <div class="row"></div>

            <%--留言--%>
            <div id="page-message" class="row">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                网页留言
                            </div>
                            <div class="panel-body">
                                <div class="panel-group" id="accordion">
                                <%  count = 0; %>
                                <% for(Message message:messages){ count++;%>
                                <div class="panel panel-default">
                                    <a  class="btn btn-danger btn-sm " onclick="delMessage(this.name)" name="<%=message.getId()%>" style="float: right;" >删除</a>
                                    <div class="panel-heading">
                                        <h5 class="panel-title" >
                                            <a style="color: #0e76a8" data-toggle="collapse" data-parent="#accordion" href="#collapse<%=count%>"  class="collapsed"><%=message.getMname()+"  ( "+message.getMemail()+"  ;  "+message.getMphone()+" )"%></a>
                                        </h5>

                                    </div>
                                    <div id="collapse<%=count%>" class="panel-collapse collapse" style="height: 0px;">
                                        <div class="panel-body">
                                            <%=message.getMmessage()%>
                                        </div>
                                    </div>
                                </div>
                                <%}%>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            <script> $("#page-message").hide();//隐藏</script>

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
    <!-- DATA TABLE SCRIPTS -->
    <script src="admin/assets/js/dataTables/jquery.dataTables.js"></script>
    <script src="admin/assets/js/dataTables/dataTables.bootstrap.js"></script>
    <script>
        $(document).ready(function () {
            $('#dataTables-example').dataTable();
        });
    </script>
    <script src="admin/assets/js/custom-scripts.js"></script>

    <%--隐藏页面与显示页面--%>
    <script>
        function pageInnerOnView () {
            $("#page-inner").show();   //显示点击量页面
            $("#page-article").hide();  //隐藏修改页面
            $("#page-message").hide();  //隐藏留言页面
            $("#page_now").html("数据");
             window.scrollTo(0, 0);    //页面跳到顶部
        }
        function newsOnView () {
            $("#page-article").show();   //显示修改页面
            $("#page-inner").hide();    //隐藏点击量页面
            $("#page-message").hide();//隐藏留言页面
            $("#delBtn").show();      //显示删除按钮
            $("#page_now").html("修改");
            window.scrollTo(0, 0);    //页面跳到顶部
        }
        function pageMessage () {
            $("#page-message").show();   //显示留言页面
            $("#page-article").hide();   //隐藏修改页面
            $("#page-inner").hide();    //隐藏点击量页面
            $("#page_now").html("留言");
            window.scrollTo(0, 0);    //页面跳到顶部
        }

        function addNews() {
            $("#page-article").show();   //显示修改页面
            $("#page-inner").hide();    //隐藏点击量页面
            $("#delBtn").hide();       //隐藏删除按钮
            $("#page-message").hide();  //隐藏留言页面
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
        // 删除留言
        function delMessage(name) {
                $.ajax({
                    url:   'delMessage',
                    data:{
                        'id'     : name,
                    },
                    type: 'post',
                    timeout: 1000,
                    success: function (data, status) {
                        alert("留言删除成功");
                        location.reload();
                    },
                    fail: function (err, status) {
                        alert("留言删除失败");
                    }
                });
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

</body>

</html>