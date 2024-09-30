package files;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideFilesTest {

    @Test
    void selenideDownloadTest() throws FileNotFoundException {
        open("href=https://github.com/junit-team/junit5/raw/refs/heads/main/README.md");
        File downloadedFile = $(".raw-button").download();

    }
}

