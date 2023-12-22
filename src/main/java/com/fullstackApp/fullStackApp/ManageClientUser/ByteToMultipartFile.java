package com.fullstackApp.fullStackApp.ManageClientUser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StreamUtils;

import java.io.*;

public class ByteToMultipartFile implements MultipartFile {

    private final byte[] bytes;
    private final String name;
    private final String originalFilename;
    private final String contentType;

    public ByteToMultipartFile(byte[] bytes, String name, String originalFilename, String contentType) {
        this.bytes = bytes;
        this.name = name;
        this.originalFilename = originalFilename;
        this.contentType = contentType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOriginalFilename() {
        return originalFilename;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean isEmpty() {
        return bytes == null || bytes.length == 0;
    }

    @Override
    public long getSize() {
        return bytes.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return bytes;
    }

    @Override
    @JsonIgnore
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(bytes);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        try (OutputStream outputStream = new FileOutputStream(dest)) {
            outputStream.write(bytes);
        }
    }
}