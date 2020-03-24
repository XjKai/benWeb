package com.XJK.test;

import com.XJK.pojo.Click;
import com.XJK.service.ClickService;
import com.XJK.service.impl.ClickServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClickServiceImplTest {
    ClickService clickService = new ClickServiceImpl();
    @Test
    public void addClick() {
        clickService.addClick(new Click("1","1","1","1"));

    }

    @Test
    public void getAllClick() {
        System.out.println(clickService.getAllClick());
    }

    @Test
    public void deleteClick() {
        clickService.deleteClick(Long.valueOf(1));
        clickService.deleteClick(Long.valueOf(1));
    }

    @Test
    public void reOrderClickId() {
        clickService.reOrderClickId();
    }
}