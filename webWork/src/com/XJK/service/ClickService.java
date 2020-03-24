package com.XJK.service;

import com.XJK.pojo.Click;
import com.XJK.pojo.Message;

import java.util.List;

public interface ClickService {
    /**
     * 保存click
     * @param click
     * @return
     */
    public  int addClick(Click click);

    /**
     * 获取所有click
     * @return
     */
    public List<Click> getAllClick();

    /**
     * 根据id删除click
     * @param id
     * @return
     */
    public int deleteClick(Long id);

    /**
     * 重新排序id
     */
    public void reOrderClickId();
}
