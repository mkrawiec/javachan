package com.javachan.service;

import com.javachan.domain.Board;
import com.javachan.domain.Thread;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ThreadRepository extends PagingAndSortingRepository<Thread, Long> {
    @Query("select t from Thread t join t.messages m where t.board = :board and m.createdAt = (select max(createdAt) " +
           "from Message m2 where m2.thread = t) order by m.createdAt desc")
    Page<Thread> findByBoardOrderByLastMessage(@Param("board") Board board, Pageable pageable);
}
