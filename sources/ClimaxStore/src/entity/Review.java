package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Review {
    private int userID;
    private int gameID;
    private String review;
    private double rating;
    private LocalDate reviewDate;

    public Review() {}

    public Review(int userID, int gameID, String review, double rating, LocalDate reviewDate) {
        this.userID = userID;
        this.gameID = gameID;
        this.review = review;
        this.rating = rating;
        this.reviewDate = reviewDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    @Override
    public String toString() {
        return review + "\n"
                + "Rating: " + rating + "/10" + "\n"
                + "Review Date: " + reviewDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String[] toArray() {
        String game_id = String.valueOf(gameID);
        String user_id = String.valueOf(userID);
        String rating_str = String.valueOf(rating);
        String reviewDate_str = reviewDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return new String[]{user_id,
                            game_id,
                            review,
                            rating_str,
                            reviewDate_str};
    }
}
