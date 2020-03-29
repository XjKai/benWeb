package com.XJK.service.impl;

import com.XJK.dao.MessageDao;
import com.XJK.dao.impl.MessageDaoImpl;
import com.XJK.pojo.Message;
import com.XJK.service.MessageService;

import java.util.List;

public class MessageServiceImpl implements MessageService {
    private MessageDao messageDao = new MessageDaoImpl();
    @Override
    public int addMessage(Message message) {
        return messageDao.saveMessage(message);
    }

    @Override
    public List<Message> getAllMessage() {
        return messageDao.queryAllMessage();
    }

    @Override
    public int deleteMessage(Long id) {
        return messageDao.deleteMessageById(id);
    }

    @Override
    public int clearMessage() {
        return messageDao.deleteAllMessage();
    }
}
