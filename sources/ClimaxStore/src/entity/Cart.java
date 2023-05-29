package entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static int count = 1;

    private int id;
    private List<Integer> gameCart;

    public Cart(List<Integer> gameCart) {
        this.id = count++;
        this.gameCart = gameCart;
    }

    public Cart() {
        this.id = count++;
        gameCart = new ArrayList<>();
        gameCart.add(0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getGameCart() {
        return gameCart;
    }

    public void setGameCart(List<Integer> gameCart) {
        this.gameCart = gameCart;
    }

    public String[] toArray() {
        String[] newArray = new String[gameCart.size()];
        for (int index = 0; index < gameCart.size(); index++) {
            newArray[index] = String.valueOf(gameCart.get(index));
        }
        return newArray;
    }
}
