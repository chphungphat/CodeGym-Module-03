package entity;


import java.util.ArrayList;
import java.util.List;

public class Library {
    private static int count = 0;

    private int id;
    private List<Integer> gameList;
    private List<Integer> wishList;

    public Library(List<Integer> gameList, List<Integer> wishList) {
        this.id = count++;
        this.gameList = gameList;
        this.wishList = wishList;
    }

    public Library() {
        this.id = count++;
        gameList = new ArrayList<>();
        wishList = new ArrayList<>();
        gameList.add(0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getGameList() {
        return gameList;
    }

    public void setGameList(List<Integer> gameList) {
        this.gameList = gameList;
    }

    public List<Integer> getWishList() {
        return wishList;
    }

    public void setWishList(List<Integer> wishList) {
        this.wishList = wishList;
    }

    public String[] toArray() {
        String[] newArray = new String[gameList.size() + wishList.size()];
        newArray[0] = String.valueOf(gameList.size() - 1);
        if (newArray.length == 1) {
            return newArray;
        } else {
            if ((gameList.size() == 1) && (wishList.size() > 0)) {
                newArray[0] = String.valueOf(0);
                for (int index = 0; index < wishList.size(); index++) {
                    newArray[index + 1] = String.valueOf(wishList.get(index));
                }
                return newArray;
            } else if ((gameList.size() > 1) && (wishList.size() == 0)) {
                newArray[0] = String.valueOf(gameList.size() - 1);
                for (int index = 0; index < gameList.size() - 1; index++) {
                    newArray[index + 1] = String.valueOf(gameList.get(index));
                }
            } else if ((gameList.size() > 1) && (wishList.size() > 0)) {
                newArray[0] = String.valueOf(gameList.size() - 1);
                for (int index = 1; index < gameList.size(); index++) {
                    newArray[index] = String.valueOf(gameList.get(index));
                }
                for (int index = 0; index < wishList.size(); index++) {
                    newArray[index + gameList.size()] = String.valueOf(gameList.get(index));
                }
                return newArray;
            }
        }
        return newArray;
    }

}
