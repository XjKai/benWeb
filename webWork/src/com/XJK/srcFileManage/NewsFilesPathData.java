package com.XJK.srcFileManage;

import com.XJK.pojo.Article;
import com.XJK.service.ArticleService;
import com.XJK.service.impl.ArticleServiceImpl;
import java.io.File;
import java.util.*;
import java.time.*;
import java.time.format.*;
import java.util.Locale;

/**
 * 这个静态类只允许管理员调用
 * 只能有一个管理员同时在线，所以调用这个静态类不存在线程安全问题
 */

public class NewsFilesPathData {
    static String sepa = File.separator;

    public static List<Article> getArticleList() {
        ArticleService articleService =new ArticleServiceImpl();
        List<Article> articleList = articleService.getAllArticle();
        return articleList;
    }


    /**
     * 将所有文章内容合并为一个字符串
     * @param list
     * @return
     */
    public static String getAddStringOfContent(List<Article> list){
        StringBuilder stringBuilder = new StringBuilder();
        for (Article article : list){
            stringBuilder.append(article.getContent());
        }
        return stringBuilder.toString();
    }

    /**
     * 清除attached文件夹中不需要的文件t_article
     * @param realPath
     */
    public static int delArticleSrc(List<String> delPath,String realPath){
        int delCount = 0;
        if(delPath == null){return 0;}
        for (String src : delPath) {
            src = src.substring(1);     //去除多余的一个斜杠
            String  realP = realPath.replaceAll("web.*","");
            if (sepa .equals("\\") ){    //winds要转换
                src = realP +src.replaceAll("/","\\\\");
            } else {                      //linux
                src = realP +src;
            }
            File file = new File(src);
            if (file.exists()){
                file.delete();
                delCount++;
            }
        }
        return delCount;
    }


    /**
     * 递归获取一个文件夹下的所有文件
     * @param dir
     * @param allfiles
     */
    public static void getAllFiles(File dir , List<File> allfiles) {
        if(dir.isDirectory()) {
            for (File i : dir.listFiles()) {
                if (i.isDirectory()) {   //是文件夹就递归
                    getAllFiles(i, allfiles);
                } else if (i.isFile()) {    //是文件就加入fileList
                    allfiles.add(i);
                }
            }
        } else if (dir.isFile()) {    //是文件就加入fileList
            allfiles.add(dir);
        }
    }

    public static String getdate(){
        ZonedDateTime zdt = ZonedDateTime.now();
        var zhFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.CHINA);
//        var usFormatter = DateTimeFormatter.ofPattern(" MMMM/dd/yyyy ", Locale.US);
        return zhFormatter.format(zdt);
    }
}
