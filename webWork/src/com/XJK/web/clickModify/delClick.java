package com.XJK.web.clickModify;

import com.XJK.service.ClickService;
import com.XJK.service.impl.ClickServiceImpl;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delClick")
public class delClick extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean succ = false;
        Long  id = Long.valueOf(request.getParameter("id"));
        if(id != null){
            ClickService clickService = new ClickServiceImpl();
            succ = clickService.deleteClick(id)>0;
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
