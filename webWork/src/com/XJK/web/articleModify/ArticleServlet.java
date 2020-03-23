package com.XJK.web.articleModify;

import com.XJK.pojo.Article;
import com.XJK.service.ArticleService;
import com.XJK.service.impl.ArticleServiceImpl;
import com.XJK.srcFileManage.NewsFilesPathData;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ArticleServlet extends BaseServlet {
    //修改文章的实现类
    private ArticleService articleService = new ArticleServiceImpl();


    protected void saveArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int nResult = 0;
        if(request.getSession().getAttribute("user") != null)  //判断当前是否是登陆状态
        {
            String title = request.getParameter("title");
            String introduct = request.getParameter("introduct");
            String img = request.getParameter("img");
            String content = request.getParameter("content");
            if ( !articleService.existArticle(title)){
                //文章添加
                nResult =  articleService.addArticle(new Article(title,NewsFilesPathData.getdate(),introduct,img,content));
            } else {
                //更新文章
                nResult = articleService.updateArticle(new Article(title,NewsFilesPathData.getdate(),introduct,img,content));
            }
        } else {
            nResult = -1;
        }
        //通过AJAX告知前端是否成功
        JSONObject   json = new JSONObject();
        if(nResult   > 0){
            json.put("success",   true);
            json.put("msg",   "保存成功");
        }else if(nResult == -1){
            json.put("success",   false);
            json.put("msg",   "保存失败,请登陆后保存");
        }else {
            json.put("success",   false);
            json.put("msg",   "保存失败，请检查格式");
        }
        response.setHeader("Content-Type","application/json; charset=UTF-8");
        response.getWriter().print(json.toString());
        response.getWriter().close();
    }

    protected void deleteArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean succ = false;
        String  title = (String) request.getParameter("delTitle");
        List<String> delPath = null;
        if(title != null){
            delPath = articleService.getArticle(title).contentSrcPath;
            articleService.deleteArticle(title);    //从数据库中删除
            succ = true;
        }
        //通过AJAX告知前端是否成功
        JSONObject   json = new JSONObject();
        json.put("success",   succ);
        response.setHeader("Content-Type","application/json; charset=UTF-8");
        response.getWriter().print(json.toString());
        response.getWriter().close();
        articleService.reOrderArticleId();       //重新排列数据库id
        String realPath=this.getServletContext().getRealPath("/");   //真实的物理路径  D:\bneWeb\out\artifacts\webWork\
        NewsFilesPathData.delArticleSrc(delPath,realPath);  //删除文章对应的资源
    }

    /**
     *  更新文章的图片资源，删除多余图片
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateArticleSrcFiles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // String savePath = pageContext.getServletContext().getRealPath("/") + "attached/";
        String[] srcFiles = request.getParameterValues("srcFiles");

        String realPath=this.getServletContext().getRealPath("/");   //真实的物理路径  D:\bneWeb\out\artifacts\webWork\

        String path = request.getContextPath();
        String urlPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    //URL路径 http://localhost:8080/webWork/

    }



}
