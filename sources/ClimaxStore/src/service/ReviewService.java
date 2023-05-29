package service;

import entity.Game;
import entity.Review;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReviewService {
    private static final ReviewService reviewService = new ReviewService();

    private ReviewService() {
        reviewList = new ArrayList<>();
    }

    public static ReviewService getInstance() {
        return reviewService;
    }

    private List<Review> reviewList;
    private Review currentReview;

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public Review getCurrentReview() {
        return currentReview;
    }

    public void setCurrentReview(Review currentReview) {
        this.currentReview = currentReview;
    }

    public void chooseGameToReview(List<Integer> gameID) {
        System.out.println("Select a game to write review");
        int choice = 0;
        boolean check = false;
        while (check == false) {
            choice = InputService.getInstance().inputChoice();
            for (int index = 1; index < gameID.size(); index++) {
                if (gameID.get(index) == choice) {
                    check = true;
                }
            }
            if (check == false) {
                System.out.println("Invalid Input");
            }
        }
        GameService.getInstance().setCurrentGame(GameService.getInstance().getGameList().get(choice - 1));
        writeReview();
    }

    public void writeReview() {
        if (!isGameReviewedByUser()) {
            int user_id = UserService.getInstance().getCurrentUser().getId();
            int game_id = GameService.getInstance().getCurrentGame().getId();
            String review = InputService.getInstance().inputReview();
            double rating = InputService.getInstance().inputRating();
            LocalDate date = LocalDate.now();
            Review newReview = new Review(user_id, game_id, review, rating, date);
            reviewList.add(newReview);
            currentReview = reviewList.get(reviewList.size() - 1);
            System.out.println("Review posted");
        } else {
            System.out.println("You already review this game");
        }
    }

    public void displayCurrentReview() {
        String game_name = GameService.getInstance().getGameNameByID(currentReview.getGameID());
        String user_name = UserService.getInstance().getUserNameByID(currentReview.getUserID());
        String review_date = currentReview.getReviewDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Review game " + game_name + " by " + user_name);
        System.out.println("Posted on: " + review_date);
        System.out.println(currentReview.getReview());
        System.out.println("Rating: " + currentReview.getRating());
    }

    public double getAverageRatingByGameID(int id) {
        double totalRating = 0;
        int count = 0;
        for (Review review : reviewList) {
            if (id == review.getGameID()) {
                totalRating += review.getRating();
                count++;
            }
        }
        return totalRating / count;
    }

    public boolean isGameReviewed(int id) {
        for (Review element : reviewList) {
            if (element.getGameID() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean isGameReviewedByUser() {
        int user_id = UserService.getInstance().getCurrentUser().getId();
        int game_id = GameService.getInstance().getCurrentGame().getId();
        for (Review review : reviewList) {
            if ((review.getGameID() == game_id) &&  (review.getUserID() == user_id)) {
                return true;
            }
        }
        return false;
    }

    public void displayReviewByGameID() {
        int id = GameService.getInstance().getCurrentGame().getId();
        String GameName = GameService.getInstance().getGameNameByID(id);
        if (isGameReviewed(id)) {
            System.out.println("All review about " + GameName);
            for (Review element : reviewList) {
                if (element.getGameID() == id) {
                    currentReview = element;
                    displayCurrentReview();
                    System.out.println();
                }
            }
        } else {
            System.out.println("No review about this game yet");
        }
    }

    public void displayReviewByChoice(List<Integer> gameID) {
        System.out.println("Select a game to view reviews");
        int id = 0;
        while (true) {
            id = InputService.getInstance().inputChoice();
            if (gameID.contains(id)) {
                break;
            } else {
                System.out.println("Invalid Input");
            }
        }
        GameService.getInstance().setCurrentGame(GameService.getInstance().getGameList().get(id - 1));
        displayReviewByGameID();
    }
}
