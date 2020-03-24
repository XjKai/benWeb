package com.XJK.dao;

import com.XJK.pojo.Click;
import com.XJK.pojo.Message;

import java.util.List;

public interface ClickDao {
    /**
     * 保存click
     * @param click
     * @return
     */
    public  int saveClick(Click click);

    /**
     * 获取所有click
     * @return
     */
    public List<Click> queryAllClick();

    /**
     * 根据id删除click
     * @param id
     * @return
     */
    public int deleteClickById(Long id);

    /**
     * 重新排序id
     */
    public  void reOrderId();

}
