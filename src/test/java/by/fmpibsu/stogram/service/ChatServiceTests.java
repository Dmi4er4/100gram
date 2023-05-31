package by.fmpibsu.stogram.service;

import by.fmpibsu.stogram.dao.ChatDao;
import by.fmpibsu.stogram.entity.Chat;
import by.fmpibsu.stogram.service.impl.ChatServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ChatServiceTest {

    @InjectMocks
    private ChatServiceImpl chatService;

    @Mock
    private ChatDao chatDao;

    @BeforeEach
    void setUp() {
        Chat chat1 = new Chat(1, new Date(1212121212121L));
        Chat chat2 = new Chat(2, new Date(1212133333333L));
        List<Chat> chats = Arrays.asList(chat1, chat2);

        Mockito.when(chatDao.getAllWith(ArgumentMatchers.anyLong())).thenReturn(chats);

        List<Long> memberIds = Arrays.asList(1L, 2L, 3L);
        Chat createdChat = new Chat(1, new Date(1212133333333L));
        createdChat.setMemberIds(memberIds);
        Mockito.when(chatDao.createChat(ArgumentMatchers.eq(memberIds))).thenReturn(createdChat);
    }

    @Test
    void testGetAllWith() {
        long memberId = 1L;
        List<Chat> expectedChats = Arrays.asList(new Chat(1, new Date(1212121212121L)),
                new Chat(2, new Date(1212133333333L)));

        List<Chat> actualChats = chatService.getAllWith(memberId);

        Assertions.assertEquals(actualChats, expectedChats);
        Mockito.verify(chatDao, Mockito.times(1)).getAllWith(memberId);
    }

    @Test
    void testCreateChat() {
        List<Long> memberIds = Arrays.asList(1L, 2L, 3L);
        Chat expectedChat = new Chat(1, new Date(1212133333333L));
        expectedChat.setMemberIds(memberIds);

        Chat actualChat = chatService.createChat(memberIds);

        Assertions.assertEquals(actualChat, expectedChat);
        Mockito.verify(chatDao, Mockito.times(1)).createChat(memberIds);
    }
}
