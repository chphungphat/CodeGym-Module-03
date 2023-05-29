package service.file_service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import entity.Library;
import service.LibraryService;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LibraryFileService {
    private static final LibraryFileService libraryFileService = new LibraryFileService();

    private LibraryFileService() {}

    public static LibraryFileService getInstance() {
        return libraryFileService;
    }

    private final String LIBRARY_FILEPATH = "src//data//library.csv";

    public void writeLibraryList() {
        try {
            FileWriter fw = new FileWriter(LIBRARY_FILEPATH);
            CSVWriter csvWriter = new CSVWriter(fw, CSVWriter.DEFAULT_SEPARATOR,
                                                    CSVWriter.NO_QUOTE_CHARACTER,
                                                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                                                    CSVWriter.DEFAULT_LINE_END);
            for (Library library : LibraryService.getInstance().getLibraryList()) {
                String[] libraryStringArray = library.toArray();
                csvWriter.writeNext(libraryStringArray);
            }
            csvWriter.close();
            fw.close();
        } catch (IOException exception) {
            System.out.println("Write file Error");
            exception.printStackTrace();
        }
    }

    public void readLibraryList() {
        try {
            FileReader fr = new FileReader(LIBRARY_FILEPATH);
            CSVReader csvReader = new CSVReader(fr);
            String[] data;
            while ((data = csvReader.readNext()) != null) {
                Library newLibrary = LibraryService.getInstance().toLibrary(data);
                LibraryService.getInstance().getLibraryList().add(newLibrary);
            }
            csvReader.close();
            fr.close();
        } catch (IOException exception) {
            System.err.println("Read file Error");
            exception.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
