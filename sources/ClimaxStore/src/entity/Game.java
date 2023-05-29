package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private static int count = 1;

    public static final List<String> GameTagList = new ArrayList<>(List.of(
            "Action", //0
            "RPG", //1
            "Story Rich", //2
            "Fighting", //3
            "FPS", //4
            "TPS",  //5
            "Adventure",  //6
            "RTS",  //7
            "Racing",  //8
            "Open World", //9
            "Simulation",  //10
            "Turn-Based", //11
            "Rogue-Like",  //12
            "Puzzle", //13
            "Sport", //14
            "Visual Novel", //15
            "Card & Board", //16
            "Survival", //17
            "Stealth", //18
            "Battle Royal", //19
            "City Building" //20
    ));

    public static void displayGameTagList() {
        int count = 0;
        for (String element : GameTagList) {
            System.out.println(count + ". " + element);
            count++;
        }
    }

    protected int id;
    protected String name;
    protected long price;
    protected String developer;
    protected LocalDate releaseDate;
    protected List<Integer> gametags;

    public Game() {};

    public Game(String name, long price, String developer, LocalDate releaseDate, List<Integer> gametags) {
        this.id = count++;
        this.name = name;
        this.price = price;
        this.developer = developer;
        this.releaseDate = releaseDate;
        this.gametags = gametags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Integer> getGametags() {
        return gametags;
    }

    public void setGametags(List<Integer> gametags) {
        this.gametags = gametags;
    }

    public String printGametags() {
        String text = "";
        for (Integer element : gametags) {
            text += GameTagList.get(element) + " || ";
        }
        return text;
    }

    @Override
    public String toString() {
        return id + " " + name + "\n"
                + "Price: " + price + "\n"
                + "Developer: " + developer + "\n"
                + "Release Date: " + releaseDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n"
                + "Gametags: " + printGametags();
    }

    public String[] gametagToArray() {
        String[] gametagArray = new String[gametags.size()];
        for (int index = 0; index < gametags.size(); index++) {
            gametagArray[index] = String.valueOf(gametags.get(index));
        }
        return gametagArray;
    }

    public String[] mergeArray(String[] array1, String[] array2) {
        int size1 = array1.length;
        int size2 = array2.length;
        String[] newArray = new String[size1 + size2];
        for (int index = 0; index < size1; index++) {
            newArray[index] = array1[index];
        }
        for (int index = 0; index < size2; index++) {
            newArray[size1 + index] = array2[index];
        }
        return newArray;
    }

    public String[] toArray() {
        String[] gameArray = {name,
                            String.valueOf(price),
                            developer,
                            releaseDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))};
        String[] gametagArray = gametagToArray();

        return mergeArray(gameArray, gametagArray);
    }
}
