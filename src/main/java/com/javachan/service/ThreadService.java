package com.javachan.service;

import com.javachan.domain.Board;
import com.javachan.domain.Thread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ThreadService {

    @Autowired
    private ThreadRepository repository;

    public Page<Thread> getPage(Board board, int start) {
        Pageable pageable = new PageRequest(start, 10);

        return repository.findByBoardOrderByLastMessage(board, pageable);
    }

}
