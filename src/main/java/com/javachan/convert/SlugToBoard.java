package com.javachan.convert;

import com.javachan.domain.Board;
import com.javachan.service.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SlugToBoard implements Converter<String, Board>{

    @Autowired
    BoardRepository repository;

    @Override
    public Board convert(String slug) {
        return repository.findOneBySlug(slug);
    }

}