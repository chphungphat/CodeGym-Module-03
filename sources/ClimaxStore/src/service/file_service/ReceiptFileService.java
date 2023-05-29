package service.file_service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import entity.Receipt;
import service.ReceiptService;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReceiptFileService {
    private static final ReceiptFileService receiptFileService = new ReceiptFileService();

    private ReceiptFileService() {}

    public static ReceiptFileService getInstance() {
        return receiptFileService;
    }

    private final String RECEIPT_FILEPATH = "src//data//receipt.csv";

    public void writeReceiptList() {
        try {
            FileWriter fw = new FileWriter(RECEIPT_FILEPATH);
            CSVWriter csvWriter = new CSVWriter(fw, CSVWriter.DEFAULT_SEPARATOR,
                                                    CSVWriter.NO_QUOTE_CHARACTER,
                                                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                                                    CSVWriter.DEFAULT_LINE_END);
            for (Receipt receipt : ReceiptService.getInstance().getReceiptList()) {
                String[] receiptStringArray = receipt.toArray();
                csvWriter.writeNext(receiptStringArray);
            }
            csvWriter.close();
            fw.close();
        } catch (IOException exception) {
            System.err.println("Write file Error");
            exception.printStackTrace();
        }
    }

    public void readReceiptList() {
        try {
            FileReader fr = new FileReader(RECEIPT_FILEPATH);
            CSVReader csvReader = new CSVReader(fr);
            String[] data;
            while ((data = csvReader.readNext()) != null) {
                Receipt newReceipt = ReceiptService.getInstance().toReceipt(data);
                ReceiptService.getInstance().getReceiptList().add(newReceipt);
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
