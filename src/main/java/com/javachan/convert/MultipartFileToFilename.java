package com.javachan.convert;


import com.javachan.service.FileStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class MultipartFileToFilename implements Converter<MultipartFile, String> {

    @Autowired
    FileStoreService fileStore;

    @Override
    public String convert(MultipartFile source) {
        try {
            File file = fileStore.save(source);
            return file.getName();
        } catch (IOException e) {
            return null;
        }
    }

}
