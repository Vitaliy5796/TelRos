package ru.sidorov.telros.services.abstracts;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import static java.time.OffsetDateTime.now;

public interface CommonService {

    /**
     * Returning subfolder image
     *
     * @return
     */
    static String getImageSubfolder() {
        return DateTimeFormatter.ofPattern("yyyy").format(now()) +
                "/" + DateTimeFormatter.ofPattern("MM").format(now()) +
                "/" + DateTimeFormatter.ofPattern("dd").format(now()) + "/";
    }

    /**
     * Common simple date format
     *
     * @return
     */
    static SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("HH_mm_ss_SSS");
    }

    /**
     *
     * @param fileSubfolder file subfolder
     * @param entityId entity identifier (user id, project id and so on) for file naming
     * @param fileInputStream file input stream
     * @return full path to file
     * @throws IOException
     */
    String saveFile(String fileSubfolder,
                    Integer entityId,
                    String fileExtension,
                    InputStream fileInputStream) throws IOException;
}
