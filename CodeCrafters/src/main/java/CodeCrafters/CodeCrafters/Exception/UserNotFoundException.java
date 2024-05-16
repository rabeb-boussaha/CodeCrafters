package CodeCrafters.CodeCrafters.Exception;

public class UserNotFoundException extends RuntimeException {

    private Long userId;
    private String userName;

    public UserNotFoundException(Long userId) {
        super("User with ID " + userId + " not found");
        this.userId = userId;
    }

    public UserNotFoundException(String userName) {
        super("User with name " + userName + " not found");
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}