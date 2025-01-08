package com.substring.chat.entities;

import java.util.List;

public class Room {
    private String roomId; // Unique identifier for the room
    private List<Message> messages; // List of messages in the room

    // Getter for roomId
    public String getRoomId() {
        return roomId;
    }

    // Setter for roomId
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    // Getter for messages
    public List<Message> getMessages() {
        return messages;
    }

    // Setter for messages
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
