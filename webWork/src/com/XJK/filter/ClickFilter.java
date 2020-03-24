package com.XJK.filter;

import com.XJK.pojo.Click;
import com.XJK.service.ClickService;
import com.XJK.service.impl.ClickServiceImpl;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
//"/news","/404.html","/newsMore","/contact.html","/services-grid.html","/team-grid.html"
@WebFilter(urlPatterns = {"/index"})
public class ClickFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
        ClickService clickService = new ClickServiceImpl();
        clickService.addClick(new Click(req.getRemoteAddr(),getBrowserName((HttpServletRequest)req),getOsName((HttpServletRequest) req),getdate()));
    }

    public void init(FilterConfig config) throws ServletException {

    }

    /**
     * 获取发起请求的浏览器名称
     */
    public static String getBrowserName(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        Browser browser = userAgent.getBrowser();
        return browser.getName();
    }

    /**
     * 获取发起请求的浏览器版本号
     */
    public static String getBrowserVersion(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        //获取浏览器信息
        Browser browser = userAgent.getBrowser();
        //获取浏览器版本号
        Version version = browser.getVersion(header);
        return version.getVersion();
    }

    /**
     * 获取发起请求的操作系统名称
     */
    public static String getOsName(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        return operatingSystem.getName();
    }

    /**
     * 获取时间
     * @return
     */
    public static String getdate(){
        ZonedDateTime zdt = ZonedDateTime.now();
        var zhFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd/HH:mm", Locale.CHINA);
//        var usFormatter = DateTimeFormatter.ofPattern(" MMMM/dd/yyyy ", Locale.US);
        return zhFormatter.format(zdt);
    }
}
