package com.min.board;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class boardDaoTest {
    @Autowired
    BoardDao dao;
    @Test
    public void nowTest() throws Exception {
        System.out.println(dao.now());
    }

    @Test
    public void insertTest() throws Exception {
        dao.deleteAll();
        BoardDto dto = new BoardDto("Test", "Test");
        assertTrue(dao.insert(dto) == 1);
        assertTrue(dao.insert(dto) == 1);
        assertTrue(dao.count() == 2);
        dao.deleteAll();
    }

    @Test
    public void selectTest() throws Exception {
        dao.deleteAll();
        BoardDto dto = new BoardDto("Test", "Test"); //1이 뜬다.
        assertTrue(dao.insert(dto) == 1);
        BoardDto dto2 = dao.select(dto.getBno());
        assertNotNull(dto2);
        assertEquals(dto.getBno(), dto2.getBno());
        assertEquals(dto.getContent(), dto2.getContent());
        assertEquals(dto.getTitle(), dto2.getTitle());
    }

    @Test
    public void updateTest() throws Exception {
        dao.deleteAll();
        BoardDto dto = new BoardDto("Test", "Test");
        assertTrue(dao.insert(dto) == 1);
        BoardDto dto2 = new BoardDto("Test2", "Test2");
        assertTrue(dao.update(dto.getBno(), dto2) == 1);
        BoardDto dto3 = new BoardDto("Test3", "Test3");
        assertTrue(dao.update(dto.getBno(), dto3) == 1);
    }

    @Test
    public void deleteTest() throws Exception {
        dao.deleteAll();
        BoardDto dto = new BoardDto("Test", "Test");
        assertTrue(dao.insert(dto) == 1);
        assertTrue(dao.delete(dto.getBno()) == 1);
        BoardDto dto2 = dao.select(dto.getBno());
        assertNull(dto2);
    }

    @Test
    public void countTest() throws Exception {
        dao.deleteAll();
        assertTrue(dao.count() == 0);
        BoardDto dto = new BoardDto("Test", "Test");
        assertTrue(dao.insert(dto) == 1);
        assertTrue(dao.count() == 1);
        BoardDto dto2 = new BoardDto("Test", "Test");
        assertTrue(dao.insert(dto2) == 1);
        assertTrue(dao.count() == 2);
        BoardDto dto3 = dao.select(dto.getBno());
        assertNotNull(dto3);
    }

    @Test
    public void insertBoardTestData() throws Exception {
        dao.deleteAll();
        for (int i = 1; i <= 100; i++) {
            BoardDto dto = new BoardDto("Test" + i, "Test" + i);
            dao.insert(dto);
        }
    }

    @Test
    public void viewCountTest() throws Exception {
        dao.deleteAll();
        BoardDto dto = new BoardDto("Test", "Test");
        assertTrue(dao.insert(dto) == 1);
        assertTrue(dto.getView_cnt() == 0);
        assertTrue(dao.viewCnt(dto.getBno()) == 1);
        assertTrue(dao.viewCnt(dto.getBno()) == 1);
        assertTrue(dao.viewCnt(dto.getBno()) == 1);
        dto = dao.select(dto.getBno());
        assertTrue(dto.getView_cnt() == 3);
    }

    @Test
    public void selectListTest() throws Exception {
        dao.deleteAll();
        BoardDto dto = new BoardDto("Test", "Test");
        assertTrue(dao.insert(dto) == 1);
        BoardDto dto2 = new BoardDto("Test2", "Test2");
        assertTrue(dao.insert(dto2) == 1);
        BoardDto dto3 = new BoardDto("Test3", "Test3");
        assertTrue(dao.insert(dto3) == 1);

        List<BoardDto> dtos = new ArrayList<>();
        dtos.add(dto);
        dtos.add(dto2);
        dtos.add(dto3);

        List<BoardDto> dtos2 = dao.selectAll();

        assertEquals(dtos.size(), dtos2.size());
    }

    @Test
    public void selectOldTest() throws Exception {
        dao.deleteAll();
        BoardDto dto = new BoardDto("Test", "Test");
        assertTrue(dao.insert(dto) == 1);
        BoardDto dto2 = new BoardDto("Test2", "Test2");
        assertTrue(dao.insert(dto2) == 1);
        BoardDto dto3 = new BoardDto("Test3", "Test3");
        assertTrue(dao.insert(dto3) == 1);
        assertEquals(dto.getBno(), dao.selectOld().getBno());

        assertTrue(dao.delete(dto.getBno()) == 1);
        assertEquals(dto2.getBno(), dao.selectOld().getBno());
    }
}