package com.XJK.dao.impl;

import com.XJK.dao.ArticleDao;
import com.XJK.pojo.Article;

import java.util.List;

public class ArticleDaoImpl extends BaseDao implements ArticleDao {
    /**
     * 根据title查询文章内容
     * @param title
     * @return
     */
    @Override
    public Article queryContentByTitle(String title) {
        String sql = "select id, title, putTime, introduct, img, content from t_article where title = ?";
        List<Article> articleList = queryArticle(sql, title);
        if (articleList.size() > 0 ){
            return (Article) articleList.get(0);
        }
        return null;
    }

    /**
     * 添加文章
     * @param article
     * @return
     */
    @Override
    public int saveArticle(Article article) {
        String sql = "insert into t_article(title, putTime, introduct, img, content) values (?,?,?,?,?)";
        return update(sql, article.getTitle(), article.getPutTime(), article.getIntroduct(), article.getImg(), article.getContent());
    }

    /**
     * 获取所有文章
     * @return
     */
    @Override
    public List<Article> queryAllArticle() {
        String sql = "select * from t_article";
        return queryArticle(sql);
    }

    /**
     * 根据文章名删除文章
     * @param title
     * @return
     */
    @Override
    public int deleteArticleByTitle(String title) {
        String sql = "delete from t_article where title = ?";
        return update(sql, title);
    }

    /**
     * 更新存在的文章的内容
     * @param article
     * @return
     */
    @Override
    public int updateArticleByTitle(Article article) {
//        //先获取id
//        Long id  = queryContentByTitle(article.getTitle()).getId();
        //根据id更新content
        String sql = "update t_article set putTime=?, introduct=?, img=?, content=?  where title = ?";
        return update(sql, article.getPutTime(), article.getIntroduct(), article.getImg(),article.getContent(), article.getTitle());
    }

    /**
     * 重新排序数据库id
     * @return
     */
    @Override
    public void reOrderId() {
        String sql1 = "ALTER TABLE `t_article` DROP `id`" ;
        String sql2 = "ALTER TABLE `t_article` ADD `id` BIGINT NOT NULL FIRST" ;
        String sql3 = "ALTER TABLE `t_article` MODIFY COLUMN `id` BIGINT NOT NULL AUTO_INCREMENT,ADD PRIMARY KEY(id);";
        update(sql1);
        update(sql2);
        update(sql3);
    }
}
