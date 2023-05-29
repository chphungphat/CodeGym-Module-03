package service.file_service;

import builder.GameBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import entity.Game;
import service.GameService;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GameFileService {
    private static final GameFileService gameFileService = new GameFileService();

    private GameFileService() {}

    public static GameFileService getInstance() {
        return gameFileService;
    }

    private final String GAME_FILEPATH = "src//data/game.csv";

    public void writeGameList() {
        try {
            FileWriter fw = new FileWriter(GAME_FILEPATH);
            CSVWriter csvWriter = new CSVWriter(fw, CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            for (Game game : GameService.getInstance().getGameList()) {
                String[] gameStringArray = game.toArray();
                csvWriter.writeNext(gameStringArray);
            }
            csvWriter.close();
            fw.close();
        } catch (IOException exception) {
            System.err.println("Write file Error");
            exception.printStackTrace();
        }
    }

    public void readGameList() {
        try (FileReader fr = new FileReader(GAME_FILEPATH);
             CSVReader csvReader = new CSVReader(fr)) {

            String[] data;

            while ((data = csvReader.readNext()) != null) {
                LocalDate newDate = LocalDate.parse(data[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                List<Integer> gametagsList = new ArrayList<>();
                for (int index = 4; index < data.length; index++) {
                    gametagsList.add(Integer.parseInt(data[index]));
                }

                Game newGame = GameBuilder.getInstance()
                        .name(data[0])
                        .price(Long.parseLong(data[1]))
                        .developer(data[2])
                        .releaseDate(newDate)
                        .gametags(gametagsList)
                        .build();
                GameService.getInstance().getGameList().add(newGame);
            }
        } catch (Exception exception) {
            System.err.println("Read file error");
            exception.printStackTrace();
        }
    }
}
