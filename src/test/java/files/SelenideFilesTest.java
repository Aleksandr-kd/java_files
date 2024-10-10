package files;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideFilesTest {

    @Test
    void selenideDownloadTest() throws Exception{
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        sleep(7000);
        File downloadedFile =  $("a[href*='/raw/']").download();
        InputStream is = new FileInputStream(downloadedFile);
        byte[] bytes = is.readAllBytes();
        String textContent = new String(bytes, StandardCharsets.UTF_8);
        assertThat(textContent).contains("This repository is the home of ");
        is.close();

        // Выводим путь к загруженному файлу
        System.out.println("File downloaded to: " + downloadedFile.getAbsolutePath());

    }
}

