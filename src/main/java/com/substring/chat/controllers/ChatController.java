package com.substring.chat.controllers;

import com.substring.chat.entities.Message;
import com.substring.chat.entities.Room;
import com.substring.chat.playload.MessageRequest;
import com.substring.chat.repositories.RoomRepository;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Controller
@CrossOrigin("http://localhost:3000")
public class ChatController {


    private RoomRepository roomRepository;

    public ChatController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public Message sendMessage(

            @DestinationVariable String roomId,
            @RequestBody MessageRequest request
    ){

       Room room = roomRepository.findByRoomId(request.getRoomId());
       Message message = new Message();
       message.setSender(request.getSender());
       message.setContent(request.getContent());
       message.setTimeStamp(LocalDateTime.now());

       if (room!=null){
           room.getMessages().add(message);
           roomRepository.save(room);
       }else{

           throw new RuntimeException("room not found !!");
       }



       return message;
    }


}
