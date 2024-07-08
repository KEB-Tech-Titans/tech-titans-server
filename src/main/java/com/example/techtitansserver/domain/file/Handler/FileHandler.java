package com.example.techtitansserver.domain.file.Handler;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Slf4j
@Configuration
public class FileHandler {

    @Value("${resource.file.path}")
    private String filePath;

    public void init() {
        filePath = filePath.replace("/", File.separator).replace("\\", File.separator);
    }

    public String saveFile(MultipartFile multipartFile) throws IOException {
        init();
        String originalFilename = multipartFile.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);

        String saveFilename = makeFileName(extension);
        String savePath = filePath + File.separator + saveFilename;

        File file = new File(savePath);
        ensureParentDirectoryExists(file);
        multipartFile.transferTo(file);
        setFilePermissions(file, savePath);
        return savePath;
    }

    public String makeFileName(String extension) {
        return (System.nanoTime() + "_" + UUID.randomUUID() + "." + extension);
    }

    private void ensureParentDirectoryExists(File file) {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }
    private void setFilePermissions(File file, String savePath) throws IOException {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                file.setReadable(true);
                file.setWritable(false);
                file.setExecutable(false);
            } else {
                Runtime.getRuntime().exec("chmod 400 " + savePath);
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
