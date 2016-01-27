package com.javachan.web;

import com.javachan.domain.Board;
import com.javachan.domain.Thread;
import com.javachan.service.BoardRepository;
import com.javachan.service.ThreadRepository;
import com.javachan.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    ThreadService threadService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("boards", boardRepository.findAll());

        return "board/index";
    }

    @RequestMapping(value = "/_/{slug}", method = RequestMethod.GET)
    public String show(@PathVariable String slug, @RequestParam(required = false) Integer page, Model model) {
        Board board = boardRepository.findOneBySlug(slug);
        model.addAttribute("board", board);

        int currentPage = (page == null || page < 1) ? 0 : page - 1;
        model.addAttribute("threads", threadService.getPage(board, currentPage));

        if (!model.containsAttribute("thread")) {
            model.addAttribute("thread", new Thread());
        }

        return "board/show";
    }

}
