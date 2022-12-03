package com.example.websocket.parrot_chat;

import com.example.websocket.parrot_chat.dto.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ChatController {
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message send(final Message message) {

//        final String time = new SimpleDateFormat("HH:mm").format(new Date());
//        return new Message(message.text() + time);
        return new Message(message.text());
    }

    @GetMapping("/webs")
    public String getChat(){
        return "pages/index.html";
    }
}