package by.fmpibsu.stogram.controller;

import by.fmpibsu.stogram.entity.Chat;
import by.fmpibsu.stogram.service.ChatService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/api/chats-list")
    @ResponseBody
    ResponseEntity<List<Chat>> chatsList(@RequestBody long memberId, HttpSession session) {
        if (!Objects.equals(session.getAttribute("uidSignedIn"), memberId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(chatService.getAllWith(memberId));
    }
}
