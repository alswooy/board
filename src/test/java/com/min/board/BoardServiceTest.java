package com.min.board;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardServiceTest {

    @Autowired
    BoardService service;
    @Test
    public void find() throws Exception {
        service.removeAll();
        BoardDto dto = new BoardDto("TestService", "TestService");
        assertTrue(service.write(dto) == 1);
        BoardDto dto2 = service.find(dto.getBno());
        assertNotNull(dto2);
        assertEquals(dto.getBno(), dto2.getBno());
        assertEquals(dto.getContent(), dto2.getContent());
        assertEquals(dto.getTitle(), dto2.getTitle());
    }

    @Test
    public void write() throws Exception {
        BoardDto dto = new BoardDto("TestService", "TestService");
        assertTrue(service.write(dto) == 1);
        assertTrue(service.write(dto) == 1);
//        assertTrue(service.postCntAll() == 2);
//        service.removeAll();
//        assertTrue(service.postCntAll() == 0);
    }

    @Test
    public void modify() throws Exception {
        service.removeAll();
        BoardDto dto = new BoardDto("TestService", "TestService");
        assertTrue(service.write(dto) == 1);

        BoardDto dto2 = new BoardDto("TestService2", "TestService2");
        assertTrue(service.modify(dto.getBno(), dto2) == 1);
        dto = service.find(dto.getBno());
        assertEquals(dto.getContent(), dto2.getContent());
        assertEquals(dto.getTitle(), dto2.getTitle());
        System.out.println(dto.getContent() + " " + dto2.getContent());

        BoardDto dto3 = new BoardDto("TestService3", "TestService3");
        assertTrue(service.modify(dto.getBno(), dto3) == 1);
        dto = service.find(dto.getBno());
        assertEquals(dto.getContent(), dto3.getContent());
        assertEquals(dto.getTitle(), dto3.getTitle());
    }

    @Test
    public void remove() throws Exception {
        service.removeAll();
        BoardDto dto = new BoardDto("TestService", "TestService");
        assertTrue(service.write(dto) == 1);
        assertTrue(service.postCntAll() == 1);

        service.remove(dto.getBno());
        assertTrue(service.postCntAll() == 0);
    }

    @Test
    public void views() throws Exception {
        service.removeAll();
        BoardDto dto = new BoardDto("TestService", "TestService");
        assertTrue(service.write(dto) == 1);
        assertTrue(dto.getView_cnt() == 0);
        assertTrue(service.views(dto.getBno()) == 1);
        assertTrue(service.views(dto.getBno()) == 1);
        assertTrue(service.views(dto.getBno()) == 1);

        dto=service.find(dto.getBno());
        assertTrue(dto.getView_cnt() == 3);
    }

    @Test
    public void postCntAll() throws Exception {
        service.removeAll();
        BoardDto dto = new BoardDto("TestService", "TestService");
        assertTrue(service.write(dto) == 1);
        assertTrue(service.postCntAll() != 2);
        assertTrue(service.postCntAll() == 1);

        BoardDto dto2 = new BoardDto("TestService2", "TestService2");
        assertTrue(service.write(dto2) == 1);
        assertTrue(service.postCntAll() == 2);
        assertTrue(service.postCntAll() != 1);
    }

    @Test
    public void findAll() throws Exception {
        service.removeAll();
        BoardDto dto = new BoardDto("TestService", "TestService");
        assertTrue(service.write(dto) == 1);
        BoardDto dto2 = new BoardDto("TestService2", "TestService2");
        assertTrue(service.write(dto2) == 1);
        BoardDto dto3 = new BoardDto("TestService3", "TestService3");
        assertTrue(service.write(dto3) == 1);

        List<BoardDto> dtos = service.findAll();
        assertTrue(dtos.size() == 3);

        List<BoardDto> dtos2 = new ArrayList<>();
        dtos2.add(dto);
        dtos2.add(dto2);
        dtos2.add(dto3);

        assertEquals(dtos.size(), dtos2.size());
    }

    @Test
    public void OldBnoTest() throws Exception {
        service.removeAll();
        BoardDto dto = new BoardDto("TestService", "TestService");
        assertTrue(service.write(dto) == 1);
        BoardDto dto2 = new BoardDto("TestService2", "TestService2");
        assertTrue(service.write(dto2) == 1);
        BoardDto dto3 = new BoardDto("TestService3", "TestService3");
        assertTrue(service.write(dto3) == 1);

        assertEquals(dto.getBno(), service.oldBno().getBno());
        assertTrue(service.remove(dto.getBno()) == 1);
        assertNotEquals(dto.getBno(), service.oldBno().getBno());
        assertEquals(dto2.getBno(), service.oldBno().getBno());
    }
}