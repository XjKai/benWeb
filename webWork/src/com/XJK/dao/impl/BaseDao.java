package com.XJK.dao.impl;

import com.XJK.pojo.Article;
import com.XJK.pojo.Click;
import com.XJK.pojo.Message;
import com.XJK.pojo.User;
import com.XJK.utile.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 操作数据库
 */
public abstract class BaseDao {
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 更新数据库 增/删/改
     * @param sql
     * @param args
     * @return
     */
    public int update(String sql, Object ...args){
        Connection connection = JdbcUtils.getConnection();
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            int count = 1;
            for(Object i : args){
                ps.setObject(count, i); // 注意：索引从1开始
                count++;
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
        return  -1;
    }

    /**
     * 查询用户
     * @param sql
     * @param args
     * @return
     */
    public List<User> queryUser(String sql, Object ...args){
        Connection connection =JdbcUtils.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            int count = 1;
            for(Object i : args){
                ps.setObject(count, i); // 注意：索引从1开始
                count++;
            }
            List<User> userList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    userList.add( new  User(rs));
                }
                return userList;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
        return null;
    }

    /**
     * 查询文章
     * @param sql
     * @param args
     * @return
     */
    public List<Article> queryArticle(String sql, Object ...args){
        Connection connection =JdbcUtils.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            int count = 1;
            for(Object i : args){
                ps.setObject(count, i); // 注意：索引从1开始
                count++;
            }
            List<Article> articleList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                articleList.add( new  Article(rs));
            }
            return articleList;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
        return null;
    }

    /**
     * 查询message
     * @param sql
     * @param args
     * @return
     */
    public List<Message> queryMessage(String sql, Object ...args){
        Connection connection =JdbcUtils.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            int count = 1;
            for(Object i : args){
                ps.setObject(count, i); // 注意：索引从1开始
                count++;
            }
            List<Message> messages = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                messages.add( new Message(rs));
            }
            return messages;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
        return null;
    }
    /**
     * 查询message
     * @param sql
     * @param args
     * @return
     */
    public List<Click> queryClick(String sql, Object ...args){
        Connection connection =JdbcUtils.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            int count = 1;
            for(Object i : args){
                ps.setObject(count, i); // 注意：索引从1开始
                count++;
            }
            List<Click> clicks = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                clicks.add( new Click(rs));
            }
            return clicks;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
        return null;
    }
//    JdbcUtils方法
//    /**
//     * 查询返回多个javaBean的sql语句
//     * @param type    返回对象类型
//     * @param sql    指向的sql语句
//     * @param args   sql对应的参数
//     * @param <T>    返回类型的泛型
//     * @return
//     */
//    public <T> List<T> queryForList(Class<T> type, String sql, Object ...args){
//        Connection connection =JdbcUtils.getConnection();
//
//        try {
//            return queryRunner.query(connection,sql,new BeanListHandler<T>(type),args);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JdbcUtils.close(connection);
//        }
//        return null;
//    }
//
//    /**
//     *  执行返回一行一列的sql语句
//     * @param sql  执行的sql语句
//     * @param args sql对应的参数值
//     * @return
//     */
//    public Object queryForSingleValue(String sql,Object ... args){
//        Connection connection = JdbcUtils.getConnection();
//        try {
//           return queryRunner.query(connection,sql,new ScalarHandler(),args);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JdbcUtils.close(connection);
//        }
//        return  null;
//    }
}
