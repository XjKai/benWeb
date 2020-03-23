package com.XJK.test;

import com.XJK.pojo.Message;
import com.XJK.service.MessageService;
import com.XJK.service.impl.MessageServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageServiceImplTest {
    MessageService messageService = new MessageServiceImpl();
    @Test
    public void addMessage() {
        messageService.addMessage(new Message("熊健凯","522697031@qq.com","13972907293","hello"));
        messageService.addMessage(new Message("xxx","xxxx@qq.com","xxxxxxx","xxxxxxxxxxx"));

    }

    @Test
    public void getAllMessage() {
        System.out.println(messageService.getAllMessage());
    }

    @Test
    public void deleteMessage() {
        System.out.println(messageService.deleteMessage((long) 2));
    }
}