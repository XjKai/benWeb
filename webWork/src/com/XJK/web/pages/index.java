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

@WebServlet(urlPatterns = "/index" )
public class index extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ViewEngine viewEngine = new ViewEngine(request.getServletContext());  //模板引擎
        //设置页面编码
        response.setHeader("Content-Type","text/html; charset=UTF-8");
        try {
            //index页面显示前三次动态
            List<Article> allArticle = NewsFilesPathData.getArticleList();
            List<Article> articles = new ArrayList<>();
            for (int i = 0; i < allArticle.size(); i++) {
                if (i<3){
                    articles.add(allArticle.get(i));
                } else if(i == 2){
                    break;
                }
            }

            Map<String, Object> m = new HashMap<>();  //渲染的数据
            m.put("articles",articles);
            viewEngine.render(m, response.getWriter(), "index.html");
        } catch (Exception e){
            viewEngine.render(null,response.getWriter(),"404.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
