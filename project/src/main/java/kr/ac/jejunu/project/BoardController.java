package kr.ac.jejunu.project;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardRepository boardRepository;

    @GetMapping("/{num}")
    public Board get(@PathVariable Integer num){
        return boardRepository.findById(num).get();
    }

    @GetMapping("/list")
    public List<Board> list(){
        return boardRepository.findAll();
    }

    @PostMapping
    public Board create(@RequestBody Board board){
        return boardRepository.save(board);
    }

    @PutMapping
    public void modify(@RequestBody Board board){
        boardRepository.save(board);
    }

    @DeleteMapping("/{num}")
    public void delete(@PathVariable Integer num){
        boardRepository.delete(boardRepository.findById(num).get());
    }
}