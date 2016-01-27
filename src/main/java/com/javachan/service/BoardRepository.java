package com.javachan.service;

import com.javachan.domain.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long> {
    Board findOneBySlug(String slug);
}
