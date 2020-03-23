package com.XJK.web.articleModify;

import com.XJK.service.MessageService;
import com.XJK.service.impl.MessageServiceImpl;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/delMessage")
public class delMessage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean succ = false;
        Long  id = Long.valueOf(request.getParameter("id"));

        if(id != null){
            MessageService messageService = new MessageServiceImpl();
            succ = messageService.deleteMessage(id)>0;
        }
        //通过AJAX告知前端是否成功
        JSONObject json = new JSONObject();
        json.put("success",   succ);
        response.setHeader("Content-Type","application/json; charset=UTF-8");
        response.getWriter().print(json.toString());
        response.getWriter().close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
