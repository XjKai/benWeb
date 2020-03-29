package com.XJK.dao;

import com.XJK.pojo.Message;

import java.util.List;

public interface MessageDao {

    /**
     * 保存message
     * @param message
     * @return
     */
    public  int saveMessage(Message message);

    /**
     * 获取所有messgge
     * @return
     */
    public List<Message> queryAllMessage();

    /**
     * 根据id删除message
     * @param id
     * @return
     */
    public int deleteMessageById(Long id);

    /**
     * 删除所有messgge
     * @return
     */
    public int deleteAllMessage();

}
