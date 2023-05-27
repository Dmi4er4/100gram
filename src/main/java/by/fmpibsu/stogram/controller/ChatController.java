package by.fmpibsu.stogram.controller;

import by.fmpibsu.stogram.entity.Chat;
import by.fmpibsu.stogram.service.ChatService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/api/chats-list")
    @ResponseBody
    ResponseEntity<List<Chat>> chatsList(@RequestBody long memberId, HttpSession session) {
        List<Chat> result = new ArrayList<>();
        if (!session.getAttribute("uidSignedIn").equals(memberId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
        }
        result.addAll(chatService.getAllWith(memberId));
        return ResponseEntity.ok(result);
    }
}
