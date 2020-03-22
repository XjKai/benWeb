package com.XJK.service;

import com.XJK.pojo.Article;

import java.util.List;

public interface ArticleService {
    /**
     * 根据文章名获取文章
     * @param title
     * @return
     */
    public Article getArticle(String title);

    /**
     * 获取所有文章
     * @return
     */
    public List<Article> getAllArticle();

    /**
     * 删除文章
     * @param title
     * @return
     */
    public int deleteArticle(String title);

    /**
     * 文章是否存在
     * @param title
     * @return
     */
    public boolean existArticle(String title);

    /**
     * 添加文章
     * @param article
     * @return
     */
    public  int addArticle(Article article);

    /**
     *
     */
    public int updateArticle(Article article);
    /**
     *
     */
    public void reOrderArticleId();
}
