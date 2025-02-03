package ru.sidorov.telros.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sidorov.telros.config.ApplicationConfig;
import ru.sidorov.telros.services.abstracts.CommonService;

import java.io.*;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {

    private final ApplicationConfig applicationConfig;

    @Override
    public String saveFile(String fileSubfolder, Integer entityId, String fileExtension, InputStream fileInputStream) throws IOException {
        String relativePath = fileSubfolder + "/" + CommonService.getImageSubfolder();
        String fullPath = applicationConfig.getBaseFileDir() + relativePath;
        String fileName = entityId + "_" + CommonService.getDateFormat().format(new Date()) + "." + fileExtension;

        (new File(fullPath)).mkdirs();
        OutputStream out;
        int read = 0;
        byte[] bytes = new byte[1024];

        out = new FileOutputStream(fullPath + fileName);
        while ((read = fileInputStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        out.flush();
        out.close();

        return relativePath + fileName;
    }
}
