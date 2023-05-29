package by.fmpibsu.stogram.controller;

import by.fmpibsu.stogram.entity.Message;
import by.fmpibsu.stogram.service.MessageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

@Controller
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    public static class MessageJson {
        public long chat;
        public long from;
        public String text;
    }

    @PostMapping("/api/send")
    @ResponseBody
    ResponseEntity<Void> sendMessage(@RequestBody MessageJson messageRequest, HttpSession session) {
        if (!Objects.equals(session.getAttribute("uidSignedIn"), messageRequest.from)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        messageService.sendMessage(messageRequest.from, messageRequest.chat, messageRequest.text);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/load-chat")
    @ResponseBody
    ResponseEntity<List<Message>> loadChat(@RequestBody long chatId, HttpSession session) {
        return ResponseEntity.ok(messageService.loadChat(chatId));
    }
}
