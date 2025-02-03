package ru.sidorov.telros.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application")
@Getter
@Setter
public class ApplicationConfig {

    /** Base file directory */
    private String baseFileDir;
    /** Base document directory */
    private String baseDocDir;
    /** FIle access prefix ("file:" for Linux and "file:///" for Windows)*/
    private String filePrefix;
}
