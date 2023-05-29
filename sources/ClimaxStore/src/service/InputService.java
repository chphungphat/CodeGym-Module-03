package service;

import builder.AddressBuilder;
import builder.GameBuilder;
import entity.Address;
import entity.Game;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputService {
    private static final InputService inputService = new InputService();
    private final Scanner scanner = new Scanner(System.in);
    private final String NAME_REGEX = "^[a-zA-Z\\s]+";
    private final String EMAIL_REGEX = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
    private final String PHONE_REGEX = "^\\d{10}$";
    private final String DATE_REGEX = "^(0[1-9]|[12][0-9]|[3][01])/(0[1-9]|1[012])/\\d{4}$";
    private final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private InputService() {}

    public static InputService getInstance() {
        return inputService;
    }

    public String inputInfo(String typeInfo) {
        String regex = "";
        switch (typeInfo) {
            case "name" -> regex = NAME_REGEX;
            case "email" -> regex = EMAIL_REGEX;
            case "phone" -> regex = PHONE_REGEX;
            case "password" -> regex = PASSWORD_REGEX;
        }
        Pattern pattern = Pattern.compile(regex);
        String text;
        Matcher matcher;
        while (true) {
            System.out.print("Enter " + typeInfo + ": ");
            text = scanner.nextLine();
//            System.out.println("WHAT THE HELL IS THIS BUG");
            matcher = pattern.matcher(text);
            if (matcher.matches()) {
                return text;
            } else {
                System.out.println("Invalid " + typeInfo);
            }
        }
    }

    public LocalDate inputBirthDate() {
        Pattern pattern = Pattern.compile(DATE_REGEX);
        String text;
        while (true) {
            System.out.print("Enter your birthday (dd/mm/yyyy): ");
            text = scanner.nextLine();
            Matcher matcher = pattern.matcher(text);
            if (matcher.matches()) {
                try {
                    return LocalDate.parse(text, DATE_FORMATTER);
                } catch (Exception exception){
                    System.out.println("Invalid date");
                }
            } else {
                System.out.println("Invalid date");
            }
        }
    }

    public Address inputAddress() {
        System.out.print("Enter house number: ");
        String number = scanner.nextLine();
        System.out.print("Enter street name: ");
        String street = scanner.nextLine();
        System.out.print("Enter ward: ");
        String ward = scanner.nextLine();
        System.out.print("Enter district: ");
        String district = scanner.nextLine();
        System.out.print("Enter province: ");
        String province = scanner.nextLine();
        System.out.print("Enter country: ");
        String country = scanner.nextLine();
        return AddressBuilder.getInstance()
                .number(number)
                .street(street)
                .ward(ward)
                .district(district)
                .province(province)
                .country(country)
                .build();
    }

    public int inputChoice() {
        System.out.print("Enter your choice: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public long inputFundAmount() {
        System.out.print("Enter amount you want to add: ");
        return Long.parseLong(scanner.nextLine());
    }

    public String inputReview() {
        System.out.println("Write your thought about this game");
        return scanner.nextLine();
    }

    public double inputRating() {
        System.out.print("Enter your rating: ");
        return Double.parseDouble(scanner.nextLine());
    }

    public double[] inputPriceRange() {
        double[] result = new double[2];
        while (true) {
            System.out.print("Enter upper price range: ");
            double upper = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter lower price range: ");
            double lower = Double.parseDouble(scanner.nextLine());
            if (upper > lower) {
                result[0] = lower;
                result[1] = upper;
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
        return result;
    }

    public List<Integer> inputGameTagChoice() {
        List<Integer> result = new ArrayList<>();
        Game.displayGameTagList();
        while (true) {
            System.out.print("Enter a gametag to search, input -1 to exit: ");
            int choice = Integer.parseInt(scanner.nextLine());
            if ((choice < -1) || (choice > Game.GameTagList.size())) {
                System.out.println("Invalid input");
            } else if (choice == -1){
                break;
            } else {
                result.add(choice);
            }
        }
        return result;
    }

    public String inputDeveloper() {
        System.out.print("Input developer's name: ");
        return scanner.nextLine();
    }

    public Game inputGameInfo() {
        System.out.print("Enter game's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter game's price: ");
        long price = Long.parseLong(scanner.nextLine());
        System.out.print("Enter developer name: ");
        String dev = scanner.nextLine();
        System.out.print("Enter release date: ");
        LocalDate date = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Add game tags");
        Game.displayGameTagList();
        List<Integer> gametags = new ArrayList<>();
        while (true) {
            System.out.print("Enter gametag id, enter -1 to exit: ");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice != -1) {
                if ((choice < -1) || (choice >= Game.GameTagList.size())) {
                    System.out.println("Invalid Input");
                } else {
                    gametags.add(choice);
                }
            } else {
                break;
            }
        }
        return GameBuilder.getInstance().name(name)
                .price(price)
                .developer(dev)
                .releaseDate(date)
                .gametags(gametags)
                .build();
    }
}
