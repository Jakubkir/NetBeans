/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.zadanieo4b;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author patsz
 */
public class Test {
    

public class CsvFileReaderTest {
    @Test
    public void testReadBooks() throws IOException {
        // Create a mock CSV file
        String csvData = "1984,George Orwell,978-0451524935,268\n" +
                "Fahrenheit 451,Ray Bradbury,978-0345342966,208\n";

        // Mock the input stream
        CsvReader csvReader = mock(CsvFileReader.class);
        when(csvReader.readBooks("test.csv")).thenReturn(parseCsvData(csvData));

        // Verify that the CSV data was read correctly
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book("1984", "George Orwell", "978-0451524935", 268));
        expectedBooks.add(new Book("Fahrenheit 451", "Ray Bradbury", "978-0345342966", 208));

        assertEquals(expectedBooks, csvReader.readBooks("test.csv"));
    }

    private List<Book> parseCsvData(String csvData) throws IOException {
        List<Book> books = new ArrayList<>();

        String[] lines = csvData.split("\n");
        for (String line : lines) {
            String[] fields = line.split(",");
            String title = fields[0];
            String author = fields[1];
            String isbn = fields[2];
            int pages = Integer.parseInt(fields[3]);
            books.add(new Book(title, author, isbn, pages));
        }

        return books;
    }
}

public class XmlFileWriterTest {
    @Test
    public void testWriteXml() throws IOException {
        // Create a mock list of books
        List<Book> books = new ArrayList<>();
        books.add(new Book("1984", "George Orwell", "978-0451524935", 268));
        books.add(new Book("Fahrenheit 451", "Ray Bradbury", "978-0345342966", 208));

        // Create a mock output stream
        OutputStream outputStream = mock(ByteArrayOutputStream.class);

        // Mock the XmlWriter class
        XmlWriter xmlWriter = mock(XmlFileWriter.class);
        xmlWriter.writeXml(books, "test.xml");

        // Verify that the XML output is correct
        String expectedXml = "<Books>\n" +
                "  <Book>\n" +
                "    <Title>1984</Title>\n" +
                "    <Author>George Orwell</Author>\n" +
                "    <ISBN13>978-0451524935</ISBN13>\n" +
                "    <Pages>268</Pages>\n" +
                "  </Book>\n" +
                "  <Book>\n" +
                "    <Title>Fahrenheit 451</Title>\n" +
                "    <Author>Ray Bradbury</Author>\n" +
                "    <ISBN13>978-0345342966</ISBN13>\n" +
                "    <Pages>208</Pages>\n" +
                "  </Book>\n" +
                "</Books>\n";

        assertEquals(expectedXml, outputStream.toString());
    }
}
    

