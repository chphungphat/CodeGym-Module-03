package service.file_service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import entity.Cart;
import service.CartService;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CartFileService {
    private static final CartFileService cartFileService = new CartFileService();

    private CartFileService() {}

    public static CartFileService getInstance() {
        return cartFileService;
    }

    private final String CART_FILEPATH = "src//data//cart.csv";

    public void writeCartList() {
        try {
            FileWriter fw = new FileWriter(CART_FILEPATH);
            CSVWriter csvWriter = new CSVWriter(fw, CSVWriter.DEFAULT_SEPARATOR,
                                                    CSVWriter.NO_QUOTE_CHARACTER,
                                                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                                                    CSVWriter.DEFAULT_LINE_END);
            for (Cart cart : CartService.getInstance().getCartList()) {
                String[] cartStringArray = cart.toArray();
                csvWriter.writeNext(cartStringArray);
            }
            csvWriter.close();
            fw.close();
        } catch (IOException exception) {
            System.err.println("Write file Error");
            exception.printStackTrace();
        }
    }

    public void readCartList() {
        try {
            FileReader fr = new FileReader(CART_FILEPATH);
            CSVReader csvReader = new CSVReader(fr);
            String[] data;
            while ((data = csvReader.readNext()) != null) {
                Cart newCart = CartService.getInstance().toCart(data);
                CartService.getInstance().getCartList().add(newCart);
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
