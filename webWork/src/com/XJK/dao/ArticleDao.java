package com.XJK.dao;

import com.XJK.pojo.Article;

import java.util.List;

public interface ArticleDao {

    /**
     * 根据标题获取内容
     * @param title
     * @return
     */
    public Article queryContentByTitle(String title);

    /**
     * 保存文章
     * @param article
     * @return
     */
    public  int saveArticle(Article article);

    /**
     * 获取所有文章
     * @return
     */
    public List<Article> queryAllArticle();

    /**
     * 根据文章名删除文章
     * @param title
     * @return
     */
    public int deleteArticleByTitle(String title);

    /**
     * 重新排序id
     */
    public  void reOrderId();

    /**
     * 根据文章名更新文章

     * @return
     */
    public  int updateArticleByTitle(Article article);


}
