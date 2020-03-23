package com.XJK.dao.impl;

import com.XJK.dao.MessageDao;
import com.XJK.pojo.Message;

import java.util.List;

public class MessageDaoImpl extends BaseDao implements MessageDao {
    @Override
    public int saveMessage(Message message) {
        String sql = "insert into t_message(Mname, Memail, Mphone, Mmessage) values (?,?,?,?)";
        return update(sql, message.getMname(),message.getMemail(),message.getMphone(),message.getMmessage());
    }

    @Override
    public List<Message> queryAllMessage() {
        String sql = "select * from t_message";
        return queryMessage(sql);
    }

    @Override
    public int deleteMessageById(Long id) {
        String sql = "delete from t_message where id = ?";
        return update(sql, id);
    }
}
