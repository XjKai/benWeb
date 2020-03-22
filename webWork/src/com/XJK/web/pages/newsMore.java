package com.XJK.web.pages;

import com.XJK.pojo.Article;
import com.XJK.srcFileManage.NewsFilesPathData;
import com.XJK.web.ViewEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/newsMore")
public class newsMore extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ViewEngine viewEngine = new ViewEngine(request.getServletContext());  //模板引擎
        //设置页面编码
        response.setHeader("Content-Type","text/html; charset=UTF-8");
        try {
            //获取请求参数
            String i = request.getParameter("index");
            Integer index = (i == null ? 1 : Integer.valueOf(i));

            List<Article> allArticle = NewsFilesPathData.getArticleList();
            Map<String, Object> m = new HashMap<>();  //渲染的数据
            index = index <= allArticle.size() ? index : allArticle.size();

            m.put("article", allArticle.get(index - 1));
            m.put("front", index <= 1 ? index : index - 1);   //前一页
            m.put("rear", index >= allArticle.size() ? allArticle.size() : index + 1);    //后一页
            viewEngine.render(m, response.getWriter(), "blog1.html");
        } catch (Exception e){
        viewEngine.render(null,response.getWriter(),"404.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
