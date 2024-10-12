package files;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
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

    //если нет href
//    static {
//        Configuration.fileDownload = FileDownloadMode.PROXY;
//        Configuration.downloadsFolder = "downloads";
//    }
//}

    @Test
    void selenideDownloadTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadedFile = $("a[href*='/raw/']").download();
        try (InputStream is = new FileInputStream(downloadedFile)) {
            byte[] bytes = is.readAllBytes();
            String textContent = new String(bytes, StandardCharsets.UTF_8);
            assertThat(textContent).contains("This repository is the home of ");
        }
    }

    @Test
    void selenideUploadFile() {
        open("https://fineuploader.com/demos.html");
        $("input[type='file']").uploadFromClasspath("cat.jpg");
        $("div.qq-file-info").shouldHave(Condition.text("cat.jpg")); // проверка загрузки файла по тексту
//        $("span.qq-upload-file-selector").shouldHave(Condition.attribute("title", "cat.jpg")); // проверка загрузки файла по тегу

    }

}


