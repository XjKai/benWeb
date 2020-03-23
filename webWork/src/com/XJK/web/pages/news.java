package com.XJK.web.pages;

import com.XJK.pojo.Article;
import com.XJK.service.ArticleService;
import com.XJK.service.impl.ArticleServiceImpl;
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

@WebServlet(urlPatterns = "/news")
public class news extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ViewEngine viewEngine = new ViewEngine(request.getServletContext());
        //设置页面编码
        response.setHeader("Content-Type","text/html; charset=UTF-8");
        try {
            //获取请求的页面
            String p = request.getParameter("page");
            Integer page = (p == null? 1:Integer.valueOf(p))-1;

            ArticleService articleService =new ArticleServiceImpl();
            List<Article> allArticle = articleService.getAllArticle();    //所有文章

            List<Article> articles = new ArrayList<>();
            //取出三篇
            for (int i = page*3; i < (page+1)*3; i++) {
                if (i < allArticle.size()){
                    articles.add(allArticle.get(i));
                }
            }
            Integer pages = allArticle.size()/3 + 1;
            Integer index = page + 1;

            Map<String,Object> m = new HashMap<>();  //渲染的数据
            m.put("pages",pages);  //所有的页数
            m.put("articles",articles);              //当前页要显示的新闻
            m.put("index",index); //当前页是第几页
            m.put("front",index <= 1?index:index-1);   //前一页
            m.put("rear",index >= pages?index:index+1);    //后一页

            viewEngine.render(m,response.getWriter(),"blog-standard.html");
        } catch (Exception e){
            viewEngine.render(null,response.getWriter(),"404.html");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
