package service.file_service;

import builder.AddressBuilder;
import builder.CustomerBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import entity.Address;
import entity.Customer;
import entity.User;
import service.UserService;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserFileService {
    private static final UserFileService userFileService = new UserFileService();
    private final String USER_FILEPATH = "src//data//user.csv";

    private UserFileService() {}

    public static UserFileService getInstance() {
        return userFileService;
    }

    public void writeUserList() {
        try {
            FileWriter fw = new FileWriter(new File(USER_FILEPATH));
            CSVWriter csvWriter = new CSVWriter(fw, CSVWriter.DEFAULT_SEPARATOR,
                                                    CSVWriter.NO_QUOTE_CHARACTER,
                                                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                                                    CSVWriter.DEFAULT_LINE_END);
            for (User user : UserService.getInstance().getUserList()) {
                if (user.getName().equals("Admin")) {
                    continue;
                }
                String[] customerStringArray = ((Customer) user).toArray();
                csvWriter.writeNext(customerStringArray);
            }
            csvWriter.close();
            fw.close();
        } catch (IOException exception) {
            System.err.println("Write file Error");
            exception.printStackTrace();
        }
    }

    public void readUserList() {
        try {
            FileReader fr = new FileReader(new File(USER_FILEPATH));
            CSVReader csvReader = new CSVReader(fr);
            String[] data;
            while ((data = csvReader.readNext()) != null) {
                Address newAddress = AddressBuilder.getInstance()
                        .number(data[4])
                        .street(data[5])
                        .ward(data[6])
                        .district(data[7])
                        .province(data[8])
                        .country(data[9])
                        .build();
                LocalDate newDate = LocalDate.parse(data[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                User newUser = CustomerBuilder.getInstance()
                        .name(data[0])
                        .phone(data[1])
                        .password(data[2])
                        .birthday(newDate)
                        .address(newAddress)
                        .email(data[10])
                        .wallet(Long.parseLong(data[11]))
                        .build();
                UserService.getInstance().getUserList().add(newUser);
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
