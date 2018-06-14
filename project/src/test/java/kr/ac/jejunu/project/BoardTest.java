package kr.ac.jejunu.project;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardTest {

    public static final String PATH = "/api/board";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void get(){
        Integer num = 1;
        String id = "abc";
        String title = "허윤호";
        String content = "홍";
        String time = "2018-05-05";
        Board board = restTemplate.getForObject(PATH + "/" + num, Board.class);
        assertThat(board.getNum(), is(num));
        assertThat(board.getId(), is(id));
        assertThat(board.getTitle(), is(title));
        assertThat(board.getContent(), is(content));
        assertThat(board.getTime(), is(time));
    }

    @Test
    public void list(){
        List<Board> boards = restTemplate.getForObject(PATH + "/list", List.class);
        assertThat(boards, not(IsEmptyCollection.empty()));
    }

    @Test
    public void create(){
        String id = "abc";
        String title = "def";
        String content = "hong";
        String time = "2018-06-09 00:00:00";
        Board createdBoard = createBoard(id, title, content, time);
        validate(id, title, content, time, createdBoard);
    }

    private void validate(String id, String title, String content, String time, Board createdBoard) {
        Board resultBoard = restTemplate.getForObject(PATH + "/" + createdBoard.getNum(), Board.class);
        assertThat(resultBoard.getId(), is(id));
        assertThat(resultBoard.getTitle(), is(title));
        assertThat(resultBoard.getContent(), is(content));
        assertThat(resultBoard.getTime(), is(time));
    }

    private Board createBoard(String id, String title, String content, String time) {
        Board board = new Board();
        board.setId(id);
        board.setTitle(title);
        board.setContent(content);
        board.setTime(time);
        return restTemplate.postForObject(PATH, board, Board.class);
    }
}