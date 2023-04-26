/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.zadanieo4b;

/**
 *
 * @author patsz
 */
public class Java1 {

    public static void main(String[] args) {
       public interface CsvReader {
    List<Book> readBooks(String filePath) throws IOException;
}


public interface XmlWriter {
    void writeXml(List<Book> books, String filePath) throws IOException;
}


public class Book {
    private String title;
    private String author;
    private String isbn;
    private int pages;

    public Book(String title, String author, String isbn, int pages) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}


public class CsvFileReader implements CsvReader {
    @Override
    public List<Book> readBooks(String filePath) throws IOException {
        List<Book> books = new ArrayList<>();


        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            String title = data[0].trim();
            String author = data[1].trim();
            String isbn = data[2].trim();
            int pages = Integer.parseInt(data[3].trim());
            books.add(new Book(title, author, isbn, pages));
        }
        csvReader.close();


        return books;
    }
}




public class XmlFileWriter implements XmlWriter {
    @Override
    public void writeXml(List<Book> books, String filePath) throws IOException {
        BufferedWriter xmlWriter = new BufferedWriter(new FileWriter(filePath));
        xmlWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n");
        xmlWriter.write("<Books>\n");
        for (Book book : books) {
            xmlWriter.write("\t<Book>\n");
            xmlWriter.write("\t\t<Title>" + book.getTitle() + "</Title>\n");
            xmlWriter.write("\t\t<Author>" + book.getAuthor() + "</Author>\n");
            xmlWriter.write("\t\t<ISBN13>" + book.getIsbn() + "</ISBN13>\n");
            xmlWriter.write("\t\t<Pages>" + book.getPages() + "</Pages>\n");
            xmlWriter.write("\t</Book>\n");
        }
        xmlWriter.write("</Books>\n");
        xmlWriter.close();
    }
}




public class Main {
    public static void main(String[] args) {
        String csvFilePath = "path/to/csv/file.csv";
        String xmlFilePath = "path/to/xml/file.xml";


        CsvReader csvReader = new CsvFileReader();
        XmlWriter xmlWriter = new XmlFileWriter();


        try {
            List<Book> books = csvReader.readBooks(csvFilePath);
            xmlWriter.writeXml(books, xmlFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    }

