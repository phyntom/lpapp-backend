package co.radiantmic.lpapp.domain;

public class AuthToken {

    private String token;

    private User user;

    public AuthToken() {

    }

    public AuthToken(String token) {
        this.token = "Bearer "+token;
    }

    public String getToken() {

        return token;
    }

    public void setToken(String token) {

        this.token = token;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {

        this.user = user;
    }
}
