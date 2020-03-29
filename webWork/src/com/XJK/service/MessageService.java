package com.XJK.service;

import com.XJK.pojo.Message;

import java.util.List;

public interface MessageService {
    /**
     * 保存message
     * @param message
     * @return
     */
    public  int addMessage(Message message);

    /**
     * 获取所有messgge
     * @return
     */
    public List<Message> getAllMessage();

    /**
     * 根据id删除message
     * @param id
     * @return
     */
    public int deleteMessage(Long id);

    /**
     * 删除所有message
     * @return
     */
    public int clearMessage();

}
