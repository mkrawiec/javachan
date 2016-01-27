package com.javachan.service;

import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileStoreService {

    public File save(MultipartFile upload) throws IOException {
        File file = storeToDisk(upload);

        return renameFile(file);
    }

    private File storeToDisk(MultipartFile upload) throws IOException {
        byte[] bytes = upload.getBytes();
        File file = createFile(upload.getOriginalFilename());

        BufferedOutputStream stream =
                new BufferedOutputStream(new FileOutputStream(file));
        stream.write(bytes);
        stream.close();
        return file;
    }

    private File renameFile(File file) throws IOException {
        File newFile = createFile(generateFullName(file));
        Files.move(file, newFile);

        return newFile;
    }

    private String generateFullName(File file) throws IOException {
        String filename = generateFileHash(file);
        String extension = Files.getFileExtension(file.getName());

        return String.format("%s.%s", filename, extension);
    }

    private String generateFileHash(File file) throws IOException {
        String hash = Files.hash(file, Hashing.md5()).toString();

        return hash.substring(0, 15);
    }

    private File createFile(String filename) throws IOException {
        return new File("build/resources/main/static/uploads/" + filename);
    }

}
