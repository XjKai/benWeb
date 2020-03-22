package com.XJK.web.user;

import com.XJK.pojo.User;
import com.XJK.service.UserService;
import com.XJK.service.impl.UserServiceImpl;
import com.XJK.web.ViewEngine;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class UserServlet extends BaseServlet {
    UserService userService =new UserServiceImpl();
    Map<String,HttpSession> userSession = new HashMap<>();  //用来存储已经登陆的用户的用户名和session
    //管理员登陆
    protected void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String proPath = request.getContextPath();   //获取当前工程名 如/test
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //用户名密码正确
        if (userService.login(new User(null,username,password,null)) != null ){
            isLoginAndRemove(username);
            //将用户加入session
            request.getSession().setAttribute("user",username);
            userSession.put(username,request.getSession());
            //请求重定向
//            response.sendRedirect(proPath+"/admin/articleAdd.jsp");
            response.sendRedirect(proPath+"/admin/adminPage.jsp");
        } else {
            request.getSession().invalidate();
            //请求重定向
            response.sendRedirect(proPath+"/login.jsp");
        }
    }


    //管理员登出
    protected void adminLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String proPath = request.getContextPath();   //获取当前工程名 如/test
        Object username = request.getSession().getAttribute("user");
        //从已登录用户中清除该用户
        if(userSession.get(username) != null) { userSession.remove(username);}
        //失效session中当前用户的sessionId
        request.getSession().invalidate();
        //请求转发到登陆界面
        response.sendRedirect(proPath + "/login.jsp");
    }



    //用户注册
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");

        if (password.equals(pwd)){
            if (userService.existUsername(username)){  //用户名已存在
                //跳转回注册页面
                request.getRequestDispatcher("/regist.jsp").forward(request,response);
            } else {
                //注册
                userService.registerUser(new User(null,username,password,email));
                //跳转回注册成功页面
                response.addHeader("Cache-Control", "no-store, must-revalidate");
                request.getRequestDispatcher("/registSucc.jsp").forward(request,response);
            }
        } else {  //密码不一致,跳回注册页面
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
        }
    }

    /**
     * 检查请求的用户是否在登陆状态
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    protected void loginStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean loginOn = true;
        if (request.getSession().getAttribute("user") == null){
            loginOn = false;
        }
        //通过AJAX返回数据
        JSONObject json = new JSONObject();
        if(loginOn){
            json.put("loginOn",   true);
        } else {
            json.put("loginOn",   false);
        }
        response.setHeader("Content-Type","application/json; charset=UTF-8");
        response.getWriter().print(json.toString());
        response.getWriter().close();
    }



    /**
     * @return
     * @throws ServletException
     * @throws IOException
     */
    protected void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String> m = new HashMap<>();
        m.put("user","XJK");
        ViewEngine viewEngine = new ViewEngine(request.getServletContext());
        viewEngine.render(m,response.getWriter(),"hello.html");

    }

    /**
     * 检查用户是否登陆，若已登录，则下线已登录的用户
     * @param username
     * @return
     */
    protected boolean isLoginAndRemove(String username) {
        if ( (userSession.get(username)) != null){     //当前用户已登录
            userSession.get(username).invalidate(); //将其下线
            return true;
        }
        return false;
    }
}
