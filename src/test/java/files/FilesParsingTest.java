package files;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class FilesParsingTest {

    ClassLoader cl = FilesParsingTest.class.getClassLoader();

    @Test
    void pdfParseTest() throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloadPdf = $("a[href='junit-user-guide-5.11.2.pdf']").download();
        PDF content = new PDF(downloadPdf);
        assertThat(content.author).contains("Sam Brannen");
    }

    @Test
    void xlsParseTest() throws Exception {

        try (InputStream resourceAsStream = cl.getResourceAsStream("shkola.xlsx")) {
            XLS content = new XLS(resourceAsStream);
            System.out.println("");

        }


        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloadPdf = $("a[href='junit-user-guide-5.11.2.pdf']").download();
        PDF content = new PDF(downloadPdf);
        assertThat(content.author).contains("Sam Brannen");
    }

}