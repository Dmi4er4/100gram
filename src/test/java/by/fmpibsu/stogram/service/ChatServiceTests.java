package by.fmpibsu.stogram.service;

import by.fmpibsu.stogram.dao.ChatDao;
import by.fmpibsu.stogram.entity.Chat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ChatServiceTest {

    @Autowired
    private ChatService chatService;

    @Mock
    private ChatDao chatDao;

    @BeforeEach
    void setUp() {
        // Настройка поведения заглушки chatDao перед каждым тестом
        Chat chat1 = new Chat(1, null);
        Chat chat2 = new Chat(2, null);
        List<Chat> chats = Arrays.asList(chat1, chat2);

        Mockito.when(chatDao.getAllWith(ArgumentMatchers.anyLong())).thenReturn(chats);

        List<Long> memberIds = Arrays.asList(1L, 2L);
        Chat createdChat = new Chat(1);
        Mockito.when(chatDao.createChat(ArgumentMatchers.eq(memberIds))).thenReturn(createdChat);
    }

    @Test
    void testGetAllWith() {
        long memberId = 1L;
        List<Chat> expectedChats = Arrays.asList(new Chat(1, null), new Chat(2, null));

        List<Chat> actualChats = chatService.getAllWith(memberId);

        Assertions.assertEquals(expectedChats, actualChats);
        Mockito.verify(chatDao, Mockito.times(1)).getAllWith(memberId);
    }

    @Test
    void testCreateChat() {
        List<Long> memberIds = Arrays.asList(1L, 2L);
        Chat expectedChat = new Chat(1);

        Chat actualChat = chatService.createChat(memberIds);

        Assertions.assertEquals(expectedChat, actualChat);
        Mockito.verify(chatDao, Mockito.times(1)).createChat(memberIds);
    }
}
