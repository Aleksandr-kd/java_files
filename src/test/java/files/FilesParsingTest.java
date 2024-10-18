package files;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.devtools.v127.input.Input;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;


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
            assertThat(content.excel.getSheet("1").getRow(0).getCell(0).getStringCellValue()).isEqualTo("Привет");

        }


        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloadPdf = $("a[href='junit-user-guide-5.11.2.pdf']").download();
        PDF content = new PDF(downloadPdf);
        assertThat(content.author).contains("Sam Brannen");
    }


    @Test
    void csvParseTest() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("qa.csv");
                CSVReader reader = new CSVReader(new InputStreamReader(resource))

        ) {
            List<String[]> content = reader.readAll();
            assertThat(content.get(0)[1]).contains("lesson");
        }
    }


    @Test
    void zipParseTest() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("fail.zip");
                ZipInputStream zis = new ZipInputStream(resource)

        ) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                assertThat(entry.getName()).isEqualTo("sample.txt");
            }
        }
    }
}