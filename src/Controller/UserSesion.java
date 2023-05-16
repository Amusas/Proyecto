package Controller;

public class UserSesion {
    private static UserSesion instance;
    private String userId;
    private String username;
    private String privileges;

    private UserSesion(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public static UserSesion getInstance(String userId, String username) {
        if (instance == null) {
            instance = new UserSesion(userId, username);
        }
        return instance;
    }

    public void logout() {
        instance = null;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPrivileges() {
        return privileges;
    }
}
