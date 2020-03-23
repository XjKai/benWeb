package com.XJK.web.pages;

import com.XJK.pojo.Message;
import com.XJK.service.MessageService;
import com.XJK.service.impl.MessageServiceImpl;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/contact" )
public class contact extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //通过AJAX告知前端是否成功
        JSONObject json = new JSONObject();
        String message = "";
        String name = request.getParameter("name"); if(name==null||name.length()==0){message+="姓名不能为空，";}
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");if(phone==null||phone.length()==0){message+="电话不能为空，";}
        String comments = request.getParameter("comments");if(comments==null||comments.length()==0){message+="内容不能为空，";}

        if(message.length()==0){
            //将内容添加到数据库
            MessageService messageService = new MessageServiceImpl();
            int count = messageService.addMessage(new Message(name,email,phone,comments));
            message += count>0?"发送成功":"发送失败";
        }
        json.put("message",   message);
        response.setHeader("Content-Type","application/json; charset=UTF-8");
        response.getWriter().print(json.toString());
        response.getWriter().close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
