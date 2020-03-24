package com.XJK.service.impl;

import com.XJK.dao.ClickDao;
import com.XJK.dao.impl.ClickDaoImpl;
import com.XJK.pojo.Click;
import com.XJK.service.ClickService;

import java.util.List;

public class ClickServiceImpl implements ClickService {
    ClickDao clickDao = new ClickDaoImpl();
    @Override
    public int addClick(Click click) {
        return clickDao.saveClick(click);
    }

    @Override
    public List<Click> getAllClick() {
        return clickDao.queryAllClick();
    }

    @Override
    public int deleteClick(Long id) {
        return clickDao.deleteClickById(id);
    }

    @Override
    public void reOrderClickId() {
        clickDao.reOrderId();
    }
}
