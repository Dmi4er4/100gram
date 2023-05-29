package by.fmpibsu.stogram.controller;

import by.fmpibsu.stogram.entity.Message;
import by.fmpibsu.stogram.service.MessageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
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
    ResponseEntity<Timestamp> sendMessage(@RequestBody MessageJson messageRequest, HttpSession session) {
        if (!Objects.equals(session.getAttribute("uidSignedIn"), messageRequest.from)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var msg = messageService.sendMessage(
                messageRequest.from,
                messageRequest.chat,
                messageRequest.text
        );
        return ResponseEntity.ok(msg.getTime());
    }

    @PostMapping("/api/load-chat")
    @ResponseBody
    ResponseEntity<List<Message>> loadChat(@RequestBody long chatId, HttpSession session) {
        return ResponseEntity.ok(messageService.loadChat(chatId));
    }

    public static class FetchRequestJson {
        public long c;
        public Timestamp t;
    }

    @PostMapping("/api/new-messages")
    @ResponseBody
    ResponseEntity<List<Message>> fetchNewerMessages(
            @RequestBody FetchRequestJson request, HttpSession session) {
        var chatId = request.c;
        var lastKnownMessageTimestamp = request.t;
        return ResponseEntity.ok(messageService.fetchNewerMessages(chatId, lastKnownMessageTimestamp));
    }
}
