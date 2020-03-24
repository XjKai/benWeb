package com.XJK.pojo;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Click {
    private Long id;
    private String ip;
    private String browser;
    private String system;
    private String cDate;


    public Click(String ip, String browser, String system, String cDate) {
        this.ip = ip;
        this.browser = browser;
        this.system = system;
        this.cDate = cDate;
    }

    public Click(ResultSet resultSet){
        try {
            this.id = resultSet.getLong("id");
            this.ip = resultSet.getString("ip");
            this.browser = resultSet.getString("browser");
            this.system = resultSet.getString("system");
            this.cDate = resultSet.getString("cDate");
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getcDate() {
        return cDate;
    }

    public void setcDate(String cDate) {
        this.cDate = cDate;
    }

    @Override
    public String toString() {
        return "Click{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", browser='" + browser + '\'' +
                ", system='" + system + '\'' +
                ", cDate='" + cDate + '\'' +
                '}';
    }
}
