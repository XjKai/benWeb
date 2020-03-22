package com.XJK.test;

import com.XJK.dao.ArticleDao;
import com.XJK.dao.impl.ArticleDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArticleDaoImplTest {
    ArticleDao articleDao = new ArticleDaoImpl();
    @Test
    public void queryContentByTitle() {
    }

    @Test
    public void saveArticle() {
    }

    @Test
    public void queryAllArticle() {
    }

    @Test
    public void deleteArticleByTitle() {
        System.out.println(articleDao.deleteArticleByTitle("文章2"));
    }

    @Test
    public void reOrderId(){
         String sepa = java.io.File.separator;
        System.out.println(sepa);
    }
}