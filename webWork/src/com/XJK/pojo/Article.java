package com.XJK.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Article {
    public Long id;
    public String title;
    public String putTime;
    public String introduct;
    public String img;
    public String content;
    public String imgPath = "";
    public List<String> contentSrcPath;
    private Pattern pattern = Pattern.compile("src=\"[^\"]*\"?");
    String sepa = java.io.File.separator;

    public Article() {
    }

    public Article(String title,String putTime, String introduct, String img, String content) {
        this.title = title;
        this.content = content;
        this.putTime = putTime;
        this.introduct = introduct;
        this.img = img;
    }
    public Article(ResultSet resultSet){
        try {
            this.id = resultSet.getLong("id");
            this.title = resultSet.getString("title");
            this.content = resultSet.getString("content");
            this.putTime = resultSet.getString("putTime");
            this.introduct = resultSet.getString("introduct");
            this.img = resultSet.getString("img");
            setImgPath(this.img);     //获取简介图片的路径
            setContentSrcPath(this.content);    //获取文中的资源路径
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getImgPath() {
        return imgPath;
    }
    public List<String> getContentSrcPath() {
        return contentSrcPath;
    }
    public void setContentSrcPath(String content) {
        this.contentSrcPath = new ArrayList<>();
        String s = "";
        Matcher m = pattern.matcher(content);
        while (m.find()) {
            s = content.substring(m.start(), m.end());
            s = s.replaceAll("src=\"","");
            s = s.replaceAll("\"","");
            this.contentSrcPath.add(s);
           // src="/attached/image/20200322/20200322230634_975.jpg" alt="" />
        }
        this.contentSrcPath.add(imgPath);
    }

    public void setImgPath(String img) {
        Matcher m = pattern.matcher(img);
        while (m.find()) {
            this.imgPath = img.substring(m.start(), m.end());
            this.imgPath = this.imgPath.replaceAll("src=\"","");
            this.imgPath = this.imgPath.replaceAll("\"","");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPutTime() {
        return putTime;
    }

    public void setPutTime(String putTime) {
        this.putTime = putTime;
    }

    public String getIntroduct() {
        return introduct;
    }

    public void setIntroduct(String introduct) {
        this.introduct = introduct;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Object getparArr(){
        return new Object[]{id,title,content};
    }


    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", putTime='" + putTime + '\'' +
                ", introduct='" + introduct + '\'' +
                ", img='" + img + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
