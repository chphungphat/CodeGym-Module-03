public class User {
    private int UserID;
    private int UserName;

    public User(int userID, int userName) {
        UserID = userID;
        UserName = userName;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getUserName() {
        return UserName;
    }

    public void setUserName(int userName) {
        UserName = userName;
    }

    @Override
    public String toString() {
        return UserID + " " + UserName;
    }
}
