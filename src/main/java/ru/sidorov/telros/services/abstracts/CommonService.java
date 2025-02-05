package ru.sidorov.telros.services.abstracts;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import static java.time.OffsetDateTime.now;

/**
 * Сервис для сохранения файла
 */
public interface CommonService {

    /**
     * Возврат изображения подпапки
     *
     * @return дата в формате папок
     */
    static String getImageSubfolder() {
        return DateTimeFormatter.ofPattern("yyyy").format(now()) +
                "/" + DateTimeFormatter.ofPattern("MM").format(now()) +
                "/" + DateTimeFormatter.ofPattern("dd").format(now()) + "/";
    }

    /**
     * Общий простой формат даты
     *
     * @return {@link SimpleDateFormat}
     */
    static SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("HH_mm_ss_SSS");
    }

    /**
     *
     * @param fileSubfolder подпапка с файлами
     * @param entityId идентификатор объекта (идентификатор пользователя, идентификатор проекта и т. д.) для именования файлов
     * @param fileInputStream поток ввода файла
     * @return полный путь к файлу
     * @throws IOException возможное исключение
     */
    String saveFile(String fileSubfolder,
                    Integer entityId,
                    String fileExtension,
                    InputStream fileInputStream) throws IOException;
}
