package com.XJK.service.impl;

import com.XJK.dao.ArticleDao;
import com.XJK.dao.impl.ArticleDaoImpl;
import com.XJK.pojo.Article;
import com.XJK.service.ArticleService;

import java.util.List;


public class ArticleServiceImpl implements ArticleService {
    private ArticleDao articleDao = new ArticleDaoImpl();
    @Override
    public Article getArticle(String title) {
        return articleDao.queryContentByTitle(title);
    }

    @Override
    public List<Article> getAllArticle() {
        return articleDao.queryAllArticle();
    }

    @Override
    public int deleteArticle(String title) {
        return articleDao.deleteArticleByTitle(title);
    }

    @Override
    public boolean existArticle(String title) {
        if (articleDao.queryContentByTitle(title) != null){
            return true;
        }
        return false;
    }

    @Override
    public int addArticle(Article article) {
        return articleDao.saveArticle(article);
    }

    @Override
    public int updateArticle(Article article) {
        return articleDao.updateArticleByTitle(article);
    }

    @Override
    public void reOrderArticleId() {
        articleDao.reOrderId();
    }
}
