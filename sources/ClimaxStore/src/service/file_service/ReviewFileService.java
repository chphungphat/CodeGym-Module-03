package service.file_service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import entity.Review;
import service.ReviewService;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReviewFileService {
    private static final ReviewFileService reviewFileService = new ReviewFileService();

    private ReviewFileService() {}

    public static ReviewFileService getInstance() {
        return reviewFileService;
    }

    private final String REVIEW_FILEPATH = "src//data//review.csv";

    public void writeReviewList() {
        try {
            FileWriter fw = new FileWriter(new File(REVIEW_FILEPATH));
            CSVWriter csvWriter = new CSVWriter(fw, CSVWriter.DEFAULT_SEPARATOR,
                                                    CSVWriter.NO_QUOTE_CHARACTER,
                                                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                                                    CSVWriter.DEFAULT_LINE_END);
            for (Review review : ReviewService.getInstance().getReviewList()) {
                String[] reviewStringArray = review.toArray();
                csvWriter.writeNext(reviewStringArray);
            }
            csvWriter.close();
            fw.close();
        } catch (IOException exception) {
            System.err.println("Write file Error");
            exception.printStackTrace();
        }
    }

    public void readReviewList() {
        try {
            FileReader fr = new FileReader(REVIEW_FILEPATH);
            CSVReader csvReader = new CSVReader(fr);
            String[] data;
            while ((data = csvReader.readNext()) != null) {
                LocalDate reviewDate = LocalDate.parse(data[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                Review newReview = new Review(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2], Double.parseDouble(data[3]), reviewDate);
                ReviewService.getInstance().getReviewList().add(newReview);
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
