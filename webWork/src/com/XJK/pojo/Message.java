package com.XJK.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Message {
    private Long id;
    private String Mname;
    private String Memail;
    private String Mphone;
    private String Mmessage;

    public Message(String mname, String memail, String mphone, String mmessage) {
        Mname = mname;
        Memail = memail;
        Mphone = mphone;
        Mmessage = mmessage;
    }
    public Message(ResultSet resultSet){
        try {
            this.id = resultSet.getLong("id");
            this.Mname = resultSet.getString("Mname");
            this.Memail = resultSet.getString("Memail");
            this.Mphone = resultSet.getString("Mphone");
            this.Mmessage = resultSet.getString("Mmessage");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMname() {
        return Mname;
    }

    public void setMname(String mname) {
        Mname = mname;
    }

    public String getMemail() {
        return Memail;
    }

    public void setMemail(String memail) {
        Memail = memail;
    }

    public String getMphone() {
        return Mphone;
    }

    public void setMphone(String mphone) {
        Mphone = mphone;
    }

    public String getMmessage() {
        return Mmessage;
    }

    public void setMmessage(String mmessage) {
        Mmessage = mmessage;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", Mname='" + Mname + '\'' +
                ", Memail='" + Memail + '\'' +
                ", Mphone='" + Mphone + '\'' +
                ", Mmessage='" + Mmessage + '\'' +
                '}';
    }
}
